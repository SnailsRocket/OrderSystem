package com.xubo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Druid_Xu
 * @Date 2020/11/6 下午 03:58
 * @Description
 */
public class GetSystemTime {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        System.out.println(new Date());
        System.out.println(sdf.parse(sdf.format(new Date())));
    }
}
