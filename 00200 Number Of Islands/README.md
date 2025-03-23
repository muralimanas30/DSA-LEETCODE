# 00200 - Number Of Islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title           | 🔗 Link                                                     |
| ------------------ | ----------------- | ----------------------------------------------------------- |
| 200                | Number of Islands | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water. Essentially, we need to traverse the grid and whenever we find a '1', we increment our island count and then explore all adjacent '1's to mark them as visited to avoid recounting the same island.

---

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

**Algorithm Explanation:**

*   Iterate through each cell in the grid.
*   If a cell is '1' (land), increment the `islands` count.
*   Perform a Breadth-First Search (BFS) starting from that cell to mark all connected land cells as visited ('-'). This avoids recounting the same island.
*   BFS explores adjacent cells (up, down, left, right) and adds valid (within bounds and '1') cells to the queue.
*   Continue BFS until the queue is empty, meaning all connected land cells have been visited.

---

## 📊 **ASCII Representation**

Consider the following grid:

```
11000
11000
00100
00011
```

We start at `grid[0][0]` which is '1'. We increment our island count to 1 and start BFS.

The BFS will explore `grid[0][1]`, `grid[1][0]`, and `grid[1][1]`, marking them as visited.  This represents one island.

Then, the algorithm continues, skipping the '0's until it reaches `grid[2][2]`, which is '1'.  We increment the island count to 2 and start BFS again.

Finally, it discovers the '1's at `grid[3][3]` and `grid[3][4]`, which comprise the third island after discovering `grid[2][2]`.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(M * N)** where M is the number of rows and N is the number of columns in the grid. In the worst case, we might visit each cell in the grid.
*   **Space Complexity:** **O(M * N)** in the worst case, where M is the number of rows and N is the number of columns in the grid. This could occur if the entire grid is land, leading to all cells being added to the queue.
    