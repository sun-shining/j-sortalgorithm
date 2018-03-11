package heap;

/**
 * 最大堆
 */
public class MaxHeap<Item extends Comparable> {

    protected Item[] data;
    protected int count;
    protected int capacity;

    public MaxHeap(int capacity){
        data = (Item[])new Comparable[capacity+1];
        this.count = 0;
        this.capacity = capacity;
    }

    //happify:指直接将整个数组值复制到堆中，再一次性调整元素的位置
    //从第一个非叶子节点（即没有孩子的节点，为count/2位置）开始就行shirtDown操作即可，
    //到根节点下一层，即第一层即可停止
    public MaxHeap(Item[] array, int n) {
        data = (Item[]) new Comparable[n+1];
        capacity = n+1;

        for (int i = 0; i < n; i++) {
            data[i+1] = array[i];
        }

        count = n;

        //i为什么到1就可以啦，因为shiftDown会和根节点的元素进行比较的。
        for (int i = count/2; i >=1; i--) {
            shiftDown(i);
        }
    }


    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void insert(Item item){
        assert (count+1 <= capacity);

        this.data[count + 1] = item;
        count++;
        shiftUP( count );
    }

    //从堆中取出最大的元素
    public Item extractMax() {
        assert count > 0;

        Item res = data[1];
        data[1] = data[count];
        count--;
        shiftDown( 1 );

        return res;
    }

    //向下移动元素，使堆仍保持堆的性质
    private void shiftDown(int k) {
        //2*K表示k的左孩子，如果有左孩子，表明没到叶子节点所在的层
        while ( 2*k <= count) {
            int j = 2*k;
            //比较两个子节点大小，谁大，j就代表谁
            if (j+1 <= count && data[j].compareTo(data[j+1]) < 0) {
                j++;
            }

            //如果k位置元素比子节点中较大元素还大，则跳出循环，什么都不做
            if (data[k].compareTo(data[j]) >= 0) break;

            swap(j, k);
            k = j;

        }

    }

    private void swap(int i , int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    //向上移动元素，使堆扔保持堆的性质
    private void shiftUP(int k) {
        while(k > 1 && data[k/2].compareTo(data[k]) < 0) {
            swap(k/2, k);
            k = k/2;
        }
    }

    public Item getMax() {
        assert (count >0 );
        return data[1];
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
        int N = 50;
        int M = 100;
        for (int i = 0; i < N; i++) {
            maxHeap.insert(new Integer((int)Math.random()*M));
        }
        System.out.println(maxHeap.size());
    }
}
