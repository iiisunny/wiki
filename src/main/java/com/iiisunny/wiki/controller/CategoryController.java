package com.iiisunny.wiki.controller;

import com.iiisunny.wiki.common.CommonRes;
import com.iiisunny.wiki.req.CategoryQuerylReq;
import com.iiisunny.wiki.req.CategorySaveReq;
import com.iiisunny.wiki.resp.CategoryQueryResp;
import com.iiisunny.wiki.resp.PageResp;
import com.iiisunny.wiki.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @description: 电子书控制器
 * @author: iiisunny
 * @create: 2021-03-07 21:17
 **/

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonRes list(@Valid CategoryQuerylReq req){
        CommonRes<PageResp<CategoryQueryResp>> resp = new CommonRes<>();
        PageResp<CategoryQueryResp> list = categoryService.getList(req);
        resp.setContent(list);
        return resp;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonRes save(@Valid @RequestBody CategorySaveReq req) {
        CommonRes resp = new CommonRes<>();
        categoryService.save(req);
        return resp;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CommonRes delete(@PathVariable Long id) {
        CommonRes resp = new CommonRes<>();
        categoryService.delete(id);
        return resp;
    }
}
