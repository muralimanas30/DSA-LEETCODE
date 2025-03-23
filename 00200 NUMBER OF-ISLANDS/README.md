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

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.  Basically, we need to traverse the entire grid and whenever we encounter a `'1'`, we increment the island count and then perform a Breadth-First Search (BFS) or Depth-First Search (DFS) to mark all connected `'1'`s as visited, so we don't count them again.

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

Let's consider a simple grid for better visualization:

```
Grid:
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1
```

1.  **Initial Grid**: Represents the input grid with lands ('1') and water ('0').
2.  **Island Detection**: Starting from the top-left, the algorithm finds a '1'.
3.  **BFS Traversal**: BFS explores all connected '1's and marks them as visited (e.g., changes them to '-').

```
After processing the first island:
- - 0 0 0
- - 0 0 0
0 0 1 0 0
0 0 0 1 1

Then, the algorithm finds another '1' (the island in the middle).
```

4.  **Counting Islands**: Each time a new '1' is found and processed, the island count increments.

## 💡 Algorithm Explanation

*   **Initialization:**
    *   Initialize `islands = 0`.
    *   Create a `Queue` to perform BFS.
    *   Define `dx` and `dy` arrays for moving in four directions (up, right, down, left).

*   **Grid Traversal:**
    *   Iterate through each cell of the grid.
    *   If a cell contains `'1'`, it means a new island is found.

*   **BFS Traversal:**
    *   Increment `islands`.
    *   Call the `bfs` function, starting from the current cell.

*   **BFS Function (`bfs`):**
    *   Add the starting cell to the queue.
    *   Mark the starting cell as visited (e.g., change `'1'` to `'-'` to avoid recounting).
    *   While the queue is not empty:
        *   Dequeue a cell from the queue.
        *   For each of the four possible directions:
            *   Calculate the new coordinates.
            *   If the new coordinates are valid (within grid boundaries) and the cell contains `'1'`:
                *   Mark the new cell as visited.
                *   Enqueue the new cell.

*   **Validity Check (`isValid`):**
    *   Check if the given coordinates are within the grid boundaries.

*   **Return:**
    *   Return the final `islands` count.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(M * N)**, where M is the number of rows and N is the number of columns in the grid.  Each cell can be visited at most once.

*   **Space Complexity:** **O(M * N)** in the worst-case scenario, where the entire grid is filled with land ('1') and the queue can contain all the cells during BFS.
    