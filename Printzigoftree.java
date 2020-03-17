package algorithm;

import share.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


/**
 * @author yuqing Wang
 * @date 2020/3/13 3:40 PM
 **/

public class Printzigoftree {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {

        //树节点存放在queue中
        //ret里存放每一层
        //当前层存放在list中  --每一层都需要new;需要reverse判断是否需要Collections.reverse(list);之后不为空则加入ret
        //root压入queue，结果压入list，root的left right压入queue
        //直到所有节点浏览结束。


        ArrayList<ArrayList<Integer>> ret = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(pRoot);
        boolean reverse = false;


        //while为难点
        while (!queue.isEmpty()) {

            ArrayList<Integer> list = new ArrayList();


            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);

            }



            if (reverse)
                Collections.reverse(list);
            reverse = !reverse;
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }


    ArrayList<ArrayList<Integer>> NormalPrint(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList();
            int cnt = queue.size();

            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }

            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }

}
