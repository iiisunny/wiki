package com.iiisunny.wiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.req.UserLoginReq;
import com.iiisunny.wiki.req.UserQueryReq;
import com.iiisunny.wiki.req.UserResetPasswordReq;
import com.iiisunny.wiki.req.UserSaveReq;
import com.iiisunny.wiki.resp.UserLoginResp;
import com.iiisunny.wiki.resp.UserQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.UserService;
import com.iiisunny.wiki.service.impl.UserServiceImpl;
import com.iiisunny.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;


/**
 * @description: 电子书控制器
 * @author: iiisunny
 * @create: 2021-03-07 21:17
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private RedisTemplate redisTemplate;

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

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token，{}，并放入redis中", token);

        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600*24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;
    }

    @RequestMapping(value = "/logout/{token}", method = RequestMethod.GET)
    public CommonRes logout(@PathVariable String token) {
        CommonRes resp = new CommonRes<>();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token：{}", token);
        return resp;
    }
}
