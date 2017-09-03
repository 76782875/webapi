package com.tubugs.springboot.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by xuzhang on 2017/9/3.
 */
public class PwdUtil {
    private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;


    /**
     * 生成盐值
     *
     * @return
     */
    public static String generateSalt() {
        return randomNumberGenerator.nextBytes().toHex();
    }


    /**
     * 给密码加盐
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String computePwdWithSalt(String pwd, String salt) {
        return new SimpleHash(algorithmName, pwd, ByteSource.Util.bytes(salt), hashIterations).toHex();
    }
}
