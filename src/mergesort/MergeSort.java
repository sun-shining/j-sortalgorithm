package mergesort;

import inserttionsort.InsertionSort;
import utils.SortUtils;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = SortUtils.generateRandomArray(10, 1, 100);
        SortUtils.printArray(array);
        long start = System.nanoTime();
        mergeSort(array, 10);
        long end = System.nanoTime();
        System.out.println("时间为 ：" + (end-start));
        SortUtils.printArray(array);
        System.out.println(SortUtils.isSorted(array, 10));
    }

    /**
     * 思路是用二分发，将一个数组，一分为二（分成的两部分，因为可以使用递归到没边只有一个元素，所以，分成的两边就是排好序的），再合并
     * 自顶向下的核心在合并，因为最后数组被分成一个个的元素，关键在将任意两个有序的子序列合并
     * @param array
     * @param n
     */
    public static void mergeSort(int array[], int n){
        mergeSortFromL2R(array, 0 , n-1);
    }

    private static void mergeSortFromL2R(int array[] , int l, int r){
//        if (l >= r)
//            return;
        // 优化2: 对于小规模数组, 使用插入排序
        if (r-l <= 15) {
            InsertionSort.sort(array, l, r);
            return;
        }
        int mid = (l+r)/2;
        mergeSortFromL2R(array, l, mid);//左边有序
        mergeSortFromL2R(array, mid+1, r);//右边有序
        // 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
        // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
        if (array[mid] > array[mid+1])
            mergeTwoArray(array, l, mid, r);//合并左右
//            mergeTwoArray2(array, l, mid, r);
    }

    //合并已经排好序的一个数组的两部分 1172271
    public static void mergeTwoArray(int[] array, int l, int mid, int r) {
        int aux[] = new int[r-l+1];

        for (int i = l; i <= r; i++) {//i是从l开始的，所以复制原数组时，需要注意这个偏移
            aux[i-l] = array[i];
        }

        int i = l, j = mid+1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {//说明左边的都已经移动完毕，将右边剩余的元素依次赋值给原数组即可
                array[k] = aux[j-l];
                j++;
            }
            else if (j>r){//同理
                array[k] = aux[i-l];
                i++;
            }
            else if (aux[i-l] < aux[j-l]) {//哪个小，哪个赋值给array[k]
                array[k] = aux[i-l];
                i++;
            }
            else {
                array[k] = aux[j-l];
                j++;
            }

        }
    }

    //1422425
    //自顶向下排序，一定注意处理边界，另外就是这个是通用的方法，注意处理左边界l
    public static void mergeTwoArray2(int[] array, int l, int mid, int r) {
        //如果左边已经大于右边，说明已经是有序了，直接返回
        if (l >= r) return;

        int[] aux = new int[r-l+1];

        int index1 = l; //左边开始的索引
        int index2 = mid + 1; //右边开始的索引

        int i = 0;
        //先从左一右一开始处理，把数组排一遍
        while (index1 <= mid && index2 <= r) {
            if (array[index1] <= array[index2]) {
                aux[i] = array[index1];
                index1++;
                i++;
            } else {
                aux[i] = array[index2];
                index2++;
                i++;
            }

        }

        //左边先处理完，则再处理右边
        while (index1 <= mid) {
            aux[i] = array[index1];
            index1++;
            i++;
        }

        //右边先处理完，则再处理左边
        while (index2 <= r) {
            aux[i] = array[index2];
            index2++;
            i++;
        }

        //将aux中排好序的元素，赋值回原数组array中
        for (int j = 0; j < aux.length; j++) {
            array[j+l] = aux[j];//加l是因为这是个通用方法，数组是从l开始的，不是从0开始
        }
    }
}
