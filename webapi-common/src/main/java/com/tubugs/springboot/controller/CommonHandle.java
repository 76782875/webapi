package com.tubugs.springboot.controller;

import com.tubugs.springboot.frame.ResponseStatus;
import com.tubugs.springboot.frame.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuzhang on 2017/8/27.
 */
@Controller
@RequestMapping("/")
@Api(tags = "/", description = "通用返回")
//通用返回，请求页面没权限时进入，不走AOP拦截
public class CommonHandle {

    @ApiOperation(value = "没有权限")
    @RequestMapping(value = "no_right", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<Boolean> no_right() {
        return new ResponseVo(ResponseStatus.RESPONSE_NO_RIGHT);
    }
}
