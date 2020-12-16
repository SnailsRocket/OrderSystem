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
import com.xubo.modules.generator.config.RedisConfig;
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

    @Autowired
    public RedisConfig redisConfig;

    /**
     * 先校验验证码是否正确，验证码存放在redis 里面，设置过期时间为3分钟，
     *  正确再去查数据库中是否有改手机号的User
     * @param userDto
     * @return
     * @throws ParseException
     */
    @PostMapping("/login")
    public R login(@RequestBody UserDto userDto) throws ParseException {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", userDto.getPhone());
        UserEntity user = userService.getOne(wrapper, true);
        if (user != null) {
            // 用户已注册，判断密码是否正确，正确则返回token信息，用户携带token跳转到主页
            return user.getPassword() == userDto.getPassword() ?
                    R.ok().put("登录成功！", user) : R.error(401,"密码输入错误，请重新输入面！");
        } else {
//            执行注册操作,并跳转主页
            UserEntity userEntity = new UserEntity();
            userEntity.setPhone(userDto.getPhone());
            userEntity.setPassword(userDto.getPassword());
            userEntity.setCreateTime(new Date());

            userService.save(userEntity);
            return R.ok().put("注册成功！",userEntity);
        }
    }

}
