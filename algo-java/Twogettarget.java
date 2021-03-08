package algorithm;

import java.util.*;

/**
 * @author yuqing Wang
 * @date 2020/3/11 5:52 PM
 * 有序数组中找到两个数等于要求的n
 **/


public class Twogettarget{
//    public algorithm.Twogettarget() {
//    }

    public int[] sumTwo(int sum, int[] arr) {

        //时间复杂度O(n)，考虑快排思想。两个指针分别指向头尾。和小于target则首指针往后走，大于则尾指针往前走。
        //相等时停止,首尾指针相等时停止。当不存在两个这样的数字时，返回(-1,-1)。
        int[] res = {-1, -1};

        if(arr.length<=1 || sum>2*arr[arr.length-1] || sum<2*arr[0]){
            return res;
        }

        int low = 0, high = arr.length - 1;
        int temp = arr[low] + arr[high];

        while (low < high && temp != sum) {
            if (temp < sum) {
                low++;
                temp = arr[low] + arr[high];
            }
            if (temp > sum) {
                high--;
                temp = arr[low] + arr[high];
            }
        }
        if (temp == sum) {
            res[0]=low;res[1]=high;
            return res;
        } else {
            return res;
        }

    }
}
