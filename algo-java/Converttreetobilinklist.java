package algorithm;
import  java.util.Stack;
import share.TreeNode;

public class Converttreetobilinklist {
    //中序遍历返回双向链表
    /***********方法1*********************/
    public  TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null){
            return null;
        }
        TreeNode node=pRootOfTree;
        Stack<TreeNode> stack=new Stack<TreeNode>();
        Connection(node,stack);

        node=stack.get(0);
        return node;
    }

    public  void Connection(TreeNode newNode,Stack<TreeNode> stack){
        if(newNode==null) {
            return;
        }
        Connection(newNode.left,stack);

        if(stack.isEmpty()){
            stack.push(newNode);

        }
        else{
            stack.peek().right=newNode;
            newNode.left=stack.peek();
            stack.push(newNode);

        }

        Connection(newNode.right,stack);

    }

    /***********方法2*************/

    private TreeNode head = null;
    private TreeNode realHead = null;
    public TreeNode Convert2(TreeNode pRootOfTree) {
        ConvertSub(pRootOfTree);
        return realHead;
    }

    private void ConvertSub(TreeNode pRootOfTree) {

        if(pRootOfTree==null) return;
        ConvertSub(pRootOfTree.left); //整理好左子树
        if (head == null) {
            head = pRootOfTree;
            realHead = pRootOfTree;//整理中间如果左子树为空
        } else {
            head.right = pRootOfTree;
            pRootOfTree.left = head;
            head = pRootOfTree;    //整理中间如果左子树不空
        }
        ConvertSub(pRootOfTree.right);//整理好右子树
    }

    /****************方法3********************/

    //中序遍历时，前一个记为pre，当前记为p。pre.right=p；p.left=pre ；当前的p再换成pre pre=p
    //stack记录中序遍历。先一直到最左边，然后pop，pop后压入右，pop出来的就是当前的p
    //中序遍历的第一个作为root(双向链表的root)

    public TreeNode ConvertBSTToBiList(TreeNode root) {
        if(root==null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        TreeNode pre = null;// 用于保存中序遍历序列的上一节点
        boolean isFirst = true;

        while(p!=null||!stack.isEmpty()){
            //当stack空了，但是p还有那么还需要再继续。这样的情况是，原来树的左及root都结束了，那么p变成了右子树的root

            while(p!=null){
                stack.push(p);
                p = p.left;
            }//一直加入左边

            p = stack.pop();
            if(isFirst){
                root = p;// 将中序遍历序列中的第一个节点记为root！！！！
                pre = root;
                isFirst = false;
            }

            else{
                pre.right = p;
                p.left = pre;
                pre = p;
            }
            p = p.right;//中序遍历当前左为null，p结束了，看右
        }
        return root;
    }

    /*******************方法3进阶版2*************************/

    protected TreeNode leftLast = null;
    public TreeNode Convert3(TreeNode root) {
        if(root==null)
            return null;
        if(root.left==null&&root.right==null){
            leftLast = root;// 最后的一个节点可能为最右侧的叶节点
            return root;
        }
        // 1.将左子树构造成双链表，并返回链表头节点
        TreeNode left = Convert(root.left);
        // 3.如果左子树链表不为空的话，将当前root追加到左子树链表
        if(left!=null){
            leftLast.right = root;
            root.left = leftLast;
        }
        leftLast = root;// 当根节点只含左子树时，则该根节点为最后一个节点
        // 4.将右子树构造成双链表，并返回链表头节点
        TreeNode right = Convert(root.right);
        // 5.如果右子树链表不为空的话，将该链表追加到root节点之后
        if(right!=null){
            right.left = root;
            root.right = right;
        }
        return left!=null?left:root;
    }

}
