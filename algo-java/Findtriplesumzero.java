package algorithm;

import java.util.ArrayList;

public class Findtriplesumzero {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {

        //从第一个a开始遍历。后面的找到两个数和为 -a，且另外的数字必须a<=
        //所有的triple找到后，删除重复的
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        int count =0;
        for(int a=0;a<num.length;a++){
            int a_1 = num[a];
            for (int b = a+1;b<num.length;b++){
                int b_2= num[b];
                for(int c=b+1;c<num.length;c++){
                    if(a_1+b_2+num[c]==0){

                        ArrayList<Integer> tmp = new ArrayList<Integer>();//这里折腾了，如果不new则后面的add会自动append
                        tmp.add(0,a_1);tmp.add(1,b_2);tmp.add(2,num[c]);
                        result.add(count,tmp);
                        count=count+1;
                            }

                        }
                    }
                }



        ArrayList<ArrayList<Integer>> result2 = new ArrayList<ArrayList<Integer>>();
        int count2=0;
        for(int r = 0;r<result.size();r++){
            for(int n =r+1;n<result.size();n++){
                if(!compare(result.get(r),result.get(n)))//不一样
                {

                    result2.add(count2,result.get(r));
                    count2=count2+1;
                }
            }
        }

        return  result2;



    }

    public  boolean compare(ArrayList<Integer> a, ArrayList<Integer> b){

        if(a.contains(b.get(0)) && a.contains(b.get(1))  ){


            return true;
        }
        return  false;
    }

    public void sort(ArrayList<Integer> a){



    }


//    public static void main(String args[]){
//
//        algorithm.Findtriplesumzero fn = new algorithm.Findtriplesumzero();
//        int[] input={-1,0,1,2,-1,-4};
//        ArrayList<ArrayList<Integer>> res1 = fn.threeSum(input);
//
//    }
}
