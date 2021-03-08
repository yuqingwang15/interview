package algorithm;

import share.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Copyrandomlinklist {

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(){}

        RandomListNode(int label) {
            this.label = label;
        }

        public  RandomListNode clonetob(RandomListNode b){

            b.label = this.label;
            b.random =this.random;
            b.next=this.next;

            return  b;
        }
    }
//    public static void  main(String args[]){
//
//        ListNode a = new ListNode(12);
//        ListNode b = new ListNode(15);
//        ListNode c = new ListNode(18);
//        ListNode tmp = new ListNode();
//        ListNode res = new ListNode();
//        a.next =b;b.next=c;c.next=null;
//        tmp=a;
//
//        while(tmp!=null) {
//            ListNode clone = new ListNode();
//            clone.val = tmp.val;
//            clone.next = tmp.next;
//            tmp.next=clone;
//            tmp = clone.next;
//        }
//        res = a;
//        while (res!=null){
//            System.out.println(res.val);
//            res =res.next;
//        }
//
//
//
//    }


    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        // 插入新节点
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        // 建立 random 链接
        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null)
                clone.random = cur.random.next;
            cur = clone.next;
        }
        // 拆分
        cur = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pCloneHead;
    }

}
