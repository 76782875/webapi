package com.tubugs.springboot.frame;

/**
 * Created by xuzhang2 on 2017/1/6.
 */
public class ResponseVo<E> {
    private String des;
    private String msg;
    private E data;

    public ResponseVo() {
        this.msg = ResponseStatus.RESPONSE_SUCCESS.getCode();
        this.des = ResponseStatus.RESPONSE_SUCCESS.getDes();
    }

    public ResponseVo(E data) {
        this.msg = ResponseStatus.RESPONSE_SUCCESS.getCode();
        this.des = ResponseStatus.RESPONSE_SUCCESS.getDes();
        this.data = data;
    }

    public ResponseVo(ResponseStatus responseStatus) {
        this.msg = responseStatus.getCode();
        this.des = responseStatus.getDes();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
