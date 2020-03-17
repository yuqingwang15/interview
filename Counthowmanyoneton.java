package algorithm;

public class Counthowmanyoneton {

    //https://www.nowcoder.com/questionTerminal/bd7f978302044eee894445e244c7eee6
    public  int countDigitOne(int n) {
        int ones = 0;

        for (int m = 1; m <= n; m *= 10) {
            int a = n/m;
            int b = n%m;
            int c =0;
            if(a % 10 == 1) c=1;
            ones += (a + 8) / 10 * m + c * (b + 1);
        }
        return ones;
    }

}
