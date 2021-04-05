package com.iiisunny.wiki.service;

import com.iiisunny.wiki.req.CategoryQuerylReq;
import com.iiisunny.wiki.req.CategorySaveReq;
import com.iiisunny.wiki.resp.CategoryQueryResp;
import com.iiisunny.wiki.resp.PageResp;

/**
 * @description: 电子书service类
 * @author: iiisunny
 * @create: 2021-03-07 21:14
 **/

public interface CategoryService {

    // 查询电子书列表
    PageResp<CategoryQueryResp> getList(CategoryQuerylReq req);

    // 编辑电子书详情按钮
    void save(CategorySaveReq req);

    // 删除电子书
    void delete(Long id);

}
