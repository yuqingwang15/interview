package algorithm;

import  java.lang.Integer;
/**
 * @author yuqing Wang
 * @date 2020/3/12 9:17 AM
 *
 * 二进制计算1的个数，两种，一种利用位运算，一种利用api bitCount
 *
 *
 * 左移，补〇；右移，正数补〇，负数补1。异或相同为0，不同为1。
 *
 * 正数原码表示，负数补码表示。
 *
 * 反码：正数的反码与原码相同，负数的反码为对该数的原码除符号位外各位取反
 *
 * 补码：正数的补码与原码相同；负数的补码为对该数的原码除符号位外各位取反，然后在最后一位加1.
 **/
public class Countone {

    public int countnumOf1(int n ){

        int c = 0;
        while(n!=0) {
            c++;
            n = n & (n - 1);
        }
        return  c;

    }
    public int countnumOf1method2 (int n ){

        return  Integer.bitCount(n);
    }




}
