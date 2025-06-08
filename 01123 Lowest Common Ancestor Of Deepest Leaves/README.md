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
*   A node is a *leaf* if it has no children.
*   The *depth* of the root node is 0. if the depth of a node is `k`, the depth of its children is `k+1`.
*   The *deepest leaves* of a tree are the leaves with the largest depth.
*   The *lowest common ancestor* of a set of nodes `S` is the node `A` such that every node in `S` is in the subtree rooted at `A`, plus `A` is the deepest node that has this property.

**Example 1:**

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

The deepest leaves are 7 and 4, and their lowest common ancestor is 2.

**Example 2:**

```
    1
   /
  2
```

The deepest leaf is 2, and its lowest common ancestor is 2 itself.

---

## 📊 **Algorithm**

*   Calculate the maximum depth of the binary tree.
*   Define a recursive function to find the lowest common ancestor (LCA) of the deepest leaves.
*   If the current node's depth equals the maximum depth, return the current node. It's one of the deepest leaves.
*   Recursively find the LCA in the left and right subtrees.
*   If both left and right subtrees return a non-null node, the current node is the LCA.
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
For the given problem with binary trees, consider a basic tree structure to illustrate the LCA:

```
       1
      / \
     2   3
    / \
   4   5
  /
 6
```
In this example, if the deepest leaves are 6, 4, 5, then the deepest level is 3, and the LCA of the deepest leaves could be 2.
If the deepest leaves are just 6, the answer will be 6 itself.

## 📊 **WORKING**
Let's consider the first example again and step through the algorithm:

```
     3
   /   \
  5     1
 / \   / \
6   2 0   8
   / \
  7   4
```

1.  **`treeDepth(root)`**: Calculates the maximum depth of the tree, which is 4.
2.  **`findAncestors(root, 1, 4)`**:  Starts the recursive LCA finding.

    *   At node 3 (depth 1):
        *   `left = findAncestors(5, 2, 4)`
        *   `right = findAncestors(1, 2, 4)`

    *   At node 5 (depth 2):
        *   `left = findAncestors(6, 3, 4)`
        *   `right = findAncestors(2, 3, 4)`

    *   At node 6 (depth 3):
        *   `left = findAncestors(null, 4, 4)` -> returns null
        *   `right = findAncestors(null, 4, 4)` -> returns null
        *   returns 6 because depth (4) == maximumDepth

    *   At node 2 (depth 3):
        *   `left = findAncestors(7, 4, 4)` -> returns 7
        *   `right = findAncestors(4, 4, 4)` -> returns 4
        *   returns 2 because both left and right are non-null

    *   Back at node 5:
        *   `left = 6`
        *   `right = 2`
        *   returns 5 because both left and right are non-null

    *   At node 1 (depth 2):
        *   `left = findAncestors(0, 3, 4)`
        *   `right = findAncestors(8, 3, 4)`
        *   The recursive calls will result in:
            *   `left = 0` if 0's depth is maximumDepth else `null`
            *   `right = 8` if 8's depth is maximumDepth else `null`
        *   returns `null` (similarly to above)

    * Back at node 3:
        * `left = 5`
        * `right = 1`
        * returns 3 because both left and right are non-null.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The `treeDepth` function visits each node once, resulting in **O(N)** time complexity, where N is the number of nodes. The `findAncestors` function also visits each node in the worst case, leading to **O(N)** time complexity. Thus, the overall time complexity is **O(N)**.

*   **Space Complexity:** The space complexity is dominated by the recursive call stack. In the worst case (skewed tree), the call stack can grow up to the height of the tree, which can be N. Therefore, the space complexity is **O(N)**.  In the average case (balanced tree), the space complexity is **O(log N)** due to the call stack.
    