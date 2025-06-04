# 01029 - Vertical Order Traversal Of A Binary Tree
    
**Language:** Java  
**Runtime:** 4 ms (Beats 40.91% of users)  
**Memory:** 42.7 MB (Beats 51.13% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 987 | Vertical Order Traversal of a Binary Tree | [LeetCode Problem](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) |

---

## 💡 **Problem Explanation**

Given the `root` of a binary tree, calculate the vertical order traversal for the binary tree. For each node at position `(row, col)`, its left and right children will be at positions `(row + 1, col - 1)` and `(row + 1, col + 1)` respectively. The root of the tree is at `(0, 0)`.

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending at the rightmost column. There may be multiple nodes in the same row and column. In such a case, sort these nodes by their values.

**Example:**

```
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.
```

---

## 📊 **Algorithm**

*   Create a `TreeMap` called `map` to store nodes based on their `(row, col)` coordinates. The `TreeMap` is sorted first by column and then by row, ensuring nodes are processed in the correct order.
*   Perform a Depth-First Search (DFS) to traverse the binary tree.
*   In the DFS function, for each node, record its value in the `map` using its `(row, col)` coordinates as the key.
*   After the DFS traversal, convert the `map` into a `List<List<Integer>>` representing the vertical order traversal.
*   Use another `TreeMap` `colMap` to structure results per column for final ordering.

## 🔥 **Code Implementation**

```java
import java.util.*;
import javafx.util.Pair; // Import Pair

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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Pair<Integer, Integer>, ArrayList<Integer>> map = new TreeMap<>(
                (a, b) -> {
                    if (!a.getKey().equals(b.getKey()))
                        return a.getKey() - b.getKey();
                    else
                        return a.getValue() - b.getValue();
                });
        dfs(root, 0, 0, map);
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> colMap = new TreeMap<>();

        for (Map.Entry<Pair<Integer, Integer>, ArrayList<Integer>> entry : map.entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            int col = key.getValue();
            ArrayList<Integer> vals = entry.getValue();
            Collections.sort(vals);

            colMap.putIfAbsent(col, new ArrayList<>());
            colMap.get(col).addAll(vals);
        }

        res.addAll(colMap.values());
        return res;
    }

    private void dfs(TreeNode node, int row, int col, TreeMap<Pair<Integer, Integer>, ArrayList<Integer>> map) {
        if (node == null)
            return;
        Pair<Integer, Integer> key = new Pair<>(col, row);
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(node.val);

        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }
}
```

## 📊 **ASCII Representation**

Consider the following binary tree:

```
      3
     / \
    9   20
       /  \
      15   7
```

## 📊 **TABLE Representation**

N/A

## 📊 **WORKING**

Let's trace the execution with the example binary tree:

```
      3 (0,0)
     / \
    9   20
   (-1,1)/  \ (1,1)
      15   7
     (0,2)/  \ (2,2)
```

1.  The `dfs` function is called recursively to traverse the tree.
2.  Nodes are placed in a Treemap with keys as `Pair<Integer, Integer>`, (col, row).  Each entry contains list of node values.
3.  The treemap sorts the results based on column index. For nodes in the same column, they are sorted by row index.  If nodes share same col and row, their values are sorted.
4.  The treemap is traversed and the result is stored column-wise.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: The time complexity is **O(N log N)**, where N is the number of nodes in the binary tree. The DFS traversal visits each node once (O(N)). The `TreeMap` operations (insertion and retrieval) take O(log N) time, and sorting the node values in each column takes O(K log K) where K is the number of nodes in that column.
*   **Space Complexity**: The space complexity is **O(N)**, where N is the number of nodes in the binary tree. This is due to the space used by the `TreeMap` to store the nodes and the recursion stack during the DFS traversal.
    