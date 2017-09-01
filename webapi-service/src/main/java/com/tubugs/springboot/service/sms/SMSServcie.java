package com.tubugs.springboot.service.sms;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by xuzhang on 2017/8/29.
 */
public interface SMSServcie {

    /**
     * 发送普通短信消息
     *
     * @param templateCode 短信模板
     * @param signName     短信签名
     * @param telephone    发送手机号
     * @param params       模板参数
     * @return
     */
    boolean send(String templateCode, String signName, String telephone, JSONObject params);

    /**
     * 发送验证码短信
     *
     * @param telephone 发送手机号
     * @return 验证码
     */
    String sendVerifyCode(String telephone);
}
