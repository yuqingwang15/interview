package algorithm;

import static java.lang.Math.pow;

/**
 * @author yuqing Wang
 * @date 2020/3/12 9:17 AM
 * 剪绳子，乘积最大，一种为贪心一种为动态规划
 **/

public class Cutropes {

    public int maxMulti1(int n ){

        if(n<2){return 0;}else if(n==2){return  1;}else if(n==3){return 2;}
        int timesof3 = n/3;

        //如果余数是1
        if(n - timesof3*3==1){timesof3=timesof3-1;}
        int timesof2 = (n- timesof3*3)/2;//余数是2，则为1
        return (int)(pow(3,timesof3))*(int)(pow(2,timesof2));//如果余数是1，那么3+1的改成2*2，需要×4，此时timesof2是2
        //如果余数是2，那么直接×1，此时timesof2是1.

    }

    public int maxMulti2(int n){

        if(n<2){return 0;}else if(n==2){return  1;}else if(n==3){return 2;}

        int[] arr =new int[n+1];arr[0]=0;arr[1]=1;arr[2]=2;arr[3]=3;//注意1这里的长度为n+1，2这里和上面if的return不同！
        int max = 1;
        for(int i =4;i<=n;i++){
            max =1; //max需要及时更新
            for(int j =1;j<=i;j++){

                arr[i] = arr[j]*arr[i-j];
                if(max<arr[i]){max = arr[i];}

            }
            arr[i] = max;
            System.out.printf("arr[i]: %d %d %n", arr[i],i);
        }


        return arr[n];

    }



}
