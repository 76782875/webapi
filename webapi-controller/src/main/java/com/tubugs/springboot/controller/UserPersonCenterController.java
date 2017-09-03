package com.tubugs.springboot.controller;

import com.tubugs.springboot.dto.ResultDto;
import com.tubugs.springboot.frame.ResponseVo;
import com.tubugs.springboot.frame.SessionManager;
import com.tubugs.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by xuzhang on 2017/9/1.
 */
@Controller
@RequestMapping("user_person_center")
@Api(tags = "user_person_center", description = "用户个人中心")
public class UserPersonCenterController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "修改个人资料")
    @RequestMapping(value = "modify_info", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> modify_info(@RequestParam(required = false) String nick_name, @RequestParam(required = false) MultipartFile avatar, @RequestParam(required = false) String email, @RequestParam(required = false) byte sex, @RequestParam(required = false) String birthday) throws IOException {
        Boolean result = userService.modifyInfo(SessionManager.getUserAccount(), nick_name, avatar, email, sex, birthday);
        return new ResponseVo(result);
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "modify_pwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<ResultDto> modify_pwd(@RequestParam String old_pwd, @RequestParam String new_pwd) {
        ResultDto result = userService.modifyPwd(SessionManager.getUserAccount(), old_pwd, new_pwd);
        return new ResponseVo(result);
    }
}
