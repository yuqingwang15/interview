package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS1293 {

	public static int shortestPath(int[][] grid, int k) {

		// BFS
		// 1、走到当前，如果 移除的障碍>k，则不能继续往下--此路不通。 需要 int[][] 记录当前已经移除的障碍个数
		// 2、四个方向的int[][] 。当前，都需对四个方向进行for循环，判断是否 为路径
		// min 记录下最短路径

		// 3、每个方向可走的路径记录在queue中 不空则继续pop 为当前

		int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		int m = grid.length;
		int n = grid[0].length;
		int cost = -1;// 走到当前节点的一共的花费
		int[][] obstacle = new int[m][n];// 记录当前已经移除的障碍个数
		for (int i = 0; i < m; i++) {
			Arrays.fill(obstacle[i], Integer.MAX_VALUE);
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0, grid[0][0] });// 当前为第一个位置
		obstacle[0][0] = grid[0][0];

		while (!queue.isEmpty()) {
			int size = queue.size(); // 一定要先获取，queue后面要加入下一层级节点
			cost = cost + 1;
			for (int i = 0; i < size; i++) {//BFS需要对queue中每一个继续bfs到下一层，然后再来一遍
				int[] cur = queue.poll();// 当前
				if (cur[0] == m - 1 && cur[1] == n - 1) {
					
					System.out.println(cost);
					return cost;
					
				}
					
				// obstacle[cur[0]][cur[1]] = cur[2];//当前走过去则改变当前的obstacle

				for (int[] dirDiff : dir) {
					int row = cur[0] + dirDiff[0];
					int col = cur[1] + dirDiff[1];
					if (row >= 0 && row <= m - 1 && col >= 0 && col <= n - 1) {
						int newk = cur[2] + grid[row][col];//☆☆☆☆☆cur[2]记录的走到当前，一共删除的障碍物个数
						if (newk <= k && newk<obstacle[row][col] ) {//☆☆☆☆☆obstacle是，以前走到过当前位置 删除的障碍 -- 这里是贪心
							queue.offer(new int[] { row, col, newk });
							obstacle[row][col]=newk;
						}

					}
				}

			}

		}

		return -1;
	}

	public static int shortestPath2(int[][] grid, int k) {

		        //记录 已经消除的障碍物数量 
		        int m = grid.length;
		        int n = grid[0].length;

		       

		        int[][] dp = new int[m][n];
		        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		        for (int i = 0; i < m; i++) {
		            
					Arrays.fill(dp[i],Integer.MAX_VALUE);
				}

		        dp[0][0]=grid[0][0];

		        Queue<int[]> queue = new LinkedList();
		        queue.offer(new int[]{0,0});

		        int cost =-1;

		        while (!queue.isEmpty()){
		            int size = queue.size();
		            cost ++;
		            for(int i =0;i<size;i++){
		                int[] cur = queue.poll();
		                int r = cur[0];int c = cur[1]; 
		                int odk = dp[r][c];
		                if(r==m-1 && c == n-1){
		                    //如果已经到了目标节点则直接返回结果
		                	System.out.println(cost);
		                    return cost;
		                }
		                
		                

		                for(int[] d : dir){
		                    int row = r+d[0];
		                    int col = c+d[1];
		                    
		                    if(row>=0 && row<=m-1 && col>=0 && col<=n-1 ){
		            
		                            int newk = odk+grid[row][col];
		                            if(newk<=k && newk <dp[row][col]){
		                                 dp[row][col] = newk;
		           
		                    queue.offer(new int[]{row,col});
		                            }
		            
		                    
		                }
		            }
		            

		        }
		        }
		        

		    return -1;

	}

		
	
	
	public static void main(String[] args) {
		int[][] input = new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
		shortestPath2(input,1);

	}

}
