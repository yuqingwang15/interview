package algorithm;
import  share.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;

public class Routeintreegettarget {

    //从左右节点找和为target-root的路径。如果输入为叶子节点即左右为null，那么说明找到，返回val；

    public ArrayList<ArrayList<Integer>> routeTosum(TreeNode head, int sum) {


        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> res_left = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> res_right = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> res_head = new ArrayList<Integer>();

        if (head == null) {
            throw new NullPointerException();
        }

        if (head.left == null && head.right == null) {
            if (head.val == sum) {
                res_head.add(0, head.val);
                result.add(0, res_head);
                return result;
            } else {
                return null;
            }
        }

        if (head.left != null || head.right != null) {
            res_left = routeTosum(head.left, sum - head.val);
            res_left = addHead(head.val, res_left);
            if (res_left != null) {
                result.addAll(res_left);
            }
        }
        if (head.right != null) {
            res_right = routeTosum(head.right, sum - head.val);
            res_right = addHead(head.val, res_right);
            if (res_right != null) {
                result.addAll(res_right);
            }
        }


        return result;

    }

    public ArrayList<ArrayList<Integer>> addHead(int i, ArrayList<ArrayList<Integer>> res) {

        if (res != null) {
            Iterator<ArrayList<Integer>> it = res.iterator();
            while (it.hasNext()) {
                it.next().add(0, i);
            }
            return res;
        }
        return null;
    }

    /*****************方法2********************/


    private ArrayList<ArrayList<Integer>> ret = new ArrayList();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        backtracking(root, target, new ArrayList());
        return ret;
    }

    private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null)
            return;
        path.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            ret.add(new ArrayList(path));
        } else {
            backtracking(node.left, target, path);
            backtracking(node.right, target, path);
        }
        path.remove(path.size() - 1);  //比如path138 在ret.add后，path-1，回到13，再看right，加入后再-1，回到1
    }
}


