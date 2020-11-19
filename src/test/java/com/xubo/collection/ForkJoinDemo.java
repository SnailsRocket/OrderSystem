package com.xubo.collection;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.junit.Test;

/**
 * @Author Druid_Xu
 * @Date 2020/11/18 下午 03:50
 * @Description
 * Fork-join 框架 ：在必要的时候将一个大的任务分解(Fork)成若干个小任务,然后交有多线程去执行，
 *  然后将得到的结果(join)到一起
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    /**
     * 要想使用 ForkJoin 类必须继承 RecursiveAction (无返回值) 或者 RecursiveTask (有返回值)
     * @return
     */
    private static final long serialVersionUID = 23423422L;
    private long start;
    private long end;

    /*public ForkJoinDemo() {
    }*/

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
//    定义阀值
    private static final long THRESHOLD = 10000L;

    @Override
    protected Long compute() {
//        判断是否执行时间是否超过阀值，超过阀值就拆分任务
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (end - start) / 2;
            ForkJoinDemo left = new ForkJoinDemo(start, middle);
            //拆分子任务，压入线程队列
            left.fork();
            ForkJoinDemo right = new ForkJoinDemo(middle + 1, end);
            right.fork();

            //合并并返回
            return left.join() + right.join();
        }
    }

    /**
     * 实现数的累加
     */
    @Test
    public void test() {
//        开始时间
        Instant start = Instant.now();

//        创建一个线程池
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10000000000L);
//        没有返回值
//        pool.execute(task);

//        有返回值
        Long sum = pool.invoke(task);

        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).getSeconds());
    }

    /**
     * jdk 8 并行流 parallel
     *
     */

}
