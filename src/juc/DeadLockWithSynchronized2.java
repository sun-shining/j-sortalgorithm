package juc;


import java.util.concurrent.TimeUnit;

/**
 *
 * @Author dasongju
 * @Date 2021/1/20 21:54
 */
public class DeadLockWithSynchronized2 extends Thread {

    private String first;
    private String second;

    public DeadLockWithSynchronized2(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained :  " + first);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second) {
                System.out.println(this.getName() + " obtained :  " + second);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";

        DeadLockWithSynchronized2 t1 = new DeadLockWithSynchronized2("Thread1", lockA, lockB);
        DeadLockWithSynchronized2 t2 = new DeadLockWithSynchronized2("Thread2", lockB, lockA);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

