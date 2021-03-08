package leetcode;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StringMatch {

	public static int[] createPrefixTable(char[] pattern) {
		int len = 0;
		int[] prefix = new int[pattern.length];
		prefix[0] = 0;
		int i = 1;
		// 开始循环

		while (i < pattern.length - 1) {
			// 相等不等

			if (pattern[len] == pattern[i]) {
				len++;
				prefix[i] = len;
				i++;
			} else {
				if (len - 1 > 0) {
					len = prefix[len - 1];
				} else {
					prefix[i] = len;
					i++;
				}
			}
		}
		return prefix;

	}

	public static int countPatternRepeat(String pattern, String text, int[] prefix) {
		char[] p = pattern.toCharArray();
		char[] t = text.toCharArray();
		int lenp = p.length;
		int lent = t.length;
		int count = 0;

		if (lenp > lent)
			return 0;

		int i = 0, j = 0;
		// 每个text中的字符和pattern的开始比对
		while (i < lent) {
			// int j = 0;
			// while (j < lenp) {
			if (j == lenp - 1 && t[i] == p[j]) {
				count++;
				System.out.println();
				System.out.println(i - j);
				j = prefix[j];
			}

			if (t[i] == p[j]) {
				i++;
				j++;
			} else {
				j = prefix[j];
				if (j == -1) {
					i++;
					j++;
				}
				// }
			}
		}
		System.out.println(count);
		return count;

	}

	static String text;
	static String pattern;
	static InputReader in = new InputReader(System.in);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int len1;
	static int len2;
	static int[] next;

	public static void main(String[] args) {
		// Scanner sc=new Scanner(System.in);
//		text = "ababababcabaab";
//		pattern = "ababcabaa";

		String[] randomStr = randomTest(20, 4);
		text = randomStr[1];
		pattern = randomStr[0];

		int[] prefix = createPrefixTable(pattern.toCharArray());
		int lenp = pattern.length();
		int lent = text.length();
		prefix[0] = -1;

		for (int i = lenp - 2; i > 0; i--) {
			prefix[i + 1] = prefix[i];
		}

		for (int i : prefix) {
			System.out.print(i);
		}
		System.out.println();
		countPatternRepeat(pattern, text, prefix);

		// 根据prefix表，遍历比较pattern和text

//        text=in.next();
//        pattern=in.next();
//        
//        len1=text.length();
//        len2=pattern.length();
//        
//        int times=KMP();
//        System.out.println(times); 
//        
//        out.flush();
//        out.close();
	}

	public static String[] randomTest(int sizet,int sizep) {
		//int sizet = 20; int sizep = 4;
		StringBuilder text =new StringBuilder();
		StringBuilder pattern=new StringBuilder();
		
		for(int i = 0 ;i<sizet;i++)	{
			int a = (int) Math.round(Math.random()*25) + 97;
			text.append(String.valueOf((char)a));
		
		}
		for(int i = 0 ;i<sizep;i++)	{
			int a = (int) Math.round(Math.random()*25) + 97;
			pattern.append(String.valueOf((char)a));
		
		}	
		
		System.out.println(pattern);
		System.out.println(text);
		String[] res = new String[]{pattern.toString(),text.toString()};
		
		return res;
	}

	public static void CreateNextTable() {
		next = new int[10000];
		for (int i = 0; i < 10000; i++) {
			next[i] = 0;
		}
		int i = 0; // next下标
		next[i] = -1;
		int j = next[i]; // 模式串下标

		while (i < len2) {
			if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
				next[i] = j; // 匹配就更新
			} else {
				j = next[j]; // 不匹配就跳转
			}
		}
		return;
	}

	public static int KMP() {
		CreateNextTable(); // 创建next表

		int times = 0;
		int i = 0; // 模式串
		int j = 0; // 文本串

		while (j < len1) {
			if (i == -1 || pattern.charAt(i) == text.charAt(j)) {
				i++;
				j++;
			} else // 若不匹配
			{
				i = next[i];
			}
			if (i == len2) {
				times++;
				i = next[i]; // 默认回到下标0
			}
		}

		return times;
	}
}

class InputReader {
	private final static int BUF_SZ = 65536;
	BufferedReader in;
	StringTokenizer tokenizer;

	public InputReader(InputStream in) {
		super();
		this.in = new BufferedReader(new InputStreamReader(in), BUF_SZ);
		tokenizer = new StringTokenizer("");
	}

	public String next() {
		while (!tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(in.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}
}
