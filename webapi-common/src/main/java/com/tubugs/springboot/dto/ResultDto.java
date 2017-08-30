package com.tubugs.springboot.dto;

/**
 * Created by xuzhang on 2017/8/28.
 */
public class ResultDto {
    private boolean success;
    private String descript;
    private String extend;

    public ResultDto() {
    }

    public ResultDto(boolean success, String descript) {
        this.success = success;
        this.descript = descript;
    }

    public ResultDto(boolean success, String descript, String extend) {
        this.success = success;
        this.descript = descript;
        this.extend = extend;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }
}
