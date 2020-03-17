package algorithm;

import share.ListNode;

public class Reverselist {

    /*****************方法一********************/
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = ReverseList(next);
        next.next = head;
        return newHead;
    }// a -> b ->c  next指向b ReverseList(next)返回c->b

    /*****************方法二********************/
    public ListNode ReverseList2(ListNode head) {
        ListNode newList = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;//b(第二个节点)为next 存起来最后再作为head重来一遍。现在只需要变成-1>a，然后-1->b->a。做法为：
        // head.next = newList.next即b->a，然后再让newlist.next为此时的head，即newlist—>b->a。
        }
}
