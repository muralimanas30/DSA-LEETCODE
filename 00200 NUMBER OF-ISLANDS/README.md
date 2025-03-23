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

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), you need to count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water. Simply put, you need to traverse the grid and count how many separate landmasses there are.

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

Let's consider a simple example:

```
11000
11000
00100
00011
```

The algorithm will identify and count the connected components of '1's, which represent the islands.  Here's a visualization of how BFS might traverse the first island:

```
Starting point (0,0): 11000
                    ^
Exploring neighbors:
(0,1): 11000
       ^
Marking visited and continuing...
```

## 💡**Algorithm Explanation**
*   Iterate through each cell of the grid.
*   If a cell is '1' (land), increment the island count and start a Breadth-First Search (BFS) to explore the connected land.
*   In the BFS:
    *   Mark the current land cell as visited (e.g., change it to '-').
    *   Explore its four neighbors (up, down, left, right).
    *   If a neighbor is valid (within bounds) and is land ('1'), add it to the queue and mark it as visited.
*   Continue BFS until all connected land cells of the current island are visited.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: O(M * N), where M is the number of rows and N is the number of columns in the grid.  Each cell is visited at most once.

*   **Space Complexity**: O(min(M, N)) in the worst case. This happens when the grid is filled with land, and the queue in BFS can grow up to the size of the smaller dimension of the grid.
    