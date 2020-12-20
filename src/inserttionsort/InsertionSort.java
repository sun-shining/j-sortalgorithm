package inserttionsort;

import java.util.concurrent.TimeUnit;
import selectionsort.SelectionSort;
import utils.SortFlagInterface;
import utils.SortUtils;

import java.lang.annotation.Annotation;

/**
 * 插入排序的主要性质就是可以快速结束内层循环，特别适合基本有序的排序
 **/
public class InsertionSort implements SortFlagInterface {

    //10000个随机数字，运行时间为56毫秒
//    public  void sort(int[] array, int n){
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j > 0; j--){
//                if (array[j-1] > array[j]){
//                    SortUtils.swap4Int(j-1, j, array);
//                }
//            }
//        }
//    }

    /**
     * 优化后的插入排序，内层循环不在发现元素位置不对时就进行交换，而是在内层循环寻找array[i]合适的位置，这样省去2次交换 2020年02月28日11:58:06 运行结果不对呢？
     * for (int i = 0; i < n; i++) { int tep = array[i]; int j; //j表示arry[i]应该存放的位置 for (j = i; j >
     * 0; j--){ //此处可以这么做的原因是，优化后的插入排序是为了找到array[i]应该在的位置，找到即可提前结束循环，这也是优化的点，减少循环次数
     * //而冒泡是要挨个比较大小，把大的往后挪，故一次比较结果正确就结束不适合冒泡排序 if (array[j-1] > tep){ //这一行也可以扔到for循环里，j>0后面
     * array[j] = array[j-1]; } } array[j] = tep;// } 改成下面的代码可以了.上面不行的原因是j的值内层循环完肯定是0
     */
    public void sort(int[] array, int n) {
        for (int i = 1; i < n; i++) {
            int tep = array[i];//寻找arry[i]应该存放的合适位置
            int j; //j表示arry[i]应该存放的位置
            for (j = i; j > 0 && array[j - 1] > tep; j--) {
                //此处可以这么做的原因是，优化后的插入排序是为了找到array[i]应该在的位置，找到即可提前结束循环，这也是优化的点，减少循环次数
                //而冒泡是要挨个比较大小，把大的往后挪，故一次比较结果正确就结束不适合冒泡排序
                //这一行也可以扔到for循环里，j>0后面
                array[j] = array[j - 1];
            }
            array[j] = tep;
        }
    }

    /**
     * 此方案可行
     **/
    public void sort2(int[] array, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int value = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }

    public static void sort(int[] array, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int temp = array[i];
            int j;
            for (j = i; j > l && array[j - 1] > temp; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }

    public static void insertSort2(int[] arr) {
        for (int i = 1, j; i < arr.length; i++) {
            int current = arr[i];
            for (j = i - 1; j >= 0 && arr[j] > current; j--) {
                arr[j + 1] = arr[j];
            }
            //j+1的原因是上面内层for循环结束的时候j又--了一次
            arr[j + 1] = current;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int a[] = new int[]{2, 1};//SortUtils.generateRandomIntArray(10, 0, 100);
        SortUtils.printArray(a);
        System.err.println();
//        new InsertionSort().sort(a, 10);
        InsertionSort.insertSort2(a);
        TimeUnit.MILLISECONDS.sleep(100);
        SortUtils.printArray(a);
//        int a[] = SortUtils.generateRandomIntArray(50000, 0, 100000);
//        new InsertionSort().sort(a, 50000);
//        SortUtils.calSortTime(new InsertionSort(), a, 50000);//插入排序 10000个56ms 10万个 5850毫秒 优化后4333毫秒
//        SortUtils.calSortTime(new SelectionSort(), a, 100000);//10000 517ms 10万个 7318毫秒 第二次 25766毫秒
    }

}
