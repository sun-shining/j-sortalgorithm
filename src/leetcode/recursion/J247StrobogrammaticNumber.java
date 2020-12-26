package leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 递归的解题步骤： 1.完整性检查：判断输入的参数是否合法 2.判断递归是否应该结束 3.缩小问题规模 4.整合结果
 * 题目参考地址<a href="{https://www.cnblogs.com/grandyang/p/5200919.html">
 * n = 0:   none
 *
 * n = 1:   0, 1, 8
 *
 * n = 2:   11, 69, 88, 96
 *
 * n = 3:   101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986
 */
public class J247StrobogrammaticNumber {

    public static void main(String[] args) {
        List<String> helper = helper(2, 2);
        System.out.println(helper);
    }

    /**
     *  n标识递归过程到了哪一步,比如m=4,则n在递归过程可以取4 3 2 1
     *  m则标识求的是几位的中心对称数
     **/
    static List<String> helper(int n, int m) {
        //Sanity Check
        if (n < 0 || m < 0 || n > m) {
            throw new IllegalArgumentException();
        }

        //递归何时停止
        if (n == 0) {
            return Collections.singletonList("");
        }
        if (n == 1) {
            return Arrays.asList("0", "1", "8");
        }

        //缩小问题规模  需要找规律
        List<String> list = helper(n - 2, m);

        //整合结果【当问题缩小到一定程度时，具体的处理措施】
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            if (n != m) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}
