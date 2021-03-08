public class DP730 {

    public int countPalindromicSubsequences(String S) {


        int mod = 1_000_000_000 + 7;
        int len = S.length();
        int[][] dp = new int[len][len];

        //dp存放 从i 到 j的子串的回文值
        //if s[i] != s[j]:
//    dp[i][j] = dp[i + 1][j] + dp[i][j-1] - dp[i + 1][j - 1]

//if s[i] == s[j]:
//    dp[i][j] = dp[i+1][j-1] * 2 + 1  //中间这个数就是两端的数 类似 “bcbcb”
//             = dp[i+1][j-1] * 2 + 2 //中间有两个数类似 “bcab”
//             = dp[i+1][j-1] * 2 - dp[l + 1][r - 1] `
//        int n=S.length();
//        int mod=1000000000+7;
//        int[][] dp=new int[n+3][n+3];
//        for (int i=0;i<n;i++) dp[i][i]=1;
//        char[] s=S.toCharArray();
//
//        for (int i=n-2;i>=0;i--){
//            for (int j=i+1;j<n;j++){
//                if (s[i]==s[j]){
//                    int left=i+1,right=j-1;
//                    while (left<=right&&s[left]!=s[i]) left++;
//                    while (right>=left&&s[right]!=s[j]) right--;
//                    if (left>right) dp[i][j]=dp[i+1][j-1]*2+2;
//                    else if (left==right) dp[i][j]=dp[i+1][j-1]*2+1;
//                    else dp[i][j]=dp[i+1][j-1]*2-dp[left+1][right-1];
//                }
//                else
//                    dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1];
//                dp[i][j]=(dp[i][j]<0)?dp[i][j]+mod:dp[i][j]%mod;
//            }
//        }
//        return dp[0][n-1];

        for (int i = 0; i < len; i++) dp[i][i] = 1;


        for (int i = len - 2; i >= 0; i--) {
            for (int j = i+1; j < len; j++) {

                char si = (char) S.charAt(i);

                if (si != S.charAt(j)) {

                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];

                } else {
                    char c = S.charAt(i);
                    int left = i + 1;
                    int right = j - 1;
                    while (left <=right && S.charAt(left) != c) {
                        left++;
                    }
                    while (right >=left && S.charAt(right) != c) {
                        right--;
                    }
                    if (left == right) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                    } else if (left > right) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    }

                }

                dp[i][j] = (dp[i][j] + mod) % mod;


            }
        }
        return dp[0][len - 1];

    }

    public static void main(String[] args) {
        String S = "bccb";
        char[] s = S.toCharArray();
        System.out.println("s[2] = " + s[2]);
        System.out.println("S.charAt(2) = " + S.charAt(2));
    }
}