import sun.jvm.hotspot.debugger.ThreadAccess;

import java.lang.management.ManagementFactory;

/**
 * 搞个死锁，测试一下 jstack pid 命令
 */
public class JavaStack {
    public static void main(String[] args) {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("pid:"+ pid);
        Thread t1 = new Thread(new BlockThread(true));
        Thread t2 = new Thread(new BlockThread(false));
        t1.start();
        t2.start();
    }


}

class BlockThread implements Runnable {

    boolean flag = true;

    public  BlockThread(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag){
            synchronized (LockObj.s0){
                System.out.println("Thread name:" + Thread.currentThread().getName());
                synchronized (LockObj.s1){
                    System.out.println("Thread name:" + Thread.currentThread().getName());

                }
            }
        }else {
            synchronized (LockObj.s1){
                System.out.println("Thread name:" + Thread.currentThread().getName());
                synchronized (LockObj.s0){
                    System.out.println("Thread name:" + Thread.currentThread().getName());

                }
            }
        }
    }
}
class LockObj {
    static Object s0 = new Object();
    static Object s1 = new Object();
}