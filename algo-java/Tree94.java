import java.util.*;

public class Tree94 {

    //输入：root = [1,null,2,3]
    //输出：[1,3,2]

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    List<Integer> res =new ArrayList<>();
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {



            //边界
            if (root == null) return null;
            //左 中 右子树

            if(root.left!=null){

                inorderTraversal(root.left);

            }
            res.add(root.val);
            if(root.right!=null){
                inorderTraversal(root.right);
            }

            return res;
        }

        public List<Integer> inorderTraversal2(TreeNode root) {

            Stack<TreeNode> stack  = new Stack();
            TreeNode tmp = root;
            while(!stack.isEmpty() || root!=null){
                if(root!=null){
                    stack.add(tmp);
                    tmp= tmp.left;
                }else {
                    tmp = stack.pop();
                    res.add(tmp.val);
                    root = tmp.right;
                }


            }

            return res;
        }
    }
}























