package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Printpermutationofstring {

    /********************方法1**********************/

    /* 对于无重复值的情况
     *
             * 固定第一个字符，递归取得首位后面的各种字符串组合；
            * 再把第一个字符与后面每一个字符交换，并同样递归获得首位后面的字符串组合； *递归的出口，就是只剩一个字符的时候，递归的循环过程，就是从每个子串的第二个字符开始依次与第一个字符交换，然后继续处理子串。
            *
            * 假如有重复值呢？
            * *由于全排列就是从第一个数字起，每个数分别与它后面的数字交换，我们先尝试加个这样的判断——如果一个数与后面的数字相同那么这两个数就不交换了。
            * 例如abb，第一个数与后面两个数交换得bab，bba。然后abb中第二个数和第三个数相同，就不用交换了。
            * 但是对bab，第二个数和第三个数不 同，则需要交换，得到bba。
            * 由于这里的bba和开始第一个数与第三个数交换的结果相同了，因此这个方法不行。
            *
            * 换种思维，对abb，第一个数a与第二个数b交换得到bab，然后考虑第一个数与第三个数交换，此时由于第三个数等于第二个数，
            * 所以第一个数就不再用与第三个数交换了。再考虑bab，它的第二个数与第三个数交换可以解决bba。此时全排列生成完毕！
            *
            */

    public ArrayList<String> Permutation(String str)
    {
        ArrayList<String> res=new ArrayList<String>();
        if(str.length()==0||str==null)return res;
        int n= str.length();
        helper(res,0,str.toCharArray());
        Collections.sort(res);
        return res;

    }
    public void helper( ArrayList<String> res,int index,char []s)
    {
        if(index==s.length-1) res.add(new String(s));
        for(int i=index;i<s.length;i++)
        {
            if(i==index||s[index]!=s[i])//当和后面的字母不同时进行交换
            {
                swap(s,index,i);
                helper(res,index+1,s);
                swap(s,index,i);
            }
        }

    }

    public void swap(char[]t,int i,int j)
    {
        char c=t[i];
        t[i]=t[j];
        t[j]=c;
    }

    /********************方法2**********************/

    private ArrayList<String> ret = new ArrayList();

    public ArrayList<String> Permutation2(String str) {
        if (str.length() == 0)
            return ret;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuilder());
        return ret;
    }

    private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
        if (s.length() == chars.length) {
            ret.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i])
                continue;
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) /* 保证不重复 */
                continue;
            hasUsed[i] = true;
            s.append(chars[i]);
            backtracking(chars, hasUsed, s);
            s.deleteCharAt(s.length() - 1);
            hasUsed[i] = false;
        }
    }

}
