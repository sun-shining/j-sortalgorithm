package juc;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 关键字可以保证可见性、原子性
 */
public class ThreadSynchronized {

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        new Thread(() ->{
            data.add();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Data.num);
    }
}

class Data {
    public static int num = 1;

    public synchronized void add() {
        ++num;
    }
}