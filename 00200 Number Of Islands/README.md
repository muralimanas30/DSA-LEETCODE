# 00200 - Number Of Islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title           | 🔗 Link                                                                 |
| ------------------ | ----------------- | ----------------------------------------------------------------------- |
| 200                | Number of Islands | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.  Essentially, you need to traverse the grid and find connected components of '1's, treating each component as an island.

## 📊 **Algorithm**

*   Iterate through each cell of the grid.
*   If a cell contains '1' (land), increment the island count.
*   Perform a Breadth-First Search (BFS) starting from that cell to explore the entire island and mark all connected '1's as visited to avoid recounting.
*   The BFS explores adjacent cells (up, down, left, right) and continues the exploration if they are also land ('1').
*   The algorithm continues until all cells in the grid have been processed.

## 🔥 **Code Implementation**

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Queue<Pair> qu = new LinkedList<>();
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int islands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    bfs(new Pair(i, j), grid, rows, columns);
                }
            }
        }
        return islands;
    }

    private void bfs(Pair start, char[][] grid, int rows, int columns) {
        qu.add(start);
        grid[start.x][start.y] = '-'; // Mark as visited

        while (!qu.isEmpty()) {
            Pair current = qu.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isValid(newX, newY, rows, columns) && grid[newX][newY] == '1') {
                    grid[newX][newY] = '-'; // Mark as visited
                    qu.add(new Pair(newX, newY));
                }
            }
        }
    }

    private boolean isValid(int i, int j, int rows, int columns) {
        return i >= 0 && i < rows && j >= 0 && j < columns;
    }
}
```

## 📊 **ASCII Representation**

Consider the following grid:

```
11000
11000
00100
00011
```

We can visualize the BFS traversal for identifying islands.

## 📊 **WORKING**

Let's trace the execution with the input:

```
11000
11000
00100
00011
```

1.  **Initial Grid:**

    ```
    11000
    11000
    00100
    00011
    ```

2.  **First Island Detection:**
    *   `grid[0][0]` is '1'. `islands = 1`.
    *   BFS starts at (0, 0).
    *   The connected component of '1's is marked as visited (e.g., changed to '-').

    ```
    --000
    --000
    00100
    00011
    ```

3.  **Second Island Detection:**
    *   The loop continues; `grid[2][2]` is '1'.  `islands = 2`.
    *   BFS starts at (2, 2).

    ```
    --000
    --000
    00-00
    00011
    ```

4.  **Third Island Detection:**
    *   The loop continues; `grid[3][3]` is '1'.  `islands = 3`.
    *   BFS starts at (3, 3).

    ```
    --000
    --000
    00-00
    000--
    ```

5.  **Result:** The function returns `3`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(M \* N), where M is the number of rows and N is the number of columns in the grid.  We visit each cell at most once.
*   **Space Complexity:** O(M \* N) in the worst-case scenario, where the entire grid is filled with land ('1') and the BFS queue will contain all the cells.
    