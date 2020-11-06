package com.xubo.modules.common.base.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.xubo.modules.common.base.BaseTranslate;
import com.xubo.modules.generator.entity.UserEntity;
import com.xubo.modules.security.dto.UserDto;

/**
 * @Author Druid_Xu
 * @Date 2020/11/6 下午 02:34
 * @Description
 */
public class UserTranslate implements BaseTranslate<UserDto,UserEntity>{

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * dto 转 entity
     * dto 中属性太少，没有必要 写 转换类
     * 这个尝试 抽象成能高度复用的class
     * @param userDto
     * @return
     * @throws ParseException
     */
    @Override
    public UserEntity toEntity(UserDto userDto) throws ParseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhone(userDto.getPhone());
        userEntity.setPassword(userDto.getPassword());
        return null;
    }

    @Override
    public UserDto toDto(UserEntity entity) {

        return null;
    }

    @Override
    public List<UserEntity> toEntity(List<UserDto> dtoList) {

        return null;
    }

    @Override
    public List<UserDto> toDto(List<UserEntity> entityList) {

        return null;
    }
}
