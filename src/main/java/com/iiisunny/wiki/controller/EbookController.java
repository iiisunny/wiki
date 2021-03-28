package com.iiisunny.wiki.controller;

import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.req.EbookQuerylReq;
import com.iiisunny.wiki.req.EbookSaveReq;
import com.iiisunny.wiki.resp.EbookQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.EbookService;
import com.iiisunny.wiki.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @description: 电子书控制器
 * @author: iiisunny
 * @create: 2021-03-07 21:17
 **/

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private EbookService ebookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonRes list(@Valid EbookQuerylReq req){
        CommonRes<PageResp<EbookQueryResp>> resp = new CommonRes<>();
        PageResp<EbookQueryResp> list = ebookService.getList(req);
        resp.setContent(list);
        return resp;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonRes save(@Valid @RequestBody EbookSaveReq req) {
        CommonRes resp = new CommonRes<>();
        ebookService.save(req);
        return resp;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CommonRes delete(@PathVariable Long id) {
        CommonRes resp = new CommonRes<>();
        ebookService.delete(id);
        return resp;
    }
}
