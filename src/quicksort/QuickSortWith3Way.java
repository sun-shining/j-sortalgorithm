package quicksort;

import inserttionsort.InsertionSort;
import utils.SortUtils;

/**
 * 适合有有多个和选定标尺值相等的数组，但是普通随机数组速度也不慢，所以一般是首选。
 * 三路快速排序
 */
public class QuickSortWith3Way {

    private QuickSortWith3Way(){}

    public static void sort(int array[], int n){
        sortWith3Way(array, 0, n-1);
    }

    private static void sortWith3Way(int[] array, int l, int r) {

        if (r-l <=15){
            InsertionSort.sort(array, l, r);
            return;
        }

        //随机标尺，防止快速排序退化为n^2级别的算法
        SortUtils.swap4Int(l, (int)(Math.random()*(r-l+1))+l, array);
        int v = array[l];

        //如此选三个指针的初始值是为了保证循环未开始前，三个范围内都没有元素
        int lt = l; // array[l+1...lt]<v
        int gt = r+1; // array[gt...r]>v
        int i = l+1; // array[lt+1...i)==v
        while (i < gt) {
            if (array[i] < v) {
                SortUtils.swap4Int(i, lt+1, array);
                lt++;
                i++;
            } else if (array[i] > v) {
                SortUtils.swap4Int(i, gt-1, array);//为啥是gt-1，因为你最大的元素第一个位置一定是r
                gt--;
            } else {
                i++;
            }
        }

        //为啥选的是lt相关计算而不是gt相关计算，因为标尺v放在数组左边啦
        SortUtils.swap4Int(l, lt, array);
        sortWith3Way(array, l, lt-1);//上一步把和v相等的元素换到lt位置啦，所以这一步，左边排序到lt-1就行
        sortWith3Way(array, gt, r);
    }

    public static void main(String[] args) {
        int[] array = SortUtils.generateRandomArray(1000000, 0, 100000);
        long start = System.nanoTime();
        QuickSort.sort(array, 1000000);
        long end = System.nanoTime();
        System.out.println("时间为：" + (Long)(end-start));
        System.out.println(SortUtils.isSorted(array, 1000000));
    }
}
