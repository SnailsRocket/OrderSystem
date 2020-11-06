package com.xubo.modules.security.controller;

import java.sql.Wrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody UserDto userDto) throws ParseException {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", userDto.getPhone());
        UserEntity user = userService.getOne(wrapper, true);
        if (user != null) {
            return R.ok().put("user", user);
        } else {
//            执行注册操作
            UserEntity userEntity = new UserEntity();
            userEntity.setPhone(userDto.getPhone());
            userEntity.setPassword(userDto.getPassword());

            userEntity.setCreateTime(new Date());

            userService.save(userEntity);
            return R.ok().put("注册成功！",userEntity);
        }
    }

}
