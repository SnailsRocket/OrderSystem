package com.xubo.modules.security.controller;

import java.sql.Wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xubo.modules.generator.entity.UserEntity;
import com.xubo.modules.generator.service.ComboMenuService;
import com.xubo.modules.generator.service.UserService;
import com.xubo.modules.generator.utils.R;
import com.xubo.modules.security.dto.UserDto;

/**
 * @Author Druid_Xu
 * @Date 2020/11/6 上午 11:49
 * @Description
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody UserDto userDto) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDto.getPhone());
        UserEntity user = userService.getOne(wrapper, true);
        return R.ok().put("user",user);
    }

}
