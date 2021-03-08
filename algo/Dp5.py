

class Solution:

    def longestPalindrome4(self, s: str) -> str:
        if not s: return ""
        length = len(s)
        if length == 1 or s == s[::-1]: return s
        max_len, start = 1, 0
        print(s,length)

        for i in range(1, length):
            even = s[i - max_len:i + 1]
            odd = s[i - max_len - 1:i + 1]
            print(i)
            print(even)
            print(odd)
            print(max_len,odd[::-1])
            if i - max_len - 1 >= 0 and odd == odd[::-1]:
                start = i - max_len - 1
                max_len += 2
                print("odd")
                continue
            if i - max_len >= 0 and even == even[::-1]:
                start = i - max_len
                max_len += 1
                print("even")
                continue
            print("====")
        print(s[start:start + max_len])
        return s[start:start + max_len]

    def longestPalindrome2(self, s: str) -> str:
        size = len(s)
        if size < 2:
            return s

        # 至少是 1
        max_len = 1
        res = s[0]

        for i in range(size):
            palindrome_odd, odd_len = self.__center_spread(s, size, i, i)
            palindrome_even, even_len = self.__center_spread(s, size, i, i + 1)

            # 当前找到的最长回文子串
            cur_max_sub = palindrome_odd if odd_len >= even_len else palindrome_even
            if len(cur_max_sub) > max_len:
                max_len = len(cur_max_sub)
                res = cur_max_sub

        return res

    def __center_spread(self, s, size, left, right):
        """
        left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
        """
        i = left
        j = right

        while i >= 0 and j < size and s[i] == s[j]:
            i -= 1
            j += 1
        return s[i + 1:j], j - i - 1

    def longestPalindrome3_lamache(self, s: str) -> str:
        # 特判
        size = len(s)
        if size < 2:
            return s

        # 得到预处理字符串
        t = "#"
        for i in range(size):
            t += s[i]
            t += "#"

        print('t',t)
        # 新字符串的长度
        t_len = 2 * size + 1

        # 数组 p 记录了扫描过的回文子串的信息
        p = [0 for _ in range(t_len)]

        # 双指针，它们是一一对应的，须同时更新
        max_right = 0
        center = 0

        # 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        max_len = 1
        # 原始字符串的最长回文子串的起始位置，与 max_len 必须同时更新
        start = 1

        for i in range(t_len):
            if i < max_right:
                mirror = 2 * center - i
                # 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                p[i] = min(max_right - i, p[mirror])

            # 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            left = i - (1 + p[i])
            right = i + (1 + p[i])

            # left >= 0 and right < t_len 保证不越界
            # t[left] == t[right] 表示可以扩散 1 次
            while left >= 0 and right < t_len and t[left] == t[right]:
                p[i] += 1
                left -= 1
                right += 1

            # 根据 max_right 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            # 如果 max_right 的值越大，进入上面 i < max_right 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if i + p[i] > max_right:
                # max_right 和 center 需要同时更新
                max_right = i + p[i]
                center = i

            if p[i] > max_len:
                # 记录最长回文子串的长度和相应它在原始字符串中的起点
                max_len = p[i]
                start = (i - max_len) // 2

            print(start,left,right,max_len,max_right,center,i)
        return s[start: start + max_len]

    def longestPalindrome(self, s: str) -> str:
        size = len(s)
        if size < 2:
            return s

        dp = [[False for _ in range(size)] for _ in range(size)]

        max_len = 1
        start = 0

        for i in range(size):
            dp[i][i] = True

        for j in range(1, size):
            for i in range(0, j):
                if s[i] == s[j]:
                    if j - i < 3:
                        dp[i][j] = True
                    else:
                        dp[i][j] = dp[i + 1][j - 1]
                else:
                    dp[i][j] = False

                if dp[i][j]:
                    cur_len = j - i + 1
                    if cur_len > max_len:
                        max_len = cur_len
                        start = i
        return s[start:start + max_len]


if __name__ == '__main__':
    s = Solution()
    s.longestPalindrome4("abcbabbacb")


