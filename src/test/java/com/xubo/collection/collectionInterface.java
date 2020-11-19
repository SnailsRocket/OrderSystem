package com.xubo.collection;

/**
 * @Author Druid_Xu
 * @Date 2020/11/18 下午 02:00
 * @Description
 * JDK 8 新特性 接口里面 static default 这两个关键字修饰的方法可以有方法体
 *
 */
//@FunctionalInterface //
public interface collectionInterface {

    static void show() {
        System.out.println("show");
    }
    default void play() {
        System.out.println("play");
    }

//    异常
    /*void sing() {

    }*/
}
