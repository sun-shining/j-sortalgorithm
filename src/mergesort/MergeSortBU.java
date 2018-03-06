package mergesort;

public class MergeSortBU {
    
    public void mergeSortBU(int[] array, int n){

        for (int sz = 1; sz < n; sz+=sz) {
            for (int i = 0; i+sz < n; i++) {
                MergeSort.mergeTwoArray(array, i,i+sz-1, (i+sz+sz-1 > n-1 ? n-1 : i+sz+sz-1));
            }
        }
    }
}
