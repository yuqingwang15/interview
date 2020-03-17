package algorithm;

import share.ListNode;

public class Mergesortedlist {

    public ListNode mergeSortedlist(ListNode ln1,ListNode ln2){//写的不好，也不对，递归还是没用好。


        if (ln1 == null)
            return ln2;
        if (ln2 == null)
            return ln1;

        ListNode ln1next =  ln1.next;
        ListNode ln2next =  ln2.next;

        ListNode result = new ListNode();

        if(ln1.val<=ln2.val && ln1next.val>=ln2.val){
            ln1.next=ln2;
            ln2.next = ln1next;

        }//这里不需要考虑插入，直接比较head的val就可以，也不涉及到next的val

        result = mergeSortedlist(ln1,ln2next);

        if(ln1.val>ln2.val){//找到比ln1.val小的节点，插入到ln1前面

        }


        return  result;

    }


    /*****************方法一 递归********************/

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val <= list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    /*****************方法2 迭代********************/

    //cur list1 list2

    public ListNode Merge2(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);//head = cur,则当cur 的next变化的时候，head的next自动跟着变。？不是。当cur变了，不再跟着变。
        ListNode cur = head;

        while (list1 != null && list2 != null) {//list1 1->3->5 2->4->6
            if (list1.val <= list2.val) {  //当list2的val大于list1，那么，cur->135,list1（3）->5
                cur.next = list1;
                list1 = list1.next;
            } else {  //当list1>list2 val, 则cur（1）->list2（246），list2(4)->6
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;//cur（1）->35
        }

        if (list1 != null)
            cur.next = list1;
        if (list2 != null)
            cur.next = list2;
        return head.next;
    }
}
