package com.xubo.collection;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.xubo.test.Person;

/**
 * @Author Druid_Xu
 * @Date 2020/11/18 下午 01:28
 * @Description
 * jdk 8 新特性
 *  函数式接口
 *  Lambda表达式
 *  方法引用和构造器调用
 *  StreamAPI
 *      针对集合的操作
 *      lists.stream().map().collect()
 *  接口中静态方法和默认方法
 *  新时间日期api
 *
 * 函数式编程将List<Person> 这个集合转成  List<Stirng>
 * Lambda表达式 本质上是一段匿名内部类，也可以是一段可以传递的代码
 */
public class NewFeture {

    public static void main(String[] args) {
        Person p1 = new Person("Druid", 23, "江苏");
        Person p2 = new Person("xubo", 24, "湖北");
        Person p3 = new Person("chenzikun", 90, "江苏");
        Person p4 = new Person("liyaoting", 89, "江苏");
        List<Person> persons = new ArrayList<Person>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);

        /**
         * jdk8 函数式编程
         */
//        streamcode(persons);

//        streamapi(persons);

//        filterPeopleByAge(persons);

//        filterPeopleByAgePro(persons);

//        filterPeopleByAgePro1(persons);

//        filterPeopleByAgePro2(persons);

//        filterPeopleByAgePro3(persons);

        dateTiemFeture();


    }

    /**
     * JDK 8 提供的时间类 LocalDate LocalTime LocalDateTime
     *
     *   之前使用的java.util.Date月份从0开始，我们一般会+1使用，很不方便，java.time.LocalDate月份和星期都改成了enum
     *   java.util.Date和SimpleDateFormat都不是线程安全的，而LocalDate和LocalTime和最基本的String一样，是不变类型，
     *   不但线程安全，而且不能修改。
     *   java.util.Date是一个“万能接口”，它包含日期、时间，还有毫秒数，更加明确需求取舍
     *   新接口更好用的原因是考虑到了日期时间的操作，经常发生往前推或往后推几天的情况。用java.util.Date配合Calendar要写好多代码，
     *   而且一般的开发人员还不一定能写对。
     *
     */
    private static void dateTiemFeture() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println(format);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String time1 = dateTimeFormatter.format(localDateTime);
        System.out.println(time1);
    }

    /**
     * streamapi
     *
     * @param persons
     */
    private static void streamapi(List<Person> persons) {
        Stream<Person> stream = persons.stream();
        stream.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });
    }

    /**
     * 函数式编程
     *
     * @param persons
     */
    private static void streamcode(List<Person> persons) {
        List<String> names = persons.stream().map(Person::getName).collect(Collectors.toList());
        for (String name : names) {
            System.out.println(name);
        }
    }

    /**
     * Lambda 表达式 比匿名内部类还简洁
     */
    private static void lambdademo() {
        //匿名内部类
        Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> set = new TreeSet<>(cpt);

        System.out.println("=========================");

        //使用lambda表达式
        Comparator<Integer> cpt2 = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> set2 = new TreeSet<>(cpt2);
    }

    /**
     * 过滤Person 中年龄大于 30 的 ，
     * 这个是先遍历然后再判断年龄，耗时操作
     *
     * @param persons
     * @return
     */
    private static List<Person> filterPeopleByAge(List<Person> persons) {
        List<Person> personList = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getAge() > 30) {
                personList.add(person);
            }
        }
        personList.stream().forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });
        return personList;
    }

    /**
     * 方案1：
     * 上面那个过滤有个问题，如果过滤条件有几百行，不可能次判断都重写一次
     * 可以抽象成一个接口，实现该接口有一个弊端，每换一个过滤条件，就需要重写一个 FilterPersonByAge 类
     * 这个方案
     *
     * @param persons
     * @return
     */
    private static void filterPeopleByAgePro(List<Person> persons) {
        FilterPersonByAge filterPersonByAge = new FilterPersonByAge();
        List<Person> personList = new ArrayList<Person>();
        for (Person person : persons) {
            if (filterPersonByAge.test(person)) {
                personList.add(person);
            }
        }
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    /**
     * 方案2：使用匿名内部类来实现
     * 先定义一个过滤方法，实现过滤的时候，匿名创建这个过滤方法
     *
     * @param persons
     */
    private static void filterPeopleByAgePro1(List<Person> persons) {

        List<Person> personList = filterMethod(persons, new MyFilterByAge<Person>() {
            /**
             * 重写 MyFilterByAge 接口的方法，在filterMethod 中的 mf.test会 执行这个匿名类的实现
             * @param person
             * @return
             */
            @Override
            public boolean test(Person person) {
                return person.getAge() > 30;
            }
        });
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    /**
     * 匿名内部类实现过滤/ 也实现Lambda的过滤方法
     * 过滤的时候只掉调用这个method
     * 执行到if 语句的时候，会去 filterPeopleByAgePro1 中找匿名类(这个类实现了MyFilterByAge接口)
     * 但是 MyFilterByAge 这个接口 有一个实现类，一个匿名类实现类 ，为什么是去找匿名实现类
     *
     * @param persons
     * @param mf
     * @return
     */
    private static List<Person> filterMethod(List<Person> persons, MyFilterByAge<Person> mf) {
        List<Person> personList = new ArrayList<Person>();
        for (Person person : persons) {
            if (mf.test(person)) {
                personList.add(person);
            }
        }
        return personList;
    }

    /**
     * 方案3： 使用lambda表达式实现
     * 调用 filterMethod 方法， 方案2 是用匿名内部类实现 MyFilterByAge 接口 ，方案3 是使用Lambda表达式实现判断逻辑
     *
     * @param persons
     */
    private static void filterPeopleByAgePro2(List<Person> persons) {
        List<Person> personList = filterMethod(persons, (p) ->
                p.getAge() > 30
        );
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    /**
     * 方案4：使用jdk 8新特性 Stream api
     *
     * @param persons
     */
    private static void filterPeopleByAgePro3(List<Person> persons) {
        persons.stream().filter((p) -> p.getAge() > 30).limit(5).forEach(System.out::println);

    }

}
