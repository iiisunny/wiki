package com.iiisunny.wiki.service;

import com.iiisunny.wiki.req.EbookModelReq;
import com.iiisunny.wiki.resp.EbookModelResp;

import java.util.List;

/**
 * @description: 电子书service类
 * @author: iiisunny
 * @create: 2021-03-07 21:14
 **/

public interface EbookService {

    List<EbookModelResp> getList(EbookModelReq req);

}
