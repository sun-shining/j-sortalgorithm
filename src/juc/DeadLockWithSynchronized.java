package juc;

import java.util.concurrent.TimeUnit;

/**
 * 1.互斥条件
 * 2.不可剥夺条件
 * 3.请求和保持条件
 * 4.循环等待条件
 * @Author dasongju
 * @Date 2021/1/20 21:54
 */
public class DeadLockWithSynchronized{

    public static void main(String[] args) {
        String lock1 = "lockA";
        String lock2 = "lockB";
        new Thread(()->{
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " get lock1 = " + lock1);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("get lock2 = " + lock2);
                }
            }
        }, "Thread-A").start();

        new Thread(()->{
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock2 = " + lock2);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("get lock1 = " + lock1);
                }
            }
        }, "Thread-B").start();
    }
}

