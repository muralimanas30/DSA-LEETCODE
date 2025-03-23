# 00200 - NUMBERof-islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 200 | NUMBER OF ISLANDS | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Imagine you have a grid where '1' represents land and '0' represents water. The goal is to find out how many separate islands are present in the grid. An island is a group of connected '1's (land) that are horizontally or vertically adjacent.  Think of it like counting continents on a simplified map!

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

## 💡 Algorithm Explanation
*   Iterate through each cell in the grid.
*   If a cell is '1' (land), increment the `islands` counter.
*   Perform a Breadth-First Search (BFS) starting from this cell to mark all connected land cells as visited.

    *   Add the starting cell to a queue.
    *   While the queue is not empty:
        *   Dequeue a cell.
        *   Explore its four adjacent neighbors (up, down, left, right).
        *   If a neighbor is valid (within bounds) and is land ('1'):
            *   Mark the neighbor as visited (change its value to '-').
            *   Enqueue the neighbor.
*   Return the total number of `islands`.
## 📊 **ASCII Representation**

Here's how BFS helps explore and "sink" an island:

```
Initial Grid:

11000
11000
00100
00011

Exploring from (0,0):

Queue: [(0,0)]

11000  <- (0,0) is '1', add to queue
11000
00100
00011

Mark (0,0) as visited:

-1000
11000
00100
00011

Explore neighbors of (0,0): (0,1), (1,0)

Queue: [(0,1), (1,0)]

And so on... all connected '1's will be visited and marked.
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(M \* N), where M is the number of rows and N is the number of columns in the grid.  We visit each cell at most once.
*   **Space Complexity:** O(min(M, N)) in the worst case.  This is due to the space used by the queue in the BFS.  In the worst case, if the entire grid is land, the queue could potentially hold all the cells in a row or column.
    