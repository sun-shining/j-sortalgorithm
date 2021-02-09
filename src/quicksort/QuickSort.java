package quicksort;

import inserttionsort.InsertionSort;
import utils.SortUtils;

import java.util.Random;

/**
 * 理解那张动态图
 */
public class QuickSort {

    private QuickSort() {
    }

    ;//禁止产生任何实例

    public static void main(String[] args) {
        int[] array = SortUtils.generateRandomArray(1000000, 1, 10);
//        SortUtils.printArray(array);
        long start = System.nanoTime();
        QuickSort.sort(array, 1000000);
        long end = System.nanoTime();
        System.out.println("时间为：" + (Long) (end - start));
        System.out.println(SortUtils.isSorted(array, 1000000));
//        SortUtils.printArray(array);
    }

    public static void sort(int[] a, int n) {
        QuickSort.sort(a, 0, n - 1);
    }

    //对a[l,r] 左闭右闭 进行快速排序
    private static void sort(int[] a, int l, int r) {
//        if (l >= r) return;
        //优化1 当需要排序的数量小时，还是用插入排序
        if (r - l <= 15) {
            InsertionSort.sort(a, l, r);
            return;
        }


//        int j = partition(a, l, r);
        int j = partition2(a, l, r);
        sort(a, l, j - 1);
        sort(a, j + 1, r);
    }

    // 对arr[l...r]部分进行partition操作
    // 返回j, 使得arr[l...j-1] < arr[j] ; arr[j+1...r] > arr[j]
    private static int partition(int[] a, int l, int r) {
        //***优化二，如果直接选取最左边的元素做为标尺，可能选到最小的元素，将a[l]的值随机化
        int k = (int) (Math.random() * (r - l + 1)) + l;
        SortUtils.swap4Int(l, k, a);

        int s = a[l];
        int j = l;//a[l+1...j] <s, a[j+1...r]>s

        for (int i = l + 1; i <= r; i++) {//i是每次循环考察的元素
            if (a[i] < s) {
                j++;//一直移动j的位置是为了确认标尺最后应该停在什么地方
                SortUtils.swap4Int(i, j, a);
            }
        }

        SortUtils.swap4Int(j, l, a);

        return j;
    }

    /**
     * 两路快速排序，适合解决存在大量重复元素的情况
     *
     * @param nums 待排序数据
     * @param left 数组左边界
     * @param right 数组右边界
     * @return 新标尺应该在的位置
     */
    private static int partition2(int[] nums, int left, int right) {
        int k = (int) (Math.random() * (right - left + 1)) + left;
        SortUtils.swap4Int(left, k, nums);
        int s = nums[left];

        int i = left + 1, j = right;
        while (true) {
            while (i <= right && nums[i] < s) i++;
            while (j >= left + 1 && nums[j] > s) j--;
            if (i > j) break;
            SortUtils.swap4Int(i, j, nums);
            i++;
            j--;
        }

        SortUtils.swap4Int(left, j, nums);
        return j;
    }

}
