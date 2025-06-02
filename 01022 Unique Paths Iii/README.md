# 01022 - Unique Paths Iii
    
**Language:** Java  
**Runtime:** 1 ms (Beats 37.51% of users)  
**Memory:** 41.4 MB (Beats 29.94% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 98 | Unique Paths III | [LeetCode Problem](https://leetcode.com/problems/unique-paths-iii/) |

---

## 💡 **Problem Explanation**

You are given a 2D grid representing a maze. The grid contains four types of squares:

*   `1`: Starting square.  There is exactly one starting square.
*   `2`: Ending square.  There is exactly one ending square.
*   `0`: Empty squares we can walk over.
*   `-1`: Obstacles that we cannot walk over.

The goal is to find the number of paths that start at the starting square, walk over every non-obstacle square exactly once, and end at the ending square.

**Example:**

```
Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(2,1),(2,0),(1,0),(2,2)
```

## 📊 **Algorithm**

*   Initialize a counter `count` to store the number of unique paths.
*   Find the starting position (`startX`, `startY`) in the grid.
*   Count the number of empty cells (`empty`) in the grid (cells with value 0).
*   Use a backtracking algorithm to explore all possible paths from the starting position.
*   In the backtracking function:
    *   Check if the current position is valid (within the grid boundaries and not an obstacle).
    *   If the current position is the ending square and all empty cells have been visited, increment the `count`.
    *   Mark the current cell as visited (e.g., by setting it to -1).
    *   Recursively explore the adjacent cells (up, down, left, right).
    *   Backtrack by resetting the current cell to its original value.

## 🔥 **Code Implementation**

```java
class Solution {
    public int uniquePathsIII(int[][] grid) {
        int[] count = new int[]{0};
        int rows = grid.length;
        int cols = grid[0].length;
        int startX = 0, startY = 0;
        int empty = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
                else if(grid[i][j]==0)
                    empty++;
            }
        }
        backtrack(grid, startX, startY, count, empty);
        return count[0];
    }
    
    public void backtrack(int[][] grid, int i, int j, int[] res, int empty) {
        if (!correctPosition(grid, i, j) || grid[i][j] == -1)  
            return;
        if (grid[i][j] == 2 && empty == 0) {
            res[0]++;
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = -1;
        if (temp == 0) empty--;
        backtrack(grid, i, j + 1, res, empty);
        backtrack(grid, i + 1, j, res, empty);
        backtrack(grid, i, j - 1, res, empty);
        backtrack(grid, i - 1, j, res, empty);
        grid[i][j] = temp;
    }
    
    public boolean correctPosition(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
```

## 📊 **ASCII Representation**

Consider the following grid:

```
+---+---+---+---+
| 1 | 0 | 0 | 0 |
+---+---+---+---+
| 0 | 0 | 0 | 0 |
+---+---+---+---+
| 0 | 0 | 2 | -1|
+---+---+---+---+
```

## 📊 **WORKING**

Let's walk through the algorithm with the input `grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]`:

1.  **Initialization:**
    *   `count = 0`
    *   `startX = 0`, `startY = 0` (starting position)
    *   `empty = 7` (number of empty cells)

2.  **Backtracking:**

    The backtracking function explores all possible paths from (0, 0). It marks visited cells as `-1` temporarily.

    A simplified path can be:
    (0,0) -> (0,1) -> (0,2) -> (0,3) -> (1,3) -> (1,2) -> (1,1) -> (1,0) -> (2,0) -> (2,1) -> (2,2)

    The key is that EACH empty cell should be visited.

3.  **Base Case:**

    When we reach the ending cell (2, 2) and `empty == 0` which it means we have visited all `0` cells, `count` is incremented.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(4<sup>m*n</sup>), where m and n are the dimensions of the grid.  In the worst case, we might explore all possible paths. The 4 comes from the 4 possible directions (up, down, left, right) from each cell.
*   **Space Complexity:** O(m\*n) due to the recursion depth of the backtracking algorithm. This is the maximum depth of the call stack if we visit all cells in the grid.
    