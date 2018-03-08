package apractise;

import java.util.Arrays;

/**
 * 如何计算给定数组中逆序对个数
 * 1，可直接挨个比较，时间复杂度为 n^2级别
 * 2，利用归并排序，将时间复杂度优化到 nlogn 级别
 */
public class InversionCount {

    private InversionCount(){}

    public static void mergeSort(int[] array, int n){
        merge(array, 0, n-1);
    }

    private static int merge(int[] array, int l, int r) {
        if (l>=r) return 0;

        int mid = (l+r)/2;
        int res1 = merge(array, l, mid);
        int res2 = merge(array, mid+1, r);
        return res1 + res2 + merge(array, l, mid, r);

    }

    private static int merge(int[] array, int l, int mid, int r) {

        int aux[] = Arrays.copyOfRange(array, l, r+1);

        //初始化逆序对儿个数为0
        int res = 0;

        //初始化，i起始位置指向l，j起始位置指向mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {

            if (i > mid ) {
                array[k] = aux[j-1];
                j++;
            }else if ( j > r) {
                array[k] = aux[i-1];
            }else if (aux[i-1]<=aux[j-1]){
                array[k]=aux[i-1];
                i++;
            }else{//只有当两边都排好序时，右边的小于左边时，才统计逆序对数量
                array[k]=aux[j-1];
                j++;
                res += (mid -i +1);
            }
        }
        return res;
    }
}
