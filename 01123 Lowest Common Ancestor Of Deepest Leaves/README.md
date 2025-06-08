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

Given the `root` of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

*   Each node of a Binary Tree has a value.
*   The depth of each node is the shortest distance to the root.
*   The deepest nodes are all the nodes that have the largest depth.
*   A node is an ancestor of itself and all of its descendants.

The lowest common ancestor of two nodes `a` and `b` is the node `c` with the largest depth such that `a` and `b` are descendants of `c`.

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

The deepest leaves are `7`, `4`, `0`, and `8`. The lowest common ancestor of these nodes is `2`, `7`, `4` -> `2` then `5`, `0`, `8` -> `1` then `3` which is 3.

**Input:** `root = [3,5,1,6,2,0,8,null,null,7,4]`
**Output:** `[2,7,4]`

---

## 📊 **Algorithm**
*   Calculate the maximum depth of the tree using a recursive function `treeDepth`.
*   Implement a recursive function `findAncestors` that searches for the lowest common ancestor.
*   If the current node's depth equals the maximum depth or the node is null, return the node.
*   Recursively call `findAncestors` for the left and right subtrees, incrementing the depth.
*   If both left and right subtrees return a non-null node, the current node is the LCA.
*   If only one subtree returns a non-null node, return that node.
*   If both subtrees return null, return null.
---

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

Consider the example tree:

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

1.  `treeDepth(root)` returns 4 (depth of the deepest leaves).

2.  `lcaDeepestLeaves(root)` calls `findAncestors(root, 1, 4)`.

3.  `findAncestors(3, 1, 4)`:
    *   `left = findAncestors(5, 2, 4)`
    *   `right = findAncestors(1, 2, 4)`

4.  `findAncestors(5, 2, 4)`:
    *   `left = findAncestors(6, 3, 4)`
    *   `right = findAncestors(2, 3, 4)`

5.  `findAncestors(6, 3, 4)`:
    *   `left = findAncestors(7, 4, 4)` returns 7
    *   `right = findAncestors(4, 4, 4)` returns 4
    *   Since both are not null `findAncestors(2,3,4)` return 2

6.  `findAncestors(5,2,4)`:
    *   left is non null
    *   right is non null
    *   `findAncestors(5,2,4)` return 5

7.  `findAncestors(1, 2, 4)`:
    *   `left = findAncestors(0, 3, 4)` returns 0
    *   `right = findAncestors(8, 3, 4)` returns 8
    *   return 1

8.  `findAncestors(3,1,4)`:
    *   left is non null
    *   right is non null
    *   `findAncestors(3,1,4)` returns 3

Thus, the LCA of the deepest leaves is 3.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  The `treeDepth` function visits each node once.  The `findAncestors` function also visits each node at most once.

*   **Space Complexity:** O(H), where H is the height of the tree, due to the recursive call stack. In the worst case (skewed tree), H can be equal to N, resulting in O(N) space complexity. In the best case (balanced tree), H would be log(N), leading to O(log N) space complexity.
    