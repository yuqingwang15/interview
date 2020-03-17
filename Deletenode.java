package algorithm;

import share.ListNode;


/**
 * @author yuqing Wang
 * @date 2020/3/12 11:36 AM
 * 删除链表中的节点，删除链表中重复节点
 **/

 import share.TreeNode;

public class Deletenode {

    public ListNode deleteNode(ListNode head, ListNode tobeDelete){

        if (head == null || tobeDelete == null)
            return null;
        if (tobeDelete.next != null) {
            // 要删除的节点不是尾节点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;

        } else {
            if (head == tobeDelete)
                // 只有一个节点
                head = null;
            else {
                ListNode cur = head;
                while (cur.next != tobeDelete)
                    cur = cur.next;
                cur.next = null;
            }
        }
        return head;

    }

    public ListNode deleteDuplicate(ListNode ln){

        if (ln == null){return ln;}
        if(ln.next == null){return ln;}


        //如果head重复一直找到不同于head的
        ListNode n = ln.next;
        if(ln.val == n.val){
            while(n!=null && n.val ==ln.val){//这里的n!=null 条件
                n = n.next;
            }

            ln.next = deleteDuplicate(n);

        }
        else{
            ln.next = deleteDuplicate(n);
        }
        return  ln;

    }

}
