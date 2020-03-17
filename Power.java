package algorithm;

import  java.math.*;
/**
 * @author yuqing Wang
 * @date 2020/3/12 10:28 AM
 *
 * 求某数的次方值
 **/
public class Power {

    private  boolean flag_invalidinput =false;

    public  double power(double base, int exponent){
        //exponent 0, <0, >0  base:0,<0,>0
        if(base == 0 && exponent<0){ flag_invalidinput =true;return  0.0;}//invalid
        if(base ==0 && exponent>=0){return 0.0;}else if(base!=0 && exponent==0 ){return  1.0;}
        double res = 1.0;
        if(base!=0 && exponent>0){
            for(int i=1;i<=exponent;i++){
                res = base*res;
                }

        }
        else
        if(base!=0 && exponent<0){
            res = 1.0;
            base = 1.0/base;
            for(int j=1;j<=-exponent;j++){
                res = base*res;
                //System.out.printf("base: %f ,j:%d,res: %f %n",base,j,res);
            }

        }
        //System.out.printf("fin res: %f %n",res);

        return res;

    }


    public double power2(double base,int exponent){
        //比上一种方法更简化，快速
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;

        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pow = power2(base * base, exponent / 2);//将base换成一半。除以2也可用>>右移运算。
        if (exponent % 2 != 0)//可和 0x1求 &
        { //System.out.printf(">>1  >>1, %d %d",4>>1,5>>1);
            //System.out.printf("& 0x1 %d, 0x1 %d",5 & 0x1,0x1);
            pow = pow * base;}//如果exponent为偶数，则直接为pow，如果为奇数，pow还需要乘以一次base
        return isNegative ? 1 / pow : pow;  //如果为负数，pow=1/pow



    }


}
