package selectionsort;

import utils.SortFlagInterface;
import utils.SortUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort implements SortFlagInterface{

    /**
     * 选择排序算法
     * @param arry 待排序数组
     * @param n 数组大小
     */
    public   void sort(int arry[], int n){

        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i+1; j < n ; j++) {
                if (compareTwoValue(arry[j], arry[minIndex])) {
                    minIndex = j;
                }
            }
            SortUtils.swap4Int(i, minIndex, arry);
        }
    }


    private static <T> boolean compareTwoValue(T t, T t1) {
        if (t instanceof Integer) {
            return (Integer)t < (Integer) t1;
        } else if (t instanceof Long) {
            return (Long)t < (Long)t1;
        } else if (t instanceof String){
            return ((String) t).compareTo((String)t1) < 0;
        }
        return false;
    }

    public static void main(String[] args) {
//        Integer a[] ={10,9,8,7,6,5,4,3,2,1};
        int a[] = SortUtils.generateRandomArray(10,0,10);
//        String[] a = {"D","C","B","A"};
        new SelectionSort().sort(a,10);
        SortUtils.printArray(a);

    }


}
