package heap;

import utils.SortUtils;

/**
 * O(nlogn)级别的排序
 */
public class HeapSort1 {

    private HeapSort1(){};

    //先构建最大堆，再利用堆的性质依次取出对应元素并放入数组完成排序
    public static void sort(Comparable[] array , int n ){
//        MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(n);
        MinHeap<Comparable> minHeap = new MinHeap<Comparable>(20);
        for (int i=0; i < n ; i++) {
            minHeap.insert(array[i]);
        }

        for (int i = 0; i < n; i++) {
            array[i] = minHeap.extractMin();
        }

    }

    public static void main(String[] args) {
        Integer arr[] = SortUtils.generateRandomItegerArray(10 , 1, 1000);

        sort(arr, 10);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
