import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Greedy452 {

    public static int findMinArrowShots(int[][] points) {

        if(points.length==0)return 0;
        if (points.length==1) return 1;

        //二维数组排序
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b){
                if(a[0]==b[0]){
                    return a[1] - b[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });

        //System.out.println(points[0][0]);

        int res =1;
        int len = points.length;
        Stack<int[]> sep = new Stack<>();
        boolean inter = false;

        sep.add(points[0]);

        for (int i = 1; i < len; i++) {
            inter = false;
            for (int c =0;c<res;c++) {

                int[] j = sep.get(c);

                if (j[0] <= points[i][1] && j[1]>=points[i][0]) {
                    //此时有交集

                    j[0] = Math.max(j[0], points[i][0]);
                    j[1] = Math.min(j[1], points[i][1]);
                    inter = true;
                }
            }
            if(!inter){
                sep.add(points[i]);
            }

            res = sep.size();

        }
        System.out.println(sep.size());
        return sep.size();

    }

    public static int findMinArrowShots2(int[][] points){
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
//        Arrays.sort(points, new Comparator<int[]>() {
//            public int compare(int[] a, int[] b){
//                if(a[0]==b[0]){
//                    return a[1] - b[1];
//                }else {
//                    return a[0] - b[0];
//                }
//            }
//        });


        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        int[][] points ={{-2147483646,-2147483645},{2147483646,2147483647}};

        findMinArrowShots2(points);


    }

}
