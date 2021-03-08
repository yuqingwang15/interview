import collections


class Node:
    def __init__(self, x, next=None, random=None):
        self.val = int(x)
        self.next = next
        self.random = random


class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: Node
        :rtype: Node

        输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
        """

        # DFS
        visited = {}

        def dfs(root):
            # 返回的是root之后的所有copy的头结点

            if not root: return None
            if root in visited:
                return visited[root]  # 表示该节点已经copy过

            copy = Node(root.val, None, None)
            visited[root] = copy
            copy.next = dfs(root.next)
            copy.random = dfs(root.random)

            return copy

        return dfs(head)

    def copyRandomList2(self, head):
        # bfs本质比dfs加了一个queue先进先出
        # 非递归本质比dfs，加了一个stack 递归栈，先进后出

        visited = {}  # key value both node

        def bfs(root: Node):
            if not root: return root
            queue = collections.deque()

            copy = Node(root.val, None, None)  # 创建新结点
            visited[root] = copy
            queue.append(root)

            while queue:
                ele = queue.pop()
                if ele.next is not None and ele.next not in visited:
                    visited[ele.next] = Node(ele.next.val, None, None)
                    queue.append(ele.next)
                if ele.random is not None and ele.random not in visited:
                    visited[ele.random] = Node(ele.random.val, None, None)
                    queue.append(ele.random)
                visited[ele].next = visited.get(ele.next)
                visited[ele].random = visited.get(ele.random)
            return copy

        return bfs(head)

    def copyRandomList3(self, head):
        # 遍历 & 插入
        if not head: return
        cur = head  # 指针
        while cur:
            tmp = Node(cur.val, cur.next, None)
            cur.next = tmp
            cur = tmp.next

        cur = head
        while cur:
            if cur.random:
                cur.next.random = cur.random.next
            cur = cur.next.next

        # 拆开! 同时要恢复原来的表!

            # # 3. 拆分两链表
            # cur = res = head.next
            # pre = head
            # while cur.next:
            #     pre.next = pre.next.next
            #     cur.next = cur.next.next
            #     pre = pre.next
            #     cur = cur.next
            # pre.next = None  # 单独处理原链表尾节点
            # return res  # 返回新链表头节点

        cur =copy = head.next
        pre = head
        while cur.next:
            if pre.next:
                pre.next = pre.next.next

            if cur.next:
                cur.next = cur.next.next

            pre = pre.next
            cur = cur.next
        pre.next=None
        return copy
