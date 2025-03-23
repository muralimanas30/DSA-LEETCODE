# 00200 - Number Of Islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 200 | NUMBER OF ISLANDS | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.  Essentially, you need to traverse the grid and identify connected components of '1's, treating each connected component as a single island.

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

Consider the following input grid:

```
11000
11000
00100
00011
```

Visually, the algorithm will identify the two islands like so:

```
Island 1:  [[1, 1], [1, 1]]
Island 2:  [[1], [1, 1]]
```

## 💡 **Algorithm Explanation**

Here's the step-by-step breakdown:

*   Iterate through each cell of the grid.
*   If a cell contains `'1'` (land), increment the `islands` counter.
*   Perform a Breadth-First Search (BFS) starting from that cell to mark all connected land cells as visited.
*   The BFS algorithm explores adjacent cells (up, down, left, right) and adds them to the queue if they are valid (within grid boundaries) and contain land.
*   Mark the visited land cells by changing their value to `'-'` to avoid recounting them.
*   Continue iterating until all cells have been processed.
*   Return the final `islands` count.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: O(M \* N), where M is the number of rows and N is the number of columns in the grid.  Each cell is visited at most once.

*   **Space Complexity**: O(min(M, N)) in the worst case scenario where the grid is filled with land.  The space complexity depends on the size of the queue used for BFS, which can grow up to the size of the smallest dimension of the grid if the entire grid is one island.
    