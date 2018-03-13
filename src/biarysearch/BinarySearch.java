package biarysearch;
//非递归的二分查找法，前提是需要该数组是有序的
public class BinarySearch {
    // 我们的算法类不允许产生任何实例
    private BinarySearch() {}

    // 二分查找法,在有序数组arr中,查找target
    // 如果找到target,返回相应的索引index
    // 如果没有找到target,返回-1
    public static int sort(int[] array, int n, int target) {

        //在array[l...r]区间内寻找target
        int l=0, r=n-1;

        while (l <= r) {
//            int mid = (l+r)/2; 假如l和r都是很大的数，有可能mid越界
            int mid = l + (r-l)/2;

            if (array[mid] == target)
                return mid;

            if (array[mid] > target)
                //继续考察array[mid+1...r]范围内是否存在target
                l = mid +1;
            else {
                //继续考察array[l...mid-1]范围内是否存在target
                r = mid -1;
            }
        }

        return -1;
    }

    // 测试非递归的二分查找算法
    /*public static void main(String[] args) {

        int N = 1000000;
        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = i;

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for(int i = 0 ; i < 2*N ; i ++) {
            int v = BinarySearch2.sort(arr, N, i);
            if (i < N)
                assert v == i;
            else
                assert v == -1;
        }

        return;
    }*/
}
