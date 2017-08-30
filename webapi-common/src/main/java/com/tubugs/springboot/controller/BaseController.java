package com.tubugs.springboot.controller;

import com.tubugs.springboot.service.log.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xuzhang on 2017/8/23.
 */
public class BaseController {

    @Autowired
    protected LogService logger;

    protected Session getHttpSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
