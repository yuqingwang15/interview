# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.



class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """

        res = []
        s= []
        p=root
        while p or s:
            if p :
                s.append(p)
                p=p.left
            else:
                tmp = s.pop()
                res.append(tmp.val)
                p = tmp.right

        return res
