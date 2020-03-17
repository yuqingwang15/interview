package algorithm;

import java.util.HashSet;
import  share.ListNode;

public class Entrynodeofloop {

    /*****************方法一********************/
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        HashSet<ListNode> set = new HashSet<ListNode>() ;
        while (pHead != null) {
            if (!set.add(pHead)) { return pHead ; }//hashset会return false
            pHead = pHead.next ;
        }
        return null ;
    }


    /*****************方法二********************/  //快慢指针

    //--------a入口，环=b+c
    //快指针路程=a+(b+c)k+b ，k>=1  其中b+c为环的长度，k为绕环的圈数（k>=1,即最少一圈，不能是0圈，不然和慢指针走的一样长，矛盾）。
    //慢指针路程=a+b
    //快指针走的路程是慢指针的两倍，所以：
    //（a+b）*2=a+(b+c)k+b
    //化简可得：
    //a=(k-1)(b+c)+c 这个式子的意思是： 链表头到环入口的距离=相遇点到环入口的距离+（k-1）圈环长度。其中k>=1,所以k-1>=0圈。所以两个速度相同的指针分别从链表头和相遇点出发，最后一定相遇于环入口。

    public ListNode EntryNodeOfLoop2(ListNode pHead)
    {
        ListNode fast=pHead;
        ListNode low=pHead;
        while(fast!=null&&fast.next!=null){//************************如果不包含环，要输出null
            fast=fast.next.next;
            low=low.next;
            if(fast==low)
                break;
        }
        if(fast==null||fast.next==null)//************************如果不包含环，要输出null
            return null;
        low=pHead;
        while(fast!=low){
            fast=fast.next;
            low=low.next;
        }
        return low;
    }


}
