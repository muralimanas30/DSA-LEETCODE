# 01123 - Lowest Common Ancestor Of Deepest Leaves
    
**Language:** Java  
**Runtime:** 1 ms (Beats 38.47% of users)  
**Memory:** 44.6 MB (Beats 31.91% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 1123 | LOWEST COMMON ANCESTOR OF DEEPEST LEAVES | [LeetCode Problem](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/) |

---

## 💡 **Problem Explanation**

Given the `root` of a binary tree, return the lowest common ancestor of its deepest leaves. Recall that:

*   A node is a *leaf* if it has no children.
*   The *depth* of the root is 0, and if the depth of a node is `d`, the depth of each of its children is `d + 1`.
*   The *deepest leaves* of a tree are the leaves that have the maximum depth.
*   The *lowest common ancestor* of a set `S` of nodes is the node `A` with the largest depth such that every node in `S` is in the subtree rooted at `A`.

**Example:**

Consider the following binary tree:

```
     3
   /   \
  5     1
 / \   / \
6   2 0   8
   / \
  7   4
```

The deepest leaves are `7` and `4` (depth 3). The lowest common ancestor of `7` and `4` is `2`.  Therefore, the output should be the node `2`.

---

## 📊 **Algorithm**

*   Calculate the maximum depth of the tree.
*   Recursively traverse the tree to find the lowest common ancestor of the deepest leaves.
*   If the current node's depth is equal to the maximum depth, it is a deepest leaf, so return the node.
*   Recursively find the LCA in the left and right subtrees.
*   If both left and right subtrees return a node, the current node is the LCA.
*   If only one subtree returns a node, return that node.
*   If both subtrees return null, return null.

## 🔥 **Code Implementation**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int maximumDepth = treeDepth(root);
        return findAncestors(root, 1, maximumDepth);
    }

    public TreeNode findAncestors(TreeNode root, int depth, int maximumDepth){
        if(maximumDepth == depth || root == null)
            return root;
        TreeNode left = findAncestors(root.left, depth + 1, maximumDepth);
        TreeNode right = findAncestors(root.right, depth + 1, maximumDepth);
        if(left != null && right != null) return root;
        else if(left != null) return left;
        return right;
    }

    public int treeDepth(TreeNode root){
        if(root == null)
            return 0;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return 1 + Math.max(left, right);
    }
}
```

## 📊 **ASCII Representation**

Consider the following tree for better visualization.

```
        3
       / \
      5   1
     / \ / \
    6  2 0  8
   / \
  7   4
```

## 📊 **WORKING**

Let's trace the execution with the example tree:

```
        3
       / \
      5   1
     / \ / \
    6  2 0  8
   / \
  7   4
```

1.  `treeDepth(root)` returns 4.
2.  `lcaDeepestLeaves(root)` calls `findAncestors(root, 1, 4)`.
3.  `findAncestors(3, 1, 4)`:
    *   `left = findAncestors(5, 2, 4)`
    *   `findAncestors(5, 2, 4)`:
        *   `left = findAncestors(6, 3, 4)`
        *   `findAncestors(6, 3, 4)`:
            *   `left = findAncestors(7, 4, 4)` returns `7`
            *   `right = findAncestors(4, 4, 4)` returns `4`
            *   Since depth is the maximum depth of 4, return `7` and `4`
            *  since both not null return 6
        *   `right = findAncestors(2, 3, 4)`
        *   `findAncestors(2, 3, 4)`:
            returns 2
        *  since both not null return 5
    *   `right = findAncestors(1, 2, 4)`
        *   `findAncestors(1, 2, 4)`:
            * return 1
    * since both not null return 3

So, the LCA of the deepest leaves (7 and 4) is 2.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(N), where N is the number of nodes in the binary tree. The `treeDepth` function visits each node once to determine the maximum depth. The `findAncestors` function also visits each node once in the worst case.
*   **Space Complexity:** O(H), where H is the height of the binary tree. This is due to the recursive call stack. In the worst case, H can be equal to N (for a skewed tree), and in the best case, H can be equal to log(N) (for a balanced tree).
    