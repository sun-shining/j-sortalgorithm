package utils;

import com.sun.tools.corba.se.idl.IncludeGen;

import java.util.Random;

public class SortUtils
{

    /**
     * 交换给定数组{@Param array[]}中索引为i和j两个位置的值
     * @param i 索引
     * @param j 索引
     * @param array 数组
     * @param <T>
     * @return 交换位置后的数组
     */
    public static <T> T[] swap(int i , int j , T  array[]){
        T temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    public static int[] swap4Int(int i , int j , int array[]){
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     * @param n 数组个数
     * @param rangeL 数组内元素起始范围
     * @param rangeR 数组内元素结束范围
     * @return 一个包含n个元素的数组
     */
    public static int[] generateRandomArray(int n, int rangeL, int rangeR){
        /*int[] array = new int[n];

        for (int i = 0; i < n ; i++) {
            Random random = new Random();

            int s = random.nextInt(rangeR)%(rangeR-rangeL+1) + rangeL;
            array[i] = s;
        }
        return array;*/

        assert rangeL <= rangeR;
        int array[] = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int)(Math.random()*(rangeR-rangeL+1)+rangeL);
        }
        return array;
    }

    public static int[] generateRandomIntArray(int n, int rangeL, int rangeR){
        int[] array = new int[n];

        for (int i = 0; i < n ; i++) {
            Random random = new Random();

            int s = random.nextInt(rangeR)%(rangeR-rangeL+1) + rangeL;
            array[i] = new Integer(s);
        }
        return array;
    }

    //打印数组
    public static void printArray(Integer[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }

    /**
     * 计算算法运行时间
     * @param sortFlagInterface
     * @param array
     * @param n
     */
    public static  void calSortTime(SortFlagInterface sortFlagInterface, int array[], int n){
        long startTime = System.currentTimeMillis();
        sortFlagInterface.sort(array, n);
        long endTime = System.currentTimeMillis();
//        SortUtils.printArray(array);
        System.out.println("该排序算法运行时间为：" + (endTime-startTime));
    }

    /**
     *  判断数组是否已经排好序啦
     * @param array
     * @param n 数组内元素个数
     * @return
     */
    public static boolean isSorted(int[] array, int n){
        for (int i = 0; i < n-1; i++) {
            if (array[i] > array[i+1])
                return false;
        }
        return true;
    }
}
