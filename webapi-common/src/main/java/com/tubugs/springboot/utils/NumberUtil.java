package com.tubugs.springboot.utils;

/**
 * Created by xuzhang on 2017/8/25.
 */
public class NumberUtil {

    /**
     * 生成固定长度数字
     *
     * @return
     */
    public static long generate(int length) {
        double base = Math.pow(10, length - 1);
        double r = Math.random() * base * 9 + base;
        return (long) r;
    }

    /**
     * 生成验证码（6位长度）
     *
     * @return
     */
    public static long generateVerifyCode() {
        return NumberUtil.generate(6);
    }

    /**
     * 生成用户编号（10位长度）
     *
     * @return
     */
    public static long generateUserNo() {
        return NumberUtil.generate(10);
    }
}
