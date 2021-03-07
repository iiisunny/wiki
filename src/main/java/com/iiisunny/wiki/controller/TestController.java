package com.iiisunny.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: 项目启动类
 * @author: iiisunny
 * @create: 2021-03-06 22:26
 **/

@RestController
@RequestMapping("/list")
public class TestController {

    @Value("${iisuny.port:1}")
    private String iiisunyPort;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello world" + iiisunyPort;
    }

    @RequestMapping(value = "/hello/post", method = RequestMethod.POST)
    public String hellopost(String name){
        return "hello world" + name;
    }
}
