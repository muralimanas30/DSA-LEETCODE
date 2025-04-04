# 01218 - Lowest Common Ancestor Of Deepest Leaves
    
**Language:** Java  
**Runtime:** 1 ms (Beats 32.65% of users)  
**Memory:** 44.6 MB (Beats 28.54% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title                        | 🔗 Link                                                                                                |
| ------------------ | ------------------------------- | ----------------------------------------------------------------------------------------------------- |
| 863                | Lowest Common Ancestor of Deepest Leaves | [LeetCode Problem](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/) |

## 💡 **Problem Explanation**

Given the `root` of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

*   Each node of a Binary Tree has a value.
*   The depth of each node is the number of edges between the `root` and that node.
*   The lowest common ancestor of a set of nodes `S` is the node `A` with the largest depth such that every node in `S` is in the subtree rooted at `A`.

**Example 1:**

*   **Input:** `root = [3,5,1,6,2,0,8,null,null,7,4]`
*   **Output:** `2`

**Explanation:**

The deepest leaves are the nodes with values 7 and 4. Their lowest common ancestor is the node with value 2.

**Example 2:**

*   **Input:** `root = [1]`
*   **Output:** `1`

**Explanation:**

The deepest leaf is the node with value 1, and its lowest common ancestor is itself.

**Example 3:**

*   **Input:** `root = [0,1,3,null,2]`
*   **Output:** `2`

**Explanation:**

The deepest leaves are the nodes with value 2. Their lowest common ancestor is the node with value 2.

## 📊 **Algorithm**

*   Calculate the maximum depth of the tree.
*   Implement a recursive function `findAncestors` to find the lowest common ancestor (LCA) of the deepest leaves.
    *   If the current depth equals the maximum depth, or the current node is `null`, return the current node.
    *   Recursively find the LCA in the left and right subtrees.
    *   If both left and right subtrees return non-`null` nodes, the current node is the LCA.
    *   If only one subtree returns a non-`null` node, return that node.
    *   If both subtrees return `null`, return `null`.
*   Implement a function `treeDepth` to calculate the depth of the tree.

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

Let's consider a simplified example for ASCII representation:

```
      3
     / \
    5   1
   / \
  6   2
     / \
    7   4
```

## 📊 **WORKING**

Let's trace the execution for the example input `root = [3,5,1,6,2,0,8,null,null,7,4]`.

1.  **`treeDepth(root)`**:
    *   Recursively computes the depth of the tree, which is 4.

2.  **`lcaDeepestLeaves(root)`**:
    *   Calls `findAncestors(root, 1, 4)`.

3.  **`findAncestors(3, 1, 4)`**:
    *   `left = findAncestors(5, 2, 4)`
    *   `right = findAncestors(1, 2, 4)`

4.  **`findAncestors(5, 2, 4)`**:
    *   `left = findAncestors(6, 3, 4)`
    *   `right = findAncestors(2, 3, 4)`

5.  **`findAncestors(6, 3, 4)`**:
    *   `left = findAncestors(null, 4, 4)` returns `null`
    *   `right = findAncestors(null, 4, 4)` returns `null`
    *   returns `6`

6.  **`findAncestors(2, 3, 4)`**:
    *   `left = findAncestors(7, 4, 4)` returns `7`
    *   `right = findAncestors(4, 4, 4)` returns `4`
    *   returns `2`

7.  Back in `findAncestors(5, 2, 4)`:
    *   `left = 6`, `right = 2`
    *   returns `5`

8.  **`findAncestors(1, 2, 4)`**:
    *   `left = findAncestors(0, 3, 4)` returns `0`
    *   `right = findAncestors(8, 3, 4)` returns `8`
    *   returns `1`

9.  Back in `findAncestors(3, 1, 4)`:
    *   `left = 5`, `right = 1`
    *   returns `3`

So the function ultimately finds `2` as the LCA of the deepest nodes, 7 and 4.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The `treeDepth` function traverses the tree once, taking O(N) time where N is the number of nodes. The `findAncestors` function can also potentially visit each node in the tree, resulting in O(N) time complexity. Therefore, the overall time complexity is **O(N)**.
*   **Space Complexity:** The recursion stack in both `treeDepth` and `findAncestors` can go as deep as the height of the tree in the worst case. In a balanced tree, this is O(log N), but in a skewed tree, it can be O(N). Thus, the space complexity is **O(H)**, where H is the height of the tree. In the worst case, this can be **O(N)**.
    