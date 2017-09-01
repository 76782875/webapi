package com.tubugs.springboot.dto;

/**
 * Created by xuzhang on 2017/8/28.
 */
public class ResultDto {
    private boolean success;
    private String tip;
    private String extend;

    public ResultDto() {
    }

    public ResultDto(boolean success, String tip) {
        this.success = success;
        this.tip = tip;
    }

    public ResultDto(boolean success, String tip, String extend) {
        this.success = success;
        this.tip = tip;
        this.extend = extend;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }
}
