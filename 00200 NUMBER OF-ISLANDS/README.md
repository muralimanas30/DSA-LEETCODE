# 00200 - number-of-islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 200 | NUMBER OF ISLANDS | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water. In essence, the problem asks us to find connected components in a 2D grid. Each connected component of '1's represents a single island.

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

An island would be a group of connected '1's.

## 💡 **Algorithm Explanation**

Here's a step-by-step explanation of the algorithm:

*   **Initialization:**

    *   Initialize `islands` to 0.
    *   Iterate through each cell of the grid.
*   **Island Detection:**

    *   If a cell contains `'1'`, increment `islands` and perform a Breadth-First Search (BFS) starting from that cell.
*   **Breadth-First Search (BFS):**

    *   Mark the starting cell as visited by changing its value to `'-'` (or any value other than `'1'`).
    *   Add the starting cell to a queue.
    *   While the queue is not empty:
        *   Dequeue a cell from the queue.
        *   Explore its four neighbors (up, down, left, right).
        *   For each valid neighbor (within the grid bounds and contains `'1'`):
            *   Mark the neighbor as visited.
            *   Enqueue the neighbor.
*   **Return Result:**

    *   After traversing the entire grid, return the value of `islands`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(M \* N), where M is the number of rows and N is the number of columns in the grid.  This is because each cell in the grid is visited at most once.
*   **Space Complexity:** O(min(M, N)) in the worst case, where M is the number of rows and N is the number of columns in the grid.  This is due to the space used by the queue during BFS, which can grow up to the size of the smallest dimension of the grid in the case of a large island.
    