package com.tubugs.springboot.helper;

import com.tubugs.springboot.utils.Base64Util;
import com.tubugs.springboot.utils.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xuzhang on 2017/8/27.
 */
@Component
public class AutoLoginHelper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${tubugs.login.auto.rsa.key.private}")
    private String privateKey;
    @Value("${tubugs.login.auto.rsa.key.public}")
    private String publicKey;

    public String encrypt(String source) {
        try {
            byte[] bytes = RSAUtil.encrypt(source.getBytes(), publicKey, true);
            String code = Base64Util.encryptBASE64(bytes);
            return code;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }

    }

    public String decrypt(String in) {
        try {
            byte[] bytes = RSAUtil.decrypt(Base64Util.decryptBASE64(in), privateKey, false);
            return new String(bytes);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

}
