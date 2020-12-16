package com.xubo.modules.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xubo.modules.generator.config.RedisConfig;

/**
 * @Author Druid_Xu
 * @Date 2020/12/16 下午 03:46
 * @Description
 */
@RestController
@RequestMapping("/code")
public class VerifyController {

    @Autowired
    public RedisConfig redisConfig;



}
