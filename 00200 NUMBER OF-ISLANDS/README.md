# 00200 - Number-of-islands
    
**Language:** Java  
**Runtime:** 5 ms (Beats 33.94% of users)  
**Memory:** 49.1 MB (Beats 90.48% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title            | 🔗 Link                                                                 |
| ------------------ | ------------------- | ----------------------------------------------------------------------- |
| 200                | NUMBER OF ISLANDS | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water. In essence, we need to traverse the grid and identify connected components of '1's, treating each component as a separate island.

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

## 💡 Algorithm Explanation:

-  Loop through each cell in the grid.
-  If a cell is '1' (land), increment the island count and perform a Breadth-First Search (BFS) starting from that cell.
-  During BFS, mark the current land cell as visited by changing its value to '-'.
-  Explore adjacent land cells (horizontally and vertically) using the `dx` and `dy` arrays.
-  If an adjacent cell is within the grid boundaries and is a land cell ('1'), mark it as visited and add it to the queue for further exploration.
-  Continue BFS until all connected land cells have been visited.

## 📊 **ASCII Representation**

Consider the following grid:

```
11000
11000
00100
00011
```

Visual representation during BFS from grid[0][0]:

1.  Initial grid, start BFS at (0,0):

```
11000
11000
00100
00011
```

2.  After visiting (0,0) and its neighbors:

```
--000
--000
00100
00011
```

3.  After visiting (2,2) and its neighbors:

```
--000
--000
00-00
00011
```

4. After visiting (3,3) and its neighbors:
```
--000
--000
00-00
000--
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: **O(M \* N)**, where M is the number of rows and N is the number of columns in the grid. This is because we visit each cell in the grid at most once.
*   **Space Complexity**: **O(min(M, N))** in the worst case, where M is the number of rows and N is the number of columns. This is due to the space used by the queue in the BFS algorithm, which can hold at most all the land cells if the entire grid is filled with land. In the worst case, the size of the queue is proportional to the minimum dimension of the grid.
    