package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：不阻塞，而是采用循环的方式的尝试获取锁
 * 好处是避免了线程切换，坏处是加剧cpu的消耗，如果长时间获取不到，有可能拖慢系统性能
 *
 * @Author dasongju
 * @Date 2021/2/9 16:30
 */
public class SpinLockDemo {

    public static void main(String[] args) {
        MySpinLock spinLock = new MySpinLock();
        new Thread(()->{
            spinLock.getLock();
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            spinLock.unlock();
        }, "AAA").start();

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()-> {
            spinLock.getLock();
            spinLock.unlock();
        }, "BBB").start();
    }
}

class MySpinLock {
    private AtomicReference<Thread> reference = new AtomicReference<>();

    public void getLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t thread come in");
        while (!reference.compareAndSet(null, thread)){}
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t thread come out and release lock");
    }
}