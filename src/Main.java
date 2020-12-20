import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//测试测试jinfo命令修改虚拟机参数，以 PrintGC PrintGCDetails PrintGCTimeStamp 来测试de
public class Main {
    //-Xmx20m -Xms20m -Xmn2m
    public static void main(String[] args) {

        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);//name的值：1051@MacBook-Pro-4.local
        // get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        int k = 0;
        while(true)
        {
            byte[] b = null;
            for (int i = 0; i < 10; i++) {
                b = new byte[1 * 1024 * 1024];
                ++k;
                System.out.println("~~~:"+k);
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
