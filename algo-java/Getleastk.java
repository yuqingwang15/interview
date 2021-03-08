package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Getleastk {

    //快排，先分区再递归
    public ArrayList<Integer> returnKth(int[] input,int k){
        ArrayList<Integer> res = new  ArrayList<Integer>();
        int len = input.length;
        int b = 0;int e = len-1;
        if(k>len){return  null;}
        while(b<e){

            int point = partition(input,b,e);
            if(point ==k) break;
            if(point>k)
                e=point-1;
            else if(point<k)
                b=point+1;

        }


        for(int i =0;i<k;i++){
            res.add(i,input[i]);
        }
        return res;
    }


    //partition
    public  int partition(int[] input ,int b,int e){

        //小于基准，基准p，大于基准==>input[b,e]
        int i =b;int j =e; int p =input[b];

        while (true) {
            while (input[i] <= p) {
                i++;
            }
            while (input[j] > p) {
                j--;

            }
            if (i >= j)
                break;
            swap(input, i, j);

        }

        swap(input,b,j);
        return  j;
    }

    public void swap(int[] input, int i,int j){
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;

    }

    /*******************方法2************************/

    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] nums, int k) {
        if (k > nums.length || k <= 0)
            return new ArrayList();
        Comparator<Integer> c = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        };
        PriorityQueue<Integer> maxHeap = new PriorityQueue(15,c);
        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        return new ArrayList(maxHeap);
    }


}
