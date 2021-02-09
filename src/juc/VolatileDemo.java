package juc;

import java.util.concurrent.TimeUnit;

/**
 * 内存可见性示例代码
 *
 * @Author dasongju
 * @Date 2021/2/9 15:12
 */
public class VolatileDemo {
    static class MyData {
        /**
         *  不添加volatile关键字时，每个线程拿到的多少主内存内变量的副本，没有可见性保证，所以当另外的线程修改值时，主线程感知不到
         **/
//        int data = 0;
        volatile int data = 0;
        void addData() {
            this.data = 60;
        }
    }

    public static void main(String[] args) {
        MyData mydata = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in,data:" + mydata.data);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addData();
            System.out.println(Thread.currentThread().getName() + "\t come out,data:" + mydata.data);
        }, "AAA").start();

        while (mydata.data == 0) {}

        System.out.println(Thread.currentThread().getName() + "\t mission is over,data:" + mydata.data);
    }
}
