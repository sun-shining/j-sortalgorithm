package heap;

/**
 * O(nlogn)级别的排序
 */
public class HeapSort1 {

    private HeapSort1(){};

    //先构建最大堆，再利用堆的性质依次取出对应元素并放入数组完成排序
    public static void sort(Comparable[] array , int n ){
        MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(n);

        for (int i=0; i < n ; i++) {
            maxHeap.insert(array[i]);
        }

        for (int i = n-1; i >= 0; i--) {
            array[i] = maxHeap.extractMax();
        }
    }


}
