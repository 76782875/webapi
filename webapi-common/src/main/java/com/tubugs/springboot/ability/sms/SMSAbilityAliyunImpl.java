package com.tubugs.springboot.ability.sms;

import com.alibaba.fastjson.JSONObject;
import com.tubugs.springboot.utils.HttpUtil;
import com.tubugs.springboot.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 阿里云短信服务
 * Created by xuzhang on 2017/8/29.
 */
public class SMSAbilityAliyunImpl implements SMSAbility {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${tubugs.sms.aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${tubugs.sms.aliyun.accessSecret}")
    private String accessSecret;
    @Value("${tubugs.sms.aliyun.verifycode.templateCode}")
    private String verifyCodeTemplateCode;
    @Value("${tubugs.sms.aliyun.verifycode.signName}")
    private String verifyCodeSignName;

    @Override
    public boolean send(String templateCode, String signName, String telephone, JSONObject params) {
        boolean result = sendMsg(templateCode, signName, telephone, params);
        return result;
    }

    @Override
    public String sendVerifyCode(String telephone) {
        JSONObject json = new JSONObject();
        String code = String.valueOf(NumberUtil.generateVerifyCode());
        json.put("code", code);
        boolean result = sendMsg(verifyCodeTemplateCode, verifyCodeSignName, telephone, json);
        return result ? code : null;
    }

    private boolean sendMsg(String templateCode, String signName, String telephone, JSONObject json) {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
        java.util.Map<String, String> paras = new java.util.HashMap<String, String>();

        // 1. 系统参数
        paras.put("SignatureMethod", "HMAC-SHA1");
        paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
        paras.put("AccessKeyId", accessKeyId);
        paras.put("SignatureVersion", "1.0");
        paras.put("Timestamp", df.format(new java.util.Date()));
        paras.put("Format", "JSON");

        // 2. 业务API参数
        paras.put("Action", "SendSms");
        paras.put("Version", "2017-05-25");
        paras.put("RegionId", "cn-hangzhou");
        paras.put("PhoneNumbers", telephone);
        paras.put("SignName", signName);
        paras.put("TemplateParam", json.toJSONString());
        paras.put("TemplateCode", templateCode);

        // 3. 去除签名关键字Key
        if (paras.containsKey("Signature"))
            paras.remove("Signature");

        // 4. 参数KEY排序
        java.util.TreeMap<String, String> sortParas = new java.util.TreeMap<String, String>();
        sortParas.putAll(paras);

        // 5. 构造待签名的字符串
        java.util.Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();

        try {
            while (it.hasNext()) {
                String key = it.next();
                sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
            }
            String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
            StringBuilder stringToSign = new StringBuilder();
            stringToSign.append("GET").append("&");
            stringToSign.append(specialUrlEncode("/")).append("&");
            stringToSign.append(specialUrlEncode(sortedQueryString));
            String sign = sign(accessSecret + "&", stringToSign.toString());
            // 6. 签名最后也要做特殊URL编码
            String signature = specialUrlEncode(sign);
            String result = HttpUtil.doGet("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp);
            logger.info(result);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    private String specialUrlEncode(String value) throws Exception {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    private String sign(String accessSecret, String stringToSign) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return new sun.misc.BASE64Encoder().encode(signData);
    }
}
