package buddlesort;

import utils.SortFlagInterface;
import utils.SortUtils;

public class BuddleSort {

//    public static void sort(int[] array) {
//        int i,j,temp, n = array.length;
//        for (i = 0; i < n-1; i++) {
//            for (j = 0; j < n-1-i && array[j] > array[j+1]; j++) {
//                temp = array[j];
//                array[j] = array[j + 1];
//                array[j + 1] = temp;
//            }
//        }
//    }

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
