package com.iiisunny.wiki.service;

import com.iiisunny.wiki.req.UserQueryReq;
import com.iiisunny.wiki.req.UserSaveReq;
import com.iiisunny.wiki.resp.UserQueryResp;
import com.iiisunny.wiki.resp.PageResp;

/**
 * @description: 电子书service类
 * @author: iiisunny
 * @create: 2021-03-07 21:14
 **/

public interface UserService {

    // 查询电子书列表
    PageResp<UserQueryResp> getList(UserQueryReq req);

    // 编辑电子书详情按钮
    void save(UserSaveReq req);

    // 删除电子书
    void delete(Long id);

}
