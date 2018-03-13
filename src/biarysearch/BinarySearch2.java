package biarysearch;
//递归的二分查找法
public class BinarySearch2 {
    // 我们的算法类不允许产生任何实例
    private BinarySearch2() {}

    // 二分查找法,在有序数组arr中,查找target
    // 如果找到target,返回相应的索引index
    // 如果没有找到target,返回-1
    public static int sort(int[] array, int n, int target) {

        return sortRecursive(array, 0, n-1, target);
    }

    //递归
    private static int sortRecursive(int[] array, int l, int r, int target) {
        if (l > r)
            return -1;

        int mid = l + (r-l)/2;
        if (array[mid] == target) return mid;

        if (array[mid] < target) {
            return sortRecursive(array, l, mid-1, target);
        } else
            return sortRecursive(array, mid+1, r, target);

    }

    // 测试递归的二分查找算法
    public static void main(String[] args) {

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
    }
}
