package leetcode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.smartcardio.CommandAPDU;

public class TwoPowerCalcu {

	/**
	 * @Authror Asimple
	 * 
	 * @Description 二次方程计算器
	 * @Date 2019-7-4 13:20
	 * com shift f 格式化代码
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while ((line = bf.readLine()) != null) {
			int a = 0, b = 0, c = 0, temp = 0;
			char[] strs = line.toCharArray();
			int len = strs.length;
			int left = 1, sign = 1;
			for (int i = 0; i < len; i++) {
				if (strs[i] == 'x') {
					if (temp == 0)
						temp = 1;
					if (i + 2 < len && strs[i + 1] == '^' && strs[i + 2] == '2') {
						a += temp * left * sign;
						i += 2;
					} else
						b += temp * left * sign;
					sign = 1;
					temp = 0;
				} else if (strs[i] == '+') {
					c += temp * sign * left;
					temp = 0;
					sign = 1;
				} else if (strs[i] == '-') {
					c += temp * sign * left;
					temp = 0;
					sign = -1;
				} else if (strs[i] == '=') {
					c += temp * sign * left;
					temp = 0;
					sign = 1;
					left = -1;
				} else
					temp = temp * 10 + Integer.valueOf(strs[i] - '0');
			}
			c += temp * left * sign;
			int num = b * b - 4 * a * c;
			if (num < 0)
				System.out.println("No Solution");
			else {
				double res = -b;
				double tt = Math.sqrt(num);
				double x1 = (res - tt) / (2.0 * a);
				double x2 = (res + tt) / (2.0 * a);
				System.out.printf("%.2f %.2f\n", Math.min(x1, x2), Math.max(x1, x2));
			}
		}
		bf.close();
	}

}
