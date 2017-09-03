package com.tubugs.springboot.controller.v1;

import com.tubugs.springboot.controller.BaseController;
import com.tubugs.springboot.dto.ResultDto;
import com.tubugs.springboot.frame.ResponseVo;
import com.tubugs.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private UserService userService;

    @ApiOperation(value = "发送注册验证码")
    @RequestMapping(value = "phone_register_send_code", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> phone_register_send_code(@RequestParam String phone) {
        ResultDto resultDto = userService.phoneRegisterSendCode(phone);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "注册")
    @RequestMapping(value = "phone_register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> phone_register(@RequestParam String password, @RequestParam String client_code) {
        ResultDto resultDto = userService.phoneRegister(password, client_code);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> login(@RequestParam String account, @RequestParam String password) {
        ResultDto resultDto = userService.login(account, password);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "自动登录")
    @RequestMapping(value = "login_auto", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> login_auto(@RequestParam String client_token) {
        ResultDto resultDto = userService.loginAuto(client_token);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "login_out", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<Boolean> login_out() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseVo(true);
    }

    @ApiOperation(value = "忘记密码-发送验证码")
    @RequestMapping(value = "phone_forget_send_code", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> phone_forget_send_code(@RequestParam String phone) {
        ResultDto resultDto = userService.phoneForgetSendCode(phone);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "忘记密码-验证验证码")
    @RequestMapping(value = "phone_forget_verify_code", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> phone_forget_verify_code(@RequestParam String client_code) {
        ResultDto resultDto = userService.phoneForgetVerifyCode(client_code);
        return new ResponseVo(resultDto);
    }

    @ApiOperation(value = "忘记密码-修改密码")
    @RequestMapping(value = "phone_forget_modify_pwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> phone_forget_modify_pwd(@RequestParam String new_pwd) {
        ResultDto resultDto = userService.phoneForgetModifyPwd(new_pwd);
        return new ResponseVo(resultDto);
    }
}
