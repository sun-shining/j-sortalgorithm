package heap;

public class HeapSort2 {

    private HeapSort2(){};
    //先构建最大堆，再利用堆的性质依次取出对应元素并放入数组完成排序
    //与第一种的区别就是，在创建堆时，不是一个个插入，而是使用happify算法，直接构造堆
    public static void sort(Comparable[] array , int n ){

        MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(array, n);

        for (int i = n-1; i >= 0 ; i--) {
            array[i] = maxHeap.extractMax();
        }

    }
}
