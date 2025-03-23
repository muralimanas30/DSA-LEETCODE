## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title             | 🔗 Link                                                         |
| :---------------- | :----------------- | :------------------------------------------------------------- |
| 200               | Number of Islands | [LeetCode Problem](https://leetcode.com/problems/number-of-islands/) |

---

## 💡 **Problem Explanation**

Given a 2D grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water. Simply put, you need to traverse the grid and count the distinct connected components (islands).

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

## 💡 **Algorithm Explanation**

*   Iterate through each cell in the grid.
*   If a cell contains '1' (land), increment the island count and perform a Breadth-First Search (BFS) to mark all adjacent lands as visited.
*   The BFS explores the connected component starting from the current land cell.
*   During the BFS, mark visited land cells by changing their value to '-'.
*   Use a queue to manage the cells to visit.
*   For each cell in the queue, explore its four adjacent neighbors (up, down, left, right).
*   If a neighbor is within the grid boundaries and is a land cell ('1'), mark it as visited and add it to the queue.
*   The algorithm continues until all connected land cells have been visited.

## 📊 **ASCII Representation**

Consider the following grid:

```
11000
11000
00100
00011
```

The algorithm will identify three islands:

```
Island 1: Connected '1's in the top-left corner.
Island 2: Single '1' in the middle.
Island 3: Connected '1's in the bottom-right corner.
```

The algorithm essentially traverses the grid and performs BFS or DFS on each unvisited land cell to mark the entire island as visited, and then counts the number of these traversals.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(M \* N)** where M is the number of rows and N is the number of columns in the grid.  Each cell can be visited at most once.

*   **Space Complexity:** **O(min(M,N))** in the worst case where the grid is filled with land ('1'), the queue in BFS can contain all the land cells, leading to space proportional to the smaller dimension of the grid.#   D S A - L E E T C O D E  
 