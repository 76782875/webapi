package com.tubugs.springboot.frame;

/**
 * Created by Administrator on 2017/1/16.
 */
public enum ResponseStatus {
    RESPONSE_SUCCESS("0000", "成功"),
    RESPONSE_INNER_ERROR("0001", "内部异常"),
    RESPONSE_ERROR_PARAMS("0002", "参数错误"),
    RESPONSE_NO_RIGHT("0003", "没有权限"),
    RESPONSE_OVER_TIMES("0004", "超过调用次数"),
    RESPONSE_NOT_CLIENT_REQUEST("0005", "不是客户端的请求"),;

    ResponseStatus(String code, String des) {
        this.code = code;
        this.des = des;
    }

    private String code;
    private String des;

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
