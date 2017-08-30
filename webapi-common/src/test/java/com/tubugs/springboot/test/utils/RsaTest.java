package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.RSAUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by xuzhang on 2017/8/27.
 */
public class RsaTest {

    private String publicKey = null;
    private String privateKey = null;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSAUtil.initKey();
        publicKey = RSAUtil.getPublicKey(keyMap);
        privateKey = RSAUtil.getPrivateKey(keyMap);

        System.out.println("公钥 -> " + publicKey);
        System.out.println("私钥 -> " + privateKey);
    }

    @Test
    public void test1() throws Exception {
        System.out.println("公钥加密，私钥解密");
        String sourceString = "hi, RSA";

        byte[] encodedData = RSAUtil.encrypt(sourceString.getBytes(), publicKey, true);
        byte[] decodedData = RSAUtil.decrypt(encodedData, privateKey, false);

        String targetString = new String(decodedData);
        System.out.println("加密前: " + sourceString + "，解密后: " + targetString);
        Assert.assertEquals(sourceString, targetString);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("私钥签名，公钥验证签名");
        String sourceString = "hello, RSA sign";
        byte[] data = sourceString.getBytes();

        // 产生签名
        String sign = RSAUtil.sign(data, privateKey);
        System.out.println("签名 -> " + sign);

        // 验证签名
        boolean status = RSAUtil.verify(data, publicKey, sign);
        System.out.println("状态 -> " + status);
        Assert.assertTrue(status);
    }

    @Test
    public void test3() throws Exception {
        System.out.println("私钥加密，公钥解密");
        String sourceString = "hello, reRSA";
        byte[] data = sourceString.getBytes();

        byte[] encodedData = RSAUtil.encrypt(data, privateKey, false);
        byte[] decodedData = RSAUtil.decrypt(encodedData, publicKey, true);

        String targetString = new String(decodedData);
        System.out.println("加密前: " + sourceString + "，解密后: " + targetString);
        Assert.assertEquals(sourceString, targetString);
    }

}
