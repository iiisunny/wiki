package com.iiisunny.wiki.controller;

import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.req.DocQuerylReq;
import com.iiisunny.wiki.req.DocSaveReq;
import com.iiisunny.wiki.resp.DocQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * @description: 电子书控制器
 * @author: iiisunny
 * @create: 2021-03-07 21:17
 **/

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonRes list(@Valid DocQuerylReq req){
        CommonRes<PageResp<DocQueryResp>> resp = new CommonRes<>();
        PageResp<DocQueryResp> list = docService.list(req);
        resp.setContent(list);
        return resp;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public CommonRes all(){
        CommonRes<List<DocQueryResp>> resp = new CommonRes<>();
        List<DocQueryResp> list = docService.all();
        resp.setContent(list);
        return resp;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonRes save(@Valid @RequestBody DocSaveReq req) {
        CommonRes resp = new CommonRes<>();
        docService.save(req);
        return resp;
    }

    @RequestMapping(value = "/delete/{idsstr}", method = RequestMethod.DELETE)
    public CommonRes delete(@PathVariable String idsstr) {
        CommonRes resp = new CommonRes<>();
        List<String> list = Arrays.asList(idsstr.split(","));
        docService.delete(list);
        return resp;
    }
}
