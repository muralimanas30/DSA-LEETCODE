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

Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

*   The **node of a binary tree** is a leaf if it has no children
*   The **depth of the root** is 0, and if the depth of a node is `d`, the depth of each of its children is `d + 1`.
*   The **deepest leaves** of a tree are the leaves that have the largest depth.
*   The **lowest common ancestor** of a set `S` of nodes, is the node `A` with the largest depth such that every node in `S` is in the subtree rooted at `A`.

**Example 1:**

Given the following binary tree:

```
     3
   /   \
  5     1
 / \   / \
6   2 0   8
   / \
  7   4
```

The deepest leaves are `7` and `4`. The lowest common ancestor (LCA) is `2`.

**Example 2:**

```
    1
   /
  2
```

The deepest leaf is `2`, and the LCA is `2`.

**Example 3:**

```
    0
   / \
  1   3
   \
    2
```

The deepest leaves are `2` and `3`. The LCA is `0`.

## 📊 **Algorithm**

*   Calculate the maximum depth of the binary tree using a helper function `treeDepth`.
*   Use a recursive helper function `findAncestors` to find the lowest common ancestor (LCA) of the deepest leaves.
*   If the current node is null or the current depth equals the maximum depth, return the current node.
*   Recursively call `findAncestors` for the left and right subtrees.
*   If both left and right subtrees return non-null nodes, the current node is the LCA.
*   If only one subtree returns a non-null node, return that node.
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

Consider this tree:

```
      3
     / \
    5   1
   / \ / \
  6  2 0  8
 / \
7   4
```

## 📊 **TABLE Representation**

N/A

## 📊 **WORKING**

Let's walk through the provided example:

```
      3
     / \
    5   1
   / \ / \
  6  2 0  8
 / \
7   4
```

1.  **Calculate Maximum Depth:**  `treeDepth(root)` will return 4.
2.  **Call `findAncestors(root, 1, 4)`:**
    *   `findAncestors(3, 1, 4)`
        *   `left = findAncestors(5, 2, 4)`
            *   `left = findAncestors(6, 3, 4)`
                *   `left = findAncestors(7, 4, 4)` returns `7`
                *   `right = findAncestors(4, 4, 4)` returns `4`
                *   returns 6
            *   `right = findAncestors(2, 3, 4)` returns `2`
            *   Since both `left` and `right` are not null, returns `5`
        *   `right = findAncestors(1, 2, 4)` returns `1`
        *   Since both `left` and `right` are not null, returns `3`

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  The `treeDepth` function traverses the entire tree once, taking O(N) time, where N is the number of nodes. The `findAncestors` function also traverses the tree in O(N) time in the worst case. Therefore, the overall time complexity is **O(N)**.

*   **Space Complexity:**  The space complexity is determined by the recursion depth. In the worst case (a skewed tree), the recursion depth can be O(N). Therefore, the space complexity is **O(N)**. In a balanced tree, the recursion depth will be O(log N), but the worst-case scenario dominates.
    