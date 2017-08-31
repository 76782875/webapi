package com.tubugs.springboot.frame.aop;


import com.alibaba.fastjson.JSONObject;
import com.tubugs.springboot.consts.RedisKey;
import com.tubugs.springboot.frame.ex.NoCsrfTokenException;
import com.tubugs.springboot.frame.ex.NoRightException;
import com.tubugs.springboot.helper.RedisHelper;
import com.tubugs.springboot.frame.SessionManager;
import com.tubugs.springboot.service.log.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuzhang2 on 2017/1/16.
 */
@Component
@Aspect
public class ControllerAspect {

    @Autowired
    private LogService logger;

    @Value("${csrf.on}")
    private boolean csrf_on;

    @Autowired
    private RedisHelper redisHelper;

    /**
     * 拦截
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(!void com..*Controller.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //防csrf攻击
        if (csrf_on) {
            String csrf = request.getHeader("ticket");
            if (StringUtils.isEmpty(csrf)) {
                throw new NoCsrfTokenException();
            }
        }

        //用户多地登录，剔除原先用户
        Subject subject = SecurityUtils.getSubject();
        String account = SessionManager.getUserAccount();
        if (subject.isAuthenticated()) {
            String user_unique_session_id = redisHelper.get(String.format(RedisKey.USER_UNIQUE_SESSIONID, account));
            if (!SessionManager.getSessionID().equals(user_unique_session_id)) {
                subject.logout();
                throw new NoRightException();
            }
        }

        //日志记录
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long timeDiff = System.currentTimeMillis() - start;
        String result = String.format(
                "用户:%s,会话:%s\r\n请求:%s\r\n响应:%s\r\n时间:%s ms",
                SessionManager.getUserAccount(),
                SessionManager.getSessionID(),
                String.format("%s %s %s", request.getMethod(), request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap())),
                object instanceof String ? object : JSONObject.toJSONString(object),
                timeDiff);

        if (timeDiff > 1000) {
            logger.warn(result);
        } else {
            logger.info(result);
        }

        return object;
    }
}
