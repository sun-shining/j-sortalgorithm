package mergesort;

/**
 * 拉钩里讲解的解法
 *
 * @Author dasongju
 * @Date 2020/12/20 15:39
 */
public class NewMergeSort {

    public void mergeSort(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }
        sort2Sides(arrays, 0, arrays.length - 1);
    }

    private void sort2Sides(int[] arrays, int l, int r) {
        //判断是否剩下最后一个元素
        if (l >= r) {
            return;
        }
        //防止溢出
        int mid = l + (r - l) / 2;
        //分别递归地将左右两半排好序
        sort2Sides(arrays, l, mid);
        sort2Sides(arrays, mid + 1, r);
        //将排好序的左右两半边合并
        merge2Sides(arrays, l, mid, r);
    }

    private void merge2Sides(int[] arrays, int l, int mid, int r) {
        int[] clone = arrays.clone();

        int k = l, i = l, j = mid;
        while (k <= r) {
            if (i > mid) {
                arrays[k++] = clone[j++];
            } else if (j > r) {
                arrays[k++] = clone[i++];
            } else if (clone[i] > clone[j]) {
                arrays[k++] = clone[j++];
            } else if (clone[j] > clone[i]) {
                arrays[k++] = clone[i++];
            }
        }
    }

}
