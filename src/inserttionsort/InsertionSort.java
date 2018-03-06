package inserttionsort;

import selectionsort.SelectionSort;
import utils.SortFlagInterface;
import utils.SortUtils;

import java.lang.annotation.Annotation;

public class InsertionSort implements SortFlagInterface{

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
     * 优化后的插入排序，内层循环不在发现元素位置不对时就进行交换，而是在内层循环寻找array[i]合适的位置，这样省去2次交换
     * @param array
     * @param n
     */
    public  void sort(int[] array, int n){
        for (int i = 0; i < n; i++) {
            int tep = array[i];//寻找arry[i]应该存放的合适位置
            int j; //j表示arry[i]应该存放的位置
            for (j = i; j > 0; j--){
                //此处可以这么做的原因是，优化后的插入排序是为了找到array[i]应该在的位置，找到即可提前结束循环，这也是优化的点，减少循环次数
                //而冒泡是要挨个比较大小，把大的往后挪，故一次比较结果正确就结束不适合冒泡排序
                if (array[j-1] > tep){ //这一行也可以扔到for循环里，j>0后面
                    array[j] = array[j-1];
                }
            }
            array[j] = tep;
        }
    }

    public static void main(String[] args) {
        int a[] = SortUtils.generateRandomIntArray(50000, 0, 100000);
//        new InsertionSort().sort(a, 50000);
        SortUtils.calSortTime(new InsertionSort(), a, 50000);//插入排序 10000个56ms 10万个 5850毫秒 优化后4333毫秒
//        SortUtils.calSortTime(new SelectionSort(), a, 100000);//10000 517ms 10万个 7318毫秒 第二次 25766毫秒
    }

}
