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

The problem asks us to perform a vertical order traversal of a binary tree. Imagine the tree as a 2D plane with the root at (0, 0). We need to report the value of the nodes in each vertical line, sorted from top to bottom. If two nodes are in the same row and column, the order should be from left to right.

**Example:**

**Input:**

```
     3
    / \
   9  20
     /  \
    15   7
```

**Output:**

```
[[9], [3, 15], [20], [7]]
```

Explanation:

*   Column -1: Only node 9.
*   Column 0: Nodes 3 and 15 (3 is above 15).
*   Column 1: Only node 20.
*   Column 2: Only node 7.

---

## 📊 **Algorithm**

*   We will perform a Depth-First Search (DFS) to traverse the binary tree.
*   During DFS, we maintain the row and column indices of each node.
*   We use a `TreeMap` to store nodes based on their column and row indices, ensuring columns are sorted and rows are sorted within each column.
*   Nodes with the same row and column are stored in an `ArrayList`, which is then sorted before adding to the final result.
*   Finally, extract the values from the `TreeMap` into a list of lists representing the vertical order traversal.

## 🔥 **Code Implementation**

```java
import java.util.*;

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
        // TreeMap to store nodes based on column and row
        TreeMap<Pair<Integer, Integer>, ArrayList<Integer>> map = new TreeMap<>(
                (a, b) -> {
                    if (!a.getValue().equals(b.getValue()))
                        return a.getValue() - b.getValue(); // Compare by column
                    else
                        return a.getKey() - b.getKey(); // Compare by row
                });
        dfs(root, 0, 0, map); // Start DFS

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> colMap = new TreeMap<>(); // Map to group nodes by column

        // Iterate through the TreeMap to build the result
        for (Map.Entry<Pair<Integer, Integer>, ArrayList<Integer>> entry : map.entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            int col = key.getValue();
            ArrayList<Integer> vals = entry.getValue();
            Collections.sort(vals); // Sort nodes with the same row and column

            colMap.putIfAbsent(col, new ArrayList<>());
            colMap.get(col).addAll(vals); // Add values to the corresponding column list
        }

        res.addAll(colMap.values()); // Add column lists to the result
        return res;
    }

    // Depth-First Search to traverse the tree
    private void dfs(TreeNode node, int row, int col, TreeMap<Pair<Integer, Integer>, ArrayList<Integer>> map) {
        if (node == null)
            return;
        Pair<Integer, Integer> key = new Pair<>(row, col); // Create a pair for (row, col)
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(node.val); // Add the node value to the corresponding list

        dfs(node.left, row + 1, col - 1, map); // Traverse left subtree
        dfs(node.right, row + 1, col + 1, map); // Traverse right subtree
    }
}
```

## 📊 **ASCII Representation**

Let's visualize the tree from the example:

```
      3 (0,0)
     / \
   9 (-1,1) 20 (1,1)
     /  \
 15 (0,2) 7 (2,2)
```

The (col, row) coordinates are shown next to each node.

## 📊 **TABLE Representation**

Not applicable for this problem.

## 📊 **WORKING**

Let's trace the execution with the sample input:

```
     3
    / \
   9  20
     /  \
    15   7
```

1.  **DFS(3, 0, 0)**: `map` gets (0, 0) -> \[3]
2.  **DFS(9, 1, -1)**: `map` gets (-1, 1) -> \[9]
3.  **DFS(20, 1, 1)**: `map` gets (1, 1) -> \[20]
4.  **DFS(15, 2, 0)**: `map` gets (0, 2) -> \[15]
5.  **DFS(7, 2, 2)**: `map` gets (2, 2) -> \[7]

After DFS, the `map` looks like:

```
{
  (-1, 1) -> [9],
  (0, 0) -> [3],
  (0, 2) -> [15],
  (1, 1) -> [20],
  (2, 2) -> [7]
}
```

Finally, we group by columns:

```
Column -1: [9]
Column 0: [3, 15]
Column 1: [20]
Column 2: [7]
```

Result: `[[9], [3, 15], [20], [7]]`

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The time complexity is dominated by the DFS traversal of the tree, which takes O(N) time, where N is the number of nodes in the tree.  Sorting the nodes at the same position take O(klogk) where k is the number of such nodes.  Hence O(Nlogk)

*   **Space Complexity:** The space complexity is primarily determined by the `TreeMap`. In the worst case, the `TreeMap` can store all N nodes of the tree. Also recursion stack will take O(H) space, where H is height of the tree. Therefore, the space complexity is **O(N)**.
    