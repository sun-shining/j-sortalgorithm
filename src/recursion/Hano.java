package recursion;

/**
 * 递归的解题思路有两个： 1.从问题的结果出发； 2.每次操作将问题的规模缩小1；
 *
 * 解题步骤：
 *  1.判断当前情况是否非法，如果非法就立即返回；也即完整性校验（Sanity Check）
 *  2.判断递归是否应该结束
 *  3.缩小问题规模
 *  4.整合结果
 */
public class Hano {

    public static void hano(char A, char B, char C, int n) {
        if (n > 0) {
            hano(A, C, B, n - 1);
            System.out.println(A + "->" + C);
            hano(B, A, C, n - 1);
        }
    }

    public static void main(String[] args) {
        char A = 'A';
        char B = 'B';
        char C = 'C';
        hano(A, B, C, 3);
    }
}
