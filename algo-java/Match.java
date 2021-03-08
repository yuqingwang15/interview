package algorithm;

/**
 * @author yuqing Wang
 * @date 2020/3/12 11:37 AM
 * 用正则匹配字符串
 **/
public class Match {

/*
* 当模式中的第二个字符不是“*”时：
1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。

而当模式中的第二个字符是“*”时：
如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
1、模式后移2字符，相当于x*被忽略；
2、字符串后移1字符，模式后移2字符；
3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；

*/
    /*****************方法一********************/

    public boolean match2(char[] str, char[] pattern)
    {
        return matchTwo(str,0,str.length,pattern,0,pattern.length);

    }
    private boolean matchTwo(char[] str, int i, int length1, char[] pattern,
                             int j, int length2) {
        if(i==length1 && j==length2) {
            return true;
        }
        if(i==length1&&j!=length2) {
            while(j!=length2){
                if(pattern[j]!='*'&&(j+1>=length2||pattern[j+1]!='*')){
                    return false;
                }
                j++;
            }
            return true;
        }
        if(i!=length1&&j==length2) {
            return false;
        }
        if(j+1==length2){
            if(str[i]==pattern[j]||pattern[j]=='.')
                return matchTwo(str, i+1, length1, pattern, j+1, length2);
            else {
                return false;
            }
        }
        if((str[i]==pattern[j]||pattern[j]=='.')&&pattern[j+1]!='*')
            return matchTwo(str, i+1, length1, pattern, j+1, length2);
        if((str[i]==pattern[j]||pattern[j]=='.')&&pattern[j+1]=='*')
            return matchTwo(str, i, length1, pattern, j+2, length2)||matchTwo(str, i+1, length1, pattern, j, length2);
        if(pattern[j+1]=='*')
            return matchTwo(str, i, length1, pattern, j+2, length2);
        return false;
    }

    /*****************方法二********************/

    public boolean match(char[] str, char[] pattern) {//aaa

        int m = str.length, n = pattern.length;

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++)//ab*c*.a  n=7 m=3
            if (pattern[i - 1] == '*') //pattern[0]==a,pattern[2]=*,pattern[4]=*
                dp[0][i] = dp[0][i - 2];//d[0][3] = d[0][1]  d[0][5] = d[0][3]

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1]; //d[1][1]=d[0][0]
                else if (pattern[j - 1] == '*')
                    if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                        dp[i][j] |= dp[i][j - 1]; // a* counts as single a
                        dp[i][j] |= dp[i - 1][j]; // a* counts as multiple a
                        dp[i][j] |= dp[i][j - 2]; // a* counts as empty
                    } else
                        dp[i][j] = dp[i][j - 2];   // a* only counts as empty

        return dp[m][n];
    }

}
