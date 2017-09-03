package com.tubugs.springboot.consts;

/**
 * Created by xuzhang on 2017/8/27.
 */
public class RedisKey {
    public static final String LOGIN_ONLY = "user.login.only.%s";//唯一登录令牌
    public static final String LOGIN_AUTO = "user.login.auto.%s";  //自动登录令牌
    public static final String LOGIN_FAIL_TIMES = "user.login.fail.times.%s";//登录失败次数

    public static final String REGISTER_CODE_SEND_TIMES = "user.phoneRegister.code.send.times.%s"; //注册验证码发送次数
    public static final String REGISTER_CODE_FAIL_TIMES = "user.phoneRegister.code.fail.times.%s"; //注册验证码验证失败次数

    public static final String FORGET_CODE_SEND_TIMES = "user.forget.code.send.times.%s";//忘记密码验证码发送次数
    public static final String FORGET_CODE_FAIL_TIMES = "user.forget.code.fail.times.%s";//忘记密码验证码验证失败此时
}
