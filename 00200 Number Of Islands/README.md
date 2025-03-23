# 00200 - Number Of Islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title           | 🔗 Link                                                    |
| ------------------ | ----------------- | --------------------------------------------------------- |
| 200                | Number of Islands | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.  Essentially, you need to traverse the grid and identify connected components of '1's, where each connected component represents an island.

## 📊 **Algorithm**

*   Iterate through each cell of the grid.
*   If a cell contains '1' (land), increment the island count.
*   Perform a Breadth-First Search (BFS) starting from that cell to mark all connected land cells as visited (e.g., by changing them to '-').
*   The BFS explores adjacent land cells in all four directions (up, down, left, right).
*   A helper function `isValid` verifies that the given row and column indices are within the bounds of the grid.

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
    int[] dx = {0, 1, 0, -1}; // Possible movements in x direction.
    int[] dy = {-1, 0, 1, 0}; // Possible movements in y direction.

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
   0 1 2 3 4
0  1 1 0 0 0
1  1 1 0 0 0
2  0 0 1 0 0
3  0 0 0 1 1
```

Here, '1' represents land, and '0' represents water.  The goal is to count how many distinct land masses (islands) are present in this grid.

## 📊 **WORKING**

Let's trace the execution with the following input:

```
grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
```

1.  **Initialization:** `islands = 0`.
2.  **Iteration:** The code iterates through the grid.
3.  **First '1' Encountered (0,0):** `grid[0][0] == '1'`, so `islands` becomes 1.  BFS is initiated from (0,0).
4.  **BFS:**
    *   (0,0) is marked as visited (changed to '-').
    *   Neighbors (0,1) and (1,0) are checked.
    *   (0,1) contains '1', so it's marked visited and added to the queue.
    *   (1,0) contains '1', so it's marked visited and added to the queue.
    *   The BFS continues, exploring the connected components of '1's starting from (0,0), (0,1), and (1,0).  All '1's in the top-left cluster are marked visited.
5.  **Next '1' Encountered (0,4), (1,3), (2,0):**  These lands are now covered by the algo so count will not be incremented.
6.  **Iteration Continues:** The code continues iterating. Since we have changed the value to '-' so that it not increment islands again.
7.  **Return:** Finally, the function returns `islands`, which is 1.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(M \* N), where M is the number of rows and N is the number of columns in the grid.  Each cell in the grid is visited at most once during the BFS.

*   **Space Complexity:** O(M \* N) in the worst case. This occurs when the entire grid is filled with '1's, and the queue in the BFS might contain all the cells in the grid.
    