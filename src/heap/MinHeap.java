package heap;

//经典的堆的实现，数组下标是从1开始的
//堆的性质就是，父节点与子节点之间有大小关系
public class MinHeap<Item extends Comparable> {

    protected Item[] data;
    protected int count;//元素个数
    protected int capacity;//容量

    public MinHeap(int capacity){
        this.data = (Item[])new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    public void insert(Item item) {
        this.data[count+1] = item;
        count++;

        shiftUP(count);
    }

    public Item getMin(){
        assert (count>=1);
        return data[1];//注意，数组下标是从1开始
    }

    public Item extractMin (){
        assert (count > 0);

        Item res = data[1];

//        swap(1, count); 此处不需要交换位置，只需赋值即可，因为取出最小元素后
        //count表示的元素个数会-1，则数组使用下标是访问不到原来count在的位置的
        data[1] = data[count];
        count--;
        shiftDown(1);
        return res;

    }

    //向下移动元素，假如左孩子比右孩子大，使j代表右孩子，再和k比，假如
    //k比两个孩子中较小的那个还要小，则break掉循环，已经满足堆的性质了
    //否则，交换k和j的位置，并将k的值赋值成j，循环继续
    private void shiftDown(int k) {
//        assert (count > 1);
        while ( 2*k <= count) {//说明2k不是叶子节点，还可以继续往下
            int j = k*2;//左孩子 每次都要使j的值使最新的k的左孩子，所以要放循环里，每次跟新j的值
            if (data[j].compareTo(data[j+1]) > 0)
                j++;

            if (data[k].compareTo(data[j]) < 0)
                break;

            swap(k, j);
            k = j;

        }
    }


    //为了使堆保持性质，就需要对新加入的元素进行挪动，找到合适的位置
    private void shiftUP(int k ) {

        while ( k > 1 && data[k/2].compareTo(data[k])>0) {
            swap(k, k/2);
            k = k/2;
        }
    }

    private void swap(int i , int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
