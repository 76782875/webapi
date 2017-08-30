package com.tubugs.springboot.frame.aop;

import com.alibaba.fastjson.JSONObject;
import com.tubugs.springboot.frame.ResponseStatus;
import com.tubugs.springboot.frame.ResponseVo;
import com.tubugs.springboot.frame.ex.NoCsrfTokenException;
import com.tubugs.springboot.frame.ex.NoRightException;
import com.tubugs.springboot.frame.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuzhang2 on 2017/1/18.
 */
@ControllerAdvice
public class HandlerException {
    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    //运行时异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseVo ExceptionHandler(Exception ex) {
        HttpServletRequest r = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String request = String.format(
                "用户:%s,会话:%s\r\n请求:%s;",
                SessionManager.getUserAccount(),
                SessionManager.getSessionID(),
                String.format("%s %s %s", r.getMethod(), r.getRequestURI(), JSONObject.toJSONString(r.getParameterMap()))
        );

//        if (ex instanceof ValidationException || ex instanceof MissingServletRequestParameterException) {
//            //参数错误
//            ResponseVo responseVo = new ResponseVo<>(ResponseStatus.RESPONSE_ERROR_PARAMS);
//            responseVo.setDes(responseVo.getDes() + ":" + ex.getMessage());
//            return responseVo;
//        } else if (ex instanceof NoLoginException) {
//            //没有权限
//            return new ResponseVo(ResponseStatus.RESPONSE_NO_RIGHT);
//        }

        if (ex instanceof NoCsrfTokenException) {
            return new ResponseVo(ResponseStatus.RESPONSE_NOT_CLIENT_REQUEST);
        } else if (ex instanceof NoRightException) {
            return new ResponseVo(ResponseStatus.RESPONSE_NO_RIGHT);
        }


        //内部异常
        logger.error("Exception :" + request, ex);
        return new ResponseVo(ResponseStatus.RESPONSE_INNER_ERROR);
    }
}
