package com.iiisunny.wiki.service;

import com.iiisunny.wiki.req.DocQuerylReq;
import com.iiisunny.wiki.req.DocSaveReq;
import com.iiisunny.wiki.resp.DocQueryResp;
import com.iiisunny.wiki.resp.PageResp;

import java.util.List;

/**
 * @description: 电子书service类
 * @author: iiisunny
 * @create: 2021-03-07 21:14
 **/

public interface DocService {

    // 查询电子书列表
    PageResp<DocQueryResp> list(DocQuerylReq req);

    // 查询电子书列表
    List<DocQueryResp> all();

    // 编辑电子书详情按钮
    void save(DocSaveReq req);

    // 删除电子书
    void delete(Long id);

    void delete(List<String> ids);

}
