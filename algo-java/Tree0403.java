import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree0403 {

    static class TreeNode{
        int i;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){i = x;}
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static  ListNode[] listOfDepth(TreeNode tree) {

        List<ListNode> res = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(tree);

        while (!queue.isEmpty()){
            int len = queue.size();
            //ListNode startNode = new ListNode(-1);
            ListNode prenode = new ListNode(-1);

            for(int j=0;j<len;j++){

                TreeNode tmp=queue.removeFirst();

                if(tmp.left!=null) {queue.add(tmp.left);}
                if(tmp.right!=null) {queue.add(tmp.right);}
                if(j==0) {
                    prenode=new ListNode(tmp.i);
                    res.add(prenode);

                }
                else{
                    prenode.next = new ListNode(tmp.i);
                    prenode = prenode.next;

                }
            }
            prenode=null;

            //res.add(startNode);


        }
        return  res.toArray(new ListNode[] {});

    }

    public static void main(String[] args) {

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);



        t1.left = t2;
        t1.right = t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=null;
        t3.right=t7;
        t4.left=t8;

        ListNode[] res = listOfDepth(t1);
        for(ListNode i :res){
            System.out.println("====");
            System.out.println(i.val);
            while (i.next!=null)

            {   i = i.next;
                System.out.println(i.val);
            }
        }


    }
}
