package mergesort;

import utils.SortUtils;

/**
 * 自低向上的归并排序
 */
public class MergeSortBU {
    
    public static void mergeSortBU(int[] array, int n){

        //第一次考察一个元素，第二次考察2个，第三次考察4个
        for (int sz = 1; sz < n; sz+=sz) {
            for (int i = 0; i+sz < n; i+=sz+sz) {
                //对于 array[mid] <= array[mid+1] 的情况，不进行merge
                if (array[i+sz-1] > array[i+sz])
                     MergeSort.mergeTwoArray2(array, i,i+sz-1, Math.min(i+sz+sz-1 ,n-1));
            }
        }
    }

    public static void main(String[] args) {
        int[] a = SortUtils.generateRandomArray(101, 0, 1000);
        long start = System.nanoTime();
        MergeSortBU.mergeSortBU(a, 101);
        long end = System.nanoTime();
        System.out.println("时间为： " + (end-start));
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+ " ");
        }

    }
}
