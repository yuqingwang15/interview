package algorithm;

public class Searchinarr {

    public int getPos(int[] A, int n, int val) {

        if(A.length==0 || A.length!=n || val>A[n-1] || val<A[0]){return  -1;}

        // low mid high
        int low =0;
        int high = n-1; //指向最后一位
        int mid = (low+high) /2;
        while(low<high-1 && A[mid]!=val){
            if( A[mid]>val){
                high = mid;
                mid = (low+high) /2;
            }else {low = mid; mid = (low+high) /2;}

        }
        if(A[mid]==val){
            for(int b=low;b<mid;b++){ if(A[b]==val){return b;}
            }
            return mid;}
            if(A[low]==val){ return low;}
            if(A[high]==val){
            for(int c=mid;c<high;c++){
                if(A[c]==val){
                        return c;
                    }
                }

            return high;}
        return -1;
    }


//    public static void main(String args[]){
//
//        Searchinarr sn = new Searchinarr();
//        int[] input={1,2,2,2,2,4,4};
//        int res1 = sn.getPos(input,7,2);
//        int res2 = sn.getPos(input,7,4);
//        int res3 = sn.getPos(input,7,8);
//        int res4 = sn.getPos(input,7,12);
//        int res5 = sn.getPos(input,7,-1);
//        System.out.printf("res1: %d res2:%d res3: %d res4:%d res5: %d %n",res1,res2,res3,res4,res5);
//
//    }
}

