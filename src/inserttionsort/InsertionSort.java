package inserttionsort;

import utils.SortUtils;

public class InsertionSort {

    public static void insertionSort(int[] array, int n){
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--){
                if (array[j-1] > array[j]){
                    SortUtils.swap4Int(j-1, j, array);
                }
            }
        }
    }

    public static void main(String[] args) {
        int a[] = SortUtils.generateRandomIntArray(10, 0, 100);
        insertionSort(a, 10);
        SortUtils.printArray(a);
    }
}
