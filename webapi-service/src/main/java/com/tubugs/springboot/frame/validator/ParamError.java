package com.tubugs.springboot.frame.validator;

/**
 * Created by xuzhang on 2017/9/3.
 */
public class ParamError extends RuntimeException {

    public ParamError() {
    }

    public ParamError(String message) {
        super(message);
    }
}
