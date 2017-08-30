package com.tubugs.springboot.controller.v1;

import com.tubugs.springboot.controller.BaseController;
import com.tubugs.springboot.dao.entity.User;
import com.tubugs.springboot.frame.ResponseVo;
import com.tubugs.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuzhang on 2017/8/27.
 */
@Controller
@RequestMapping("v1/user/")
@Api(tags = "v1/user/", description = "用户")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户信息")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    //@Cacheable(value = "user_info", keyGenerator = "keyGenerator")
    public ResponseVo<User> info(@RequestParam String username) {
        logger.info(String.format("get user:%s info", username));
        return new ResponseVo(userService.getUserInfoByAccount(username));
    }
}
