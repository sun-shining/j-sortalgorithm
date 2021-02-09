package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁也叫递归锁
 * synchronize和ReentrantLock都是可重入锁
 * 最大的作用是避免死锁
 * 生活中的例子是住的房子，大门一道锁，不会在厕所卧室再上锁，即使上了，打开大门默认
 * 厕所厨房也是可以直接进去的
 * 外层锁所取到锁了，内层方法还有锁可以直接进去
 * 官方的解释：线程可以进入任何一个它已经拥有锁所同步着的代码块
 *
 * synchronize 实现的可重入锁
 * AAA	  invoke sendSMS
 * AAA	  ========>invoke sendEmail
 * BBB	  invoke sendSMS
 * BBB	  ========>invoke sendEmail
 *
 * reentrantLock 实现的可重入锁
 * CCC	  invoke sendSMS
 * CCC	  ========>invoke sendEmail
 * DDD	  invoke sendSMS
 * DDD	  ========>invoke sendEmail
 *
 * @Author dasongju
 * @Date 2021/2/9 15:39
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        System.out.println(" =========== ");

        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(new Phone(), "CCC").start();
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(new Phone(), "DDD").start();
    }

}

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t  invoke sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t  ========>invoke sendEmail");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();
    private void get() {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t  invoke sendSMS");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t  ========>invoke sendEmail");
        } finally {
            lock.unlock();
        }
    }
}