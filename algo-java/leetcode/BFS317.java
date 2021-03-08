package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class BFS317 {//	1、每个1都需要一遍bfs 2、遇到0才更新距离和reach数组
	
	private static int[][] path;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
    private static int min = Integer.MAX_VALUE;
    private static int building = 0;
    
    
	public static int buildingShortPath(int[][] grid) {
		
		//为0 处建房屋，1处为building，离所有的building最近的距离，一个不能达到则-1
//		输入: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
//				1 - 0 - 2 - 0 - 1
//				|   |   |   |   |
//				0 - 0 - 0 - 0 - 0
//				|   |   |   |   |
//				0 - 0 - 1 - 0 - 0
//
//				输出: 7 
		
		//记录下当前为0 时到1 的距离，一个不能达到则-1
		//返回最小的
		if(grid==null || grid.length==0 )return -1;
		int m = grid.length;
		int n = grid[0].length;
		int[][] reach = new int[m][n];
		path = new int [m][n];
		
		for(int i=0;i<m;i++) {
			for(int j =0;j<n;j++) {
				if(grid[i][j]==1) {
					building++;
					bfs(grid,i,j,m,n,reach);//每次bfs计算出 0 到这个1 的距离和可达性，并需要将可达性、距离累计
				}
			}
		}
		

		for(int i=0;i<m;i++) {
			for(int j =0;j<n;j++) {
				if(reach[i][j]==building) {
			
					min = Math.min(min,path[i][j]);
				}
			
			}
		}
		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
		return min == Integer.MAX_VALUE ? -1 : min;
		
		
		
	}
	
	//如果第一个是1 （一记录下所有的building ），压栈，则，pop出来，找周围的，0就更新path、可达的building数量+1，都压栈
	//如果是 0 ，则出栈不管，继续找到0，cost++
	//如果访问过，则忽略
	
	
	private static void bfs(int[][] grid,int srow,int scol,int m ,int n,int[][] reach){
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[]{srow,scol});
		int cost = 0;
		boolean[][] visited = new boolean[m][n];
		visited[srow][scol] = true;
		System.out.printf(" here not in while %d %d %d \n ",path[1][3],path[1][2],cost);
		while(!queue.isEmpty()) {
			cost++; //第一层
			int s = queue.size();
			//如果是0 则
			for(int ci = 0;ci<s;ci++) {
				int[] cur = queue.poll();
				
				System.out.printf("%d \n",queue.size());
				for(int i=0;i<4;i++) {
					int r = cur[0] + dx[i];
					int c = cur[1] + dy[i];
					System.out.printf("in for %d %d %d %d %d\n ",cur[0],cur[1],r,c,cost);
					if (r>=0 && r<=m-1 && c>=0 && c<=n-1 && grid[r][c]==0  && !visited[r][c]) {
						
						System.out.printf("in while see path cost %d %d %d\n ",r,c,cost);
						if(r==1 && c==2) {
							System.out.printf("in while see path cost %d %d %b\n ",path[1][2],cost,visited[1][2]);
						}
							reach[r][c]++;//可达一个
							path[r][c] =path[r][c]+cost;
							queue.offer(new int[]{r,c});
							visited[r][c] = true;
						
					}
					
				}
				
			}
				
			}
		System.out.println();
		System.out.printf("%d %d  %d\n ",path[1][3],path[1][2],cost);
		System.out.println();
			
		}
	
	public static void main(String[] args) {
		int[][] input = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
		buildingShortPath(input);
	}
	
		

}
















