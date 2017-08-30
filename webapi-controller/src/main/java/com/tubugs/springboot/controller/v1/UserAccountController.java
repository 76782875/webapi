package com.tubugs.springboot.controller.v1;

import com.tubugs.springboot.consts.WebRedisKey;
import com.tubugs.springboot.controller.BaseController;
import com.tubugs.springboot.dto.ResultDto;
import com.tubugs.springboot.frame.ResponseVo;
import com.tubugs.springboot.helper.RedisHelper;
import com.tubugs.springboot.helper.AutoLoginHelper;
import com.tubugs.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuzhang on 2017/8/26.
 */
@Controller
@RequestMapping("v1/user_account/")
@Api(tags = "v1/user_account/", description = "用户账号")
public class UserAccountController extends BaseController {

    @Autowired
    private RedisHelper redis;

    @Autowired
    private UserService userService;

    @Autowired
    private AutoLoginHelper rsaHelper;

    @ApiOperation(value = "注册")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> register(@RequestParam String account, @RequestParam String password) {
        return new ResponseVo<Boolean>(userService.register(account, password));
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> login(@RequestParam String account, @RequestParam String password) {
        ResultDto resultDto = userService.login(account, password);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "login_out", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<Boolean> login_out() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseVo<Boolean>(true);
    }

    @ApiOperation(value = "自动登录")
    @RequestMapping(value = "auto_login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> auto_login(@RequestParam String client_token) {
        //token解密失败，需要重新登录
        String accountAndPassword = rsaHelper.decrypt(client_token);
        if (StringUtils.isEmpty(accountAndPassword))
            //TODO 改成配置
            return new ResponseVo(new ResultDto(false, "token错误"));
        String[] results = accountAndPassword.split(UserService.SPLIT_STRING);
        String account = results[0];
        String password = results[1];

        //客户端token与服务端token不一致，需要重新登录
        String auto_login_key = String.format(WebRedisKey.AUTO_LOGIN, account);
        String server_token = redis.get(auto_login_key);
        if (!client_token.equals(server_token))
            //TODO 改成配置
            return new ResponseVo(new ResultDto(false, "token不一致"));

        ResultDto auto_login_value = userService.login(account, password);
        return new ResponseVo(auto_login_value);
    }
}
