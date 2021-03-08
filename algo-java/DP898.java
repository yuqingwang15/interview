import java.util.Set;

public class DP898 {

    public int subarrayBitwiseORs(int[] arr) {
        int len = arr.length;

        Set<Integer>[] dp = new Set[len];//以 第 i 个元素为结尾的 bitwise结果的集合

        dp[0].add(arr[0]);

        for(int i =0;i<len;i++){
            dp[i].add(arr[i]);
            for(int j:dp[i-1]){
                dp[i].add( j|arr[i] ) ;
        }

        return dp[-1].size();
    }

    public static void main(String[] args) {

        int max = 10;
        //max>>=1;
        System.out.println(1|3);


    }
}
