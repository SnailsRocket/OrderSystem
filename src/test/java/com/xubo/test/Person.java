package com.xubo.test;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Druid_Xu
 * @Date 2020/10/19 下午 04:48
 * @Description
 * 要使用Person 中的属性 ，直接Person.age 不需要另外创建对象，无谓增加编译器解析成本
 */
@Getter
@Setter
public class Person {

    public String name;
    public int age;
    public String address;

    public Person() {
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
