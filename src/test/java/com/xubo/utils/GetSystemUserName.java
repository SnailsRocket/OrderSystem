package com.xubo.utils;

/**
 * @Author Druid_Xu
 * @Date 2020/10/16 上午 09:32
 * @Description 获取当前 windows 的 用户名称
 * 不能使用 _ 和 $ 开头 结尾 阿里规范 但是为什么扫不出来
 */
public class GetSystemUserName {

    public static void main(String[] args) {

        String userName = System.getProperty("user.name");
        System.out.println(userName);


    }
}
