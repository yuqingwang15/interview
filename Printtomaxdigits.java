package algorithm;

/**
 * @author yuqing Wang
 * @date 2020/3/12 11:36 AM
 * 从1到最大数打印，最大数是指，输入n的n位最大值，如n=3，最大值是999
 **/
public class Printtomaxdigits {

    public void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }

    private void print1ToMaxOfNDigits(char[] number, int digit) {//两个难点，1停止条件到底为多少length 还是length-1，2递归的函数在哪里继续调用
        if (digit == number.length) {//digit从0开始，所以最大为length-1。当等与length的时候停止。
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);//末位的每一位进行递归返回 number
        }
    }

    private void printNumber(char[] number) {//从第一个非0 char开始打印
        int index = 0;
        while (index < number.length && number[index] == '0')//找到第一个不是0的index
            index++;
        while (index < number.length)
            System.out.print(number[index++]);
        System.out.println();
    }

//    public static void main(String args[]) {
//    algorithm.Printtomaxdigits ptd = new algorithm.Printtomaxdigits();
//    ptd.print1ToMaxOfNDigits(2);
//
//}


}

