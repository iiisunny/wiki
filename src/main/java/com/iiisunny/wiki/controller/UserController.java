package com.iiisunny.wiki.controller;

import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.req.UserLoginReq;
import com.iiisunny.wiki.req.UserQueryReq;
import com.iiisunny.wiki.req.UserResetPasswordReq;
import com.iiisunny.wiki.req.UserSaveReq;
import com.iiisunny.wiki.resp.UserLoginResp;
import com.iiisunny.wiki.resp.UserQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @description: 电子书控制器
 * @author: iiisunny
 * @create: 2021-03-07 21:17
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonRes list(@Valid UserQueryReq req){
        CommonRes<PageResp<UserQueryResp>> resp = new CommonRes<>();
        PageResp<UserQueryResp> list = userService.getList(req);
        resp.setContent(list);
        return resp;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonRes save(@Valid @RequestBody UserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonRes resp = new CommonRes<>();
        userService.save(req);
        return resp;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CommonRes delete(@PathVariable Long id) {
        CommonRes resp = new CommonRes<>();
        userService.delete(id);
        return resp;
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public CommonRes resetRassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonRes resp = new CommonRes<>();
        userService.resetPassword(req);
        return resp;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public CommonRes login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonRes<UserLoginResp> resp = new CommonRes<>();
        UserLoginResp userLoginResp = userService.login(req);
        resp.setContent(userLoginResp);
        return resp;
    }
}
