package leetcode;

import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;

public class DisjointSet1697 {


	    int N = 100005;
	    int[] p = new int[N];
	    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
	        boolean[] res = new boolean[queries.length];
	        Pair[] pair = new Pair[queries.length];
	        // 添加一维index
	        for(int i = 0; i < queries.length; i++) {
	            pair[i] = new Pair(queries[i][0], queries[i][1], queries[i][2], i);
	        }
	        // queries 和 edgeList 分别按照边长从小到大排序
	        Arrays.sort(pair, (o1, o2) -> (o1.l - o2.l));
	        Arrays.sort(edgeList, (o1, o2) -> (o1[2] - o2[2]));
	        // 并查集初始化
	        for(int i = 1; i < N; i++) {
	            p[i] = i;
	        }
	        int i = 0;
	        for(Pair query : pair) {
	            int a = query.a;
	            int b = query.b;
	            int l = query.l;
	            int index = query.index;
	            for(; i < edgeList.length; i++) {
	                if(edgeList[i][2] < l) {
	                    p[find(edgeList[i][0])] = find(edgeList[i][1]);//合并
	                }else {
	                    break;
	                }
	            }
	            //查a和b是否在同一个连通块中
	            res[index] = (find(a) == find(b));
	        }
	        return res;
	    }
	    public int find(int x) {
	        if(p[x] != x) {
	            p[x] = find(p[x]);
	        }
	        return p[x];
	    }
	}
	 class Pair {
	    int a, b, l, index;
	    public Pair(int a, int b, int l, int index) {
	        this.a = a;
	        this.b = b;
	        this.l = l;
	        this.index = index;
	    }
	


}
