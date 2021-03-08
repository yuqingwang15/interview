# s[i] != s[j]:
#
# count("bc") = count("b") + count("c") - count("") = 1 + 1 - 0 = 2
# count("bcc") = count("bc") + count("cc") - count("c") = 2 + 2 - 1 = 3
# count("ab") = count("a") + count("b") - count("") = 1 + 1 - 0 = 2
# count("abc") = count("ab") + count("bc") - count("b") = 2 + 2 - 1 = 3
# count("abcc") = count("abc") + count("bcc") - count("bc") = 3 + 3 - 2 = 4
# count("abccb") = count("abcc") + count("bccb") - count("bcc") = 4 + 6 - 3 = 7
# s[i] == s[j]:
#
# count("cbc") = 2 * count("b") + 2 = 4
# count("bcab") = 2 * count("ca") + 2 = 6
#
# count("bcbcb") = 2 * count("cbc") + 1 = 2 * 4 + 1 = 9
# count("bbcabb") = 2 * count("bcab") - count("ca") = 10
# 转移方程
#
# if s[i] != s[j]:
#     dp[i][j] = dp[i + 1][j] + dp[i][j-1] - dp[i + 1][j - 1]
#
# if s[i] == s[j]:
#     dp[i][j] = dp[i+1][j-1] * 2 + 1  # 中间这个数就是两端的数 类似 “bcbcb”
#              = dp[i+1][j-1] * 2 + 2 # 中间有两个数类似 “bcab”
#              = dp[i+1][j-1] * 2 - dp[l + 1][r - 1]
# 状态转移
#
# 因为dp[i][j]的状态和dp[i+1][j],dp[i][j-1],dp[i+1][j-1]有关，所以，i采用从len-1到0的方式，j采用从右到左的方式遍历。类似lcs。
class Solution(object):
    def countPalindromicSubsequences(self, S):
        """
        :type S: str
        :rtype: int
        """
        s = S
        n = len(s)
        if n <= 1:
            return n
        res = [[0] * n for _ in range(n)]
        for le in range(1, n + 1):
            for i in range(n + 1 - le):
                j = i + le - 1
                if i == j:
                    res[i][j] = 1
                    continue
                if s[i] != s[j]:
                    res[i][j] = res[i + 1][j] + res[i][j - 1] - res[i + 1][j - 1]
                else:
                    if i + 1 == j:
                        res[i][j] = 2
                        continue
                    ns = s[i + 1:j]
                    if s[i] not in ns:
                        res[i][j] = 2 * res[i + 1][j - 1] + 2
                    else:
                        left = s.find(s[i], i + 1, j)  # 找到左边最先相等的a下标
                        right = s.rfind(s[i], i + 1, j)  # 找到右边最先相等的a下标
                        if left == right:
                            res[i][j] = 2 * res[i + 1][j - 1] + 1
                        else:
                            res[i][j] = 2 * res[i + 1][j - 1] - res[left + 1][right - 1]
        return res[0][-1] % (10 ** 9 + 7)
    def countPalindromicSubsequences2(self, S):
        N = len(S)
        MOD = 1000000007

        # nxt记录在当前字符之后，截止当前的j之前，一共有多少个回文串；例如：bccb中j=2时，当前nxt[0]=2，因为在第1个b之后有2个回文串c和cc

        # [i+1,j]之间的回文串的数量--更新的策略是：

        nxt = [0] * N
        # use用于去重，记录当前字符之后的回文串中，已被计入到ans中的回文串有多少个；例如：bccb中j=3时，use[0]=3，第1个b之后有3个回文串c、cc和b，但都已经被最后一个b收获掉了
        use = [0] * N
        # nxt[i]-use[i] : 当前可以收获的数量
        # “收获”可理解为：从i+1开始，还有多少个回文串没有被前后两个S[i]嵌套；也就是当一个新的S[j]=S[i]后，可以通过前后嵌套的S[i]和S[j]来新增的回文串数量

        ans = 0
        for j in range(N):
            # 新字符直接带来的仅包含当前字符的回文串，例如b->b；bc->c；bcc->cc；bccb->bb
            x = 1

            for i in range(j - 1, -1, -1):
                # 字符S[i]等于新增字符S[j]，则收获S[i]字符之后未收获的回文串
                if S[i] == S[j]:
                    # x : 此时的x是当前字符S[i]之后因为当前新增字符S[j]而新增的回文串数量

                    # now_nxt : 当前字符S[i]后可收获的回文串数量
                    now_nxt = nxt[i]

                    # now_use : 当前字符S[i]后已收获的回文串数量
                    now_use = use[i]

                    # 将新增的回文串数量添加到S[i]中，这些回文串是因为新增字符才新增的，所以并不能在这次被直接收获，需要等待下一个相同的字符
                    nxt[i] += x

                    # 计算当前可以收获的新增回文串数量：可收获的数量-已收获的数量
                    # 对于相同的字符来说，靠前的字符中记录的回文串数量，一定已经包含靠后的字符中记录的回文串数量（不使用+=的原因）
                    x = now_nxt - now_use + 1

                    # 将这一次收获的回文串回文串数量累计到已收获的回文串数量之中
                    use[i] = now_nxt + 1

                # 字符S[i]不等于新增字符S[j]，新增字符不能收获新的回文串，但是对于i来说，它后面又新增了x个回文串，以备之后收获
                else:
                    nxt[i] += x

            # 记录当前字符新增的回文串数
            ans += x
        return ans % MOD
    def countPalindromicSubsequences3(self, S):
        MOD = 10 ** 9 + 7
        N = len(S)
  #以 k对应的ch 开头的， i到j的字符串的 回文子串的个数

        dp = [[[0] * N for _ in range(N)] for _ in range(4)]  # 状态表格

        for i in range(N - 1, -1, -1):
            for j in range(i, N):
                for k in range(4):
                    ch = ["a", "b", "c", "d"][k]
                    if i == j:
                        if S[i] == ch:
                            # 转移方程：DP[x][i][j] = 1（i=j且S[i]=ch）->即只有一个字符的字符串，包含本身一个以目标字符开头的回文串
                            dp[k][i][j] = 1
                        # 转移方程：DP[x][i][j] = 0（i=j且S[i]!=ch）->即只有一个字符的字符串，没有以目标字符开头的回文串（因默认为0不再设置）
                    else:  # 此时：i<j
                        if S[i] != ch:
                            # 转移方程：DP[x][i][j] = DP[k][i+1][j]（i<j且S[i]!=ch）->因为不是以目标字符开头的，所以以目标字符开头的回文串数量与跳过开头字符的情况相同
                            dp[k][i][j] = dp[k][i + 1][j]
                        elif S[j] != ch:
                            # 转移方程：DP[x][i][j] = DP[k][i+1][j]（i<j且S[j]!=ch）->因为新增字符不是目标字符，所以以目标字符开头的回文串数量与跳过最后一个字符的情况相同
                            dp[k][i][j] = dp[k][i][j - 1]
                        else:  # 此时：i<j且S[i]==S[j]==ch
                            if j == i + 1:
                                # 转移方程：DP[x][i][j] = 2（j=i+1且S[i]=S[j]=ch）->即有两个相同字符的字符串（aa），包含两个非空回文串（a）和（aa）
                                dp[k][i][j] = 2
                            else:  # 此时：i<j+1且S[i]==S[j]==ch
                                # 转移方程：DP[x][i][j] = 2+DP[0][i+1][j-1]+DP[1][i+1][j-1]+DP[2][i+1][j-1]+DP[3][i+1][j-1]
                                # 新增首尾两个字符自行组成的两种情况；以及内部所有回文串增加了嵌套于两个目标字符之间的新回文串
                                dp[k][i][j] = 2  # 通过首尾两个字符（aa）自行组成的回文串（a）和（aa）
                                for m in range(4):  # 累加首尾两个字符内部包含的回文串数量->内部所有的回文串均增加了嵌套于两个目标字符之间的新回文串
                                    dp[k][i][j] += dp[m][i + 1][j - 1]
                                    dp[k][i][j] %= MOD

        ans = 0
        for k in range(4):  # 遍历以每个字符开头的情况
            ans += dp[k][0][N - 1]  # 以目标字符开头的，从头到尾的完整字符串
            ans %= MOD
        return ans



if __name__ == '__main__':
    s = Solution()
    s.countPalindromicSubsequences2("babcab")