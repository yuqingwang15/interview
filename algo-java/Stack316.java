import java.util.Arrays;
import java.util.HashMap;

public class Stack316 {

    public static  String removeDuplicateLetters(String s) {
        //去掉重复 且 保留顺序

//        class Solution {
//            public:
//            string removeDuplicateLetters(string s) {
//                string stk;
//                size_t i = 0;
//                for(size_t i = 0;i < s.size(); ++i)
//                {
//                    if(stk.find(s[i]) != string::npos) continue;
//                    while(!stk.empty()&& stk.back() > s[i]&&
//                            s.find(stk.back(), i) != string::npos)
//                        stk.pop_back();
//                    stk.push_back(s[i]);
//                }
//                return stk;
//            }
//        };

//        String removeDuplicateLetters(String s) {
//            Stack<Character> stk = new Stack<>();
//
//            // 维护一个计数器记录字符串中字符的数量
//            // 因为输入为 ASCII 字符，大小 256 够用了
//            int[] count = new int[256];
//            for (int i = 0; i < s.length(); i++) {
//                count[s.charAt(i)]++;
//            }
//
//            boolean[] inStack = new boolean[256];
//            for (char c : s.toCharArray()) {
//                // 每遍历过一个字符，都将对应的计数减一
//                count[c]--;
//
//                if (inStack[c]) continue;
//
//                while (!stk.isEmpty() && stk.peek() > c) {
//                    // 若之后不存在栈顶元素了，则停止 pop
//                    if (count[stk.peek()] == 0) {
//                        break;
//                    }
//                    // 若之后还有，则可以 pop
//                    inStack[stk.pop()] = false;
//                }
//                stk.push(c);
//                inStack[c] = true;
//            }
//
//            StringBuilder sb = new StringBuilder();
//            while (!stk.empty()) {
//                sb.append(stk.pop());
//            }
//            return sb.reverse().toString();
//        }



        String mock_stk = "";
        int[] count = new int[256];

        for(char c :s.toCharArray()){
            count[c-97] =count[c-97]+1;
        }
        System.out.println("Arrays.toString(count) = " + Arrays.toString(count));

        for(char c :s.toCharArray()){
            count[c-97]=count[c-97]-1;
            //站里没有当前元素，栈中元素后面还有且打于当前元素则pop
            System.out.println("c = " + c);
            if(! mock_stk.contains(String.valueOf(c))){
                while (!mock_stk.isEmpty() && mock_stk.charAt(mock_stk.length()-1)>c && count[mock_stk.charAt(mock_stk.length()-1)-97]>0){
                    //出栈
                    if(mock_stk.length()<=1){
                        mock_stk="";
                    }else{
                        mock_stk = mock_stk.substring(0,mock_stk.length()- 1);
                    }
                    System.out.println("mock_stk in while = " + mock_stk);
                }
                //当前元素压栈
                mock_stk = mock_stk+c;
            }

            System.out.println("c = " + c);
            System.out.println("Arrays.toString(count) = " + Arrays.toString(count));
            System.out.println("mock_stk = " + mock_stk);
        }

        return mock_stk;
    }

    public static void main(String[] args) {
        String s = "ecbacba";
        removeDuplicateLetters(s);

    }

}