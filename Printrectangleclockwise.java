package algorithm;

import java.util.ArrayList;

public class Printrectangleclockwise {


    /*****************方法1********************/
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList();

        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;

        while (r1 <= r2 && c1 <= c2) {

            for (int i = c1; i <= c2; i++)
                ret.add(matrix[r1][i]);//第一行直接加入
            for (int i = r1 + 1; i <= r2; i++)
                ret.add(matrix[i][c2]); //最后一列加入
            if (r1 != r2)
                for (int i = c2 - 1; i >= c1; i--)//最后一行加入
                    ret.add(matrix[r2][i]);
            if (c1 != c2)
                for (int i = r2 - 1; i > r1; i--)//第一列加入
                    ret.add(matrix[i][c1]);
            r1++; r2--; c1++; c2--;
        }


        return ret;
    }



    /*
    * 可以模拟魔方逆时针旋转的方法，一直做取出第一行的操作
例如
1 2 3
4 5 6
7 8 9
输出并删除第一行后，再进行一次逆时针旋转，就变成：
6 9
5 8
4 7
继续重复上述操作即可。*/

    /*****************方法2********************/


    //python
    /*   class Solution:
              # matrix类型为二维列表，需要返回列表

    def printMatrix(self, matrix):

        result = []
        while(matrix):
            result+=matrix.pop(0)
            if not matrix or not matrix[0]:
                break
            matrix = self.turn(matrix)
        return result
    def turn(self,matrix):
        num_r = len(matrix)
        num_c = len(matrix[0])
        newmat = []
        for i in range(num_c):
            newmat2 = []
            for j in range(num_r):
                newmat2.append(matrix[j][i])
            newmat.append(newmat2)
        newmat.reverse()
        return newmat
     */

}

