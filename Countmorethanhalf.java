package algorithm;

import java.util.HashMap;
import java.util.Map;

public class Countmorethanhalf {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length==0){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        int key;
        int value=1;
        int i;
        int length = array.length%2==0?array.length/2:array.length/2+1;
        for (i = 0;i<length;i++){
            key = array[i];
            if(map.containsKey(key)){
                value=map.get(key)+1;
                map.put(key,value);
            }else {
                map.put(key,value);
            }
        }
        for (i = length-1;i<array.length;i++){
            key = array[i];
            if (map.containsKey(key)){
                value =map.get(key)+1;
                if (value>=length){
                    return key;
                }else {
                    map.put(key,value);
                }
            }
        }
        return 0;
    }
}
