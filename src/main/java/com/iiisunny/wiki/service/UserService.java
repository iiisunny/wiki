package com.iiisunny.wiki.service;

import com.iiisunny.wiki.model.UserModel;
import com.iiisunny.wiki.req.UserLoginReq;
import com.iiisunny.wiki.req.UserQueryReq;
import com.iiisunny.wiki.req.UserResetPasswordReq;
import com.iiisunny.wiki.req.UserSaveReq;
import com.iiisunny.wiki.resp.UserLoginResp;
import com.iiisunny.wiki.resp.UserQueryResp;
import com.iiisunny.wiki.resp.PageResp;

/**
 * @description: 电子书service类
 * @author: iiisunny
 * @create: 2021-03-07 21:14
 **/

public interface UserService {

    // 查询用户
    PageResp<UserQueryResp> getList(UserQueryReq req);

    // 保存用户
    void save(UserSaveReq req);

    // 修改密码
    void resetPassword(UserResetPasswordReq req);

    // 删除用户
    void delete(Long id);

    // 根据用户名查询用户
    UserModel selectByLoginName(String loginName);

    // 登录
    UserLoginResp login(UserLoginReq req);

}
