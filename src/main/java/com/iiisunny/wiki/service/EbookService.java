package com.iiisunny.wiki.service;

import com.iiisunny.wiki.req.EbookQuerylReq;
import com.iiisunny.wiki.req.EbookSaveReq;
import com.iiisunny.wiki.resp.EbookQueryResp;
import com.iiisunny.wiki.resp.PageResp;

/**
 * @description: 电子书service类
 * @author: iiisunny
 * @create: 2021-03-07 21:14
 **/

public interface EbookService {

    // 查询电子书列表
    PageResp<EbookQueryResp> getList(EbookQuerylReq req);

    // 编辑电子书详情按钮
    void save(EbookSaveReq req);

    // 删除电子书
    void delete(Long id);

}
