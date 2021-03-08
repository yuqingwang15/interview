package algorithm;

/**
 * @author yuqing Wang
 * @date 2020/3/11 9:29 PM
 * 矩阵中路径是否存在
 **/

public class Routerectangle {


    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if(matrix==null || matrix.length==0 || str==null || str.length==0 || matrix.length!=rows*cols || rows<=0 || cols<=0 ) {
            return false ;
        }

        boolean[] pass = new boolean[rows*cols] ;
        int index=0;//搜索到了str中的第几个 

        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){

                if(solve(matrix,rows,cols,i,j,str,index,pass)){return true;}
            }
        }
        return false;

    }



    private boolean solve(char[] matrix, int rows,int cols,int i, int j, char[] str,int index,boolean[] pass){

        boolean  flag  = false;
        if(index ==str.length){ return true;}

        if(i>=0 && j>=0 && index<str.length && str[index]==matrix[i*cols+j] && pass[i*cols+j]!=true && i<rows  && j<cols){
            //***************************这一部分搞好久debug
            pass[i*cols+j]=true;

            if(  solve( matrix, rows,cols,i, j+1,  str, index+1,pass)|| solve(matrix, rows,cols,i+1, j,  str, index+1,pass)||
            solve(matrix, rows,cols,i, j-1,  str, index+1,pass)|| solve(matrix, rows,cols,i-1, j,  str, index+1,pass)){
                flag = true;
            }

            if(flag){

                return true;}else{  pass[i*cols+j]=false;}

        }
        return flag;
    }


}