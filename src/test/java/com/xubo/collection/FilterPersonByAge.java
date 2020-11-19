package com.xubo.collection;

import com.xubo.test.Person;

/**
 * @Author Druid_Xu
 * @Date 2020/11/18 下午 02:26
 * @Description
 * 条件过滤接口实现类
 * 过滤条件是年龄
 */
public class FilterPersonByAge implements MyFilterByAge<Person>{
    private static final Integer AGE = 30;
    @Override
    public boolean test(Person person) {
        return AGE<(person.getAge());
    }
}
