package buddlesort;

import java.util.Scanner;
import utils.SortFlagInterface;
import utils.SortUtils;

public class BuddleSort {

    /**
     * 这种写法错在，当前两个元素有序后，后面的排序因为循环第二个条件永远为false，一直不进循环体 for循环条件不满足，循环就结束了
     */
    public static void sort2(int[] array) {
        int i, j, temp, n = array.length;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - 1 - i && array[j] > array[j + 1]; j++) {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }

    public static void sort(int[] array) {
        int i, j, temp, n = array.length;
        if (n <= 1) {
            return;
        }
        //如果看到有些i<n 的，其实也无所谓，因为j需要比的最后两个元素就是j=0、1的时候，当j=1时i=n-2就足矣，再小其实也是浪费
        for (i = 0; i < n - 1; i++) {
            //提前退出标志
            boolean flag = false;
            for (j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    //有数据交换则标识为true
                    flag = true;
                }
            }
            //无数据交换可以提前退出
            if (!flag) {
                break;
            }
        }
    }

    public static void bubble_sort(int[] arr) {
        int i, j, temp, len = arr.length;
        for (i = 0; i < len - 1; i++) {
            for (j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int array[] = SortUtils.generateRandomIntArray(10,0,100);
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + "  ");
//        }
//        System.out.println();
////        BuddleSort.bubble_sort(array);
//        BuddleSort.sort(array);
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + "  ");
//        }
//        SortUtils.calSortTime(new BuddleSort(), array,10);

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        StringBuilder[] strs = new StringBuilder[n];
        for (int i=0;i<n;i++){
            strs[i] = new StringBuilder(in.next());
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<=strs[i].length()-4;){
                if (strs[i].charAt(j) == strs[i].charAt(j+1)){         //AA
                    if (strs[i].charAt(j+1) == strs[i].charAt(j+2) ||
                        strs[i].charAt(j+2) == strs[i].charAt(j+3)){    //AAAX或者AABB
                        strs[i].deleteCharAt(j+2);
                        continue;
                    }
                }else{
                    if (strs[i].charAt(j+1) == strs[i].charAt(j+2) &&
                        strs[i].charAt(j+2) == strs[i].charAt(j+3)){      //XAAA
                        strs[i].deleteCharAt(j+2);
                        continue;
                    }
                }
                j++;
            }
        }
        for (int i=0;i<n;i++)
            System.out.println(strs[i].toString());
    }

    public static String del(String str, int n) {
        return str.substring(0, n) + str.substring(n + 1);
    }
}
