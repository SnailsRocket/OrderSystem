package com.xubo.modules.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.captcha.utils.CaptchaUtil;

/**
 * @Author Druid_Xu
 * @Date 2020/12/18 上午 08:50
 * @Description
 */
@RestController
public class CaptchaController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        CaptchaUtil.out(request,response);

    }
}
