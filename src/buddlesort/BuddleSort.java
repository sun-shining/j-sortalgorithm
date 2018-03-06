package buddlesort;

import utils.SortFlagInterface;
import utils.SortUtils;

public class BuddleSort {

    /**
     * 这种写法错在，当前两个元素有序后，后面的排序因为循环第二个条件永远为false，一直不进循环体
     * for循环条件不满足，循环就结束了
     * @param array
     */
    public static void sort2(int[] array) {
        int i,j,temp, n = array.length;
        for (i = 0; i < n-1; i++) {
            for (j = 0; j < n-1-i && array[j] > array[j+1]; j++) {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }

    public static void sort(int[] array) {
        int i,j,temp, n = array.length;
        for (i = 0; i < n-1; i++) {
            for (j = 0; j < n-1-i ; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
        }
    }

    public static void bubble_sort(int[] arr) {
        int i, j, temp, len = arr.length;
        for (i = 0; i < len - 1; i++)
            for (j = 0; j < len - 1 - i; j++)
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void main(String[] args) {
        int array[] = SortUtils.generateRandomIntArray(10,0,100);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
//        BuddleSort.bubble_sort(array);
        BuddleSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
//        SortUtils.calSortTime(new BuddleSort(), array,10);

    }
}
