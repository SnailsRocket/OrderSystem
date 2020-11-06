package com.xubo.modules.security.dto;

import lombok.Data;

/**
 * @Author Druid_Xu
 * @Date 2020/11/6 上午 11:52
 * @Description
 */
@Data
public class UserDto {
    private String phone;
    private String password;
    private String code;

    public UserDto() {
    }

    public UserDto(String phone, String password, String code) {
        this.phone = phone;
        this.password = password;
        this.code = code;
    }
}
