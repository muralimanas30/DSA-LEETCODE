# 02588 - Maximum Number Of Points From Grid Queries
    
**Language:** Java  
**Runtime:** 97 ms (Beats 64.12% of users)  
**Memory:** 60.8 MB (Beats 13.74% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2588 | MAXIMUM NUMBER OF POINTS FROM GRID QUERIES | [LeetCode Problem](https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/) |

---

## 💡 **Problem Explanation**

You are given a 2D grid of integers `grid` of size `m x n`, and an array of integers `queries` of size `k`.

You can start from the top-left cell `(0, 0)` and repeat the following process:

1.  You are allowed to move to an adjacent cell i.e., `(row, col)` to `(row+1, col)`, `(row-1, col)`, `(row, col+1)`, and `(row, col-1)`.
2.  You can only move to a cell whose value is strictly smaller than the value of the current cell.
3.  You stop when you cannot move to any other cell.

For each query `queries[i]`, find the number of cells that you can visit starting from the top-left cell such that the value of the starting cell is strictly smaller than `queries[i]`.

Return an array `answer` of size `k` where `answer[i]` is the answer to the `i-th` query.

**Example:**

```
Input: grid = [[1,2,3],[4,5,6]], queries = [2,2,1,3]
Output: [2,2,0,3]

Explanation:
For queries[0] = 2, the cells that can be visited are [0,0], [0,1].
For queries[1] = 2, the cells that can be visited are [0,0], [0,1].
For queries[2] = 1, the cells that can be visited are [0,0].
For queries[3] = 3, the cells that can be visited are [0,0], [0,1], [0,2].
```

---

## 📊 **Algorithm**

*   Sort the queries along with their original indices.
*   Use a priority queue to keep track of the cells to visit, sorted by their values.
*   For each query, expand the cells from the priority queue that are less than the query value.
*   Keep track of the number of visited cells.
*   Store the result for each query based on its original index.

---

## 🔥 **Code Implementation**

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int[] maxPoints(int[][] grid, int[] queries) {
        int[][] sortedQueries = new int[queries.length][2];
        for(int i = 0; i < queries.length; i++){
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a,b) -> a[0]-b[0]);
        int[] resultant = new int[queries.length];
        int points = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        q.offer(new int[] {grid[0][0], 0, 0});
        for(int i = 0; i < sortedQueries.length; i++){
            int valQueried = sortedQueries[i][0];
            int pos = sortedQueries[i][1];
            while(!q.isEmpty() && q.peek()[0] < valQueried){
                points++;
                int[] info = q.poll();
                int cellVal = info[0];
                int row = info[1];
                int col = info[2];
                grid[row][col] = -1;
                for(int j = 0; j < dir.length; j++){
                    int newrow = row + dir[j][0];
                    int newcol = col + dir[j][1];
                    if(newrow < 0 || newcol < 0 || newrow >= grid.length || newcol >= grid[0].length){continue;}
                    if(grid[newrow][newcol] != -1){
                        q.offer(new int[] {grid[newrow][newcol], newrow, newcol});
                        grid[newrow][newcol] = -1;
                    }
                }
            }
            resultant[pos] = points;

        }

        return resultant;
    }
}
```

## 📊 **ASCII Representation**

Consider a sample grid:

```
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
```

The algorithm explores this grid based on the given queries.

## 📊 **WORKING**

Let's walk through the algorithm with `grid = [[1,2,3],[4,5,6]]` and `queries = [2,2,1,3]`.

1.  **Initialization:**
    *   `sortedQueries` becomes `[[1, 2], [2, 0], [2, 1], [3, 3]]` after sorting based on query values and storing original indices.
    *   `resultant` is initialized to `[0, 0, 0, 0]`.
    *   `points = 0`.
    *   The priority queue `q` initially contains `[[1, 0, 0]]` (value, row, col).

2.  **Iterating through Sorted Queries:**

    *   **Query 1: `valQueried = 1`, `pos = 2`**
        *   `q.peek()[0] = 1 < 1` is false, so the `while` loop doesn't execute.
        *   `resultant[2] = 0`.

    *   **Query 2: `valQueried = 2`, `pos = 0`**
        *   `q.peek()[0] = 1 < 2` is true.
        *   `points` becomes 1.
        *   `info` is `[1, 0, 0]`.
        *   `grid[0][0]` is marked as visited (`-1`).
        *   Neighbors `(0, 1)` and `(1, 0)` are added to `q`.
        *   `q` now contains `[[2, 0, 1], [4, 1, 0]]`.
        *   The `while` loop terminates.
        *   `resultant[0] = 1`.

    *   **Query 3: `valQueried = 2`, `pos = 1`**
        *   `q.peek()[0] = 2 < 2` is false.
        *   `resultant[1] = 1`.

    *   **Query 4: `valQueried = 3`, `pos = 3`**
        *   `q.peek()[0] = 2 < 3` is true.
        *   `points` becomes 2.
        *   `info` is `[2, 0, 1]`.
        *   `grid[0][1]` is marked as visited (`-1`).
        *   Neighbor `(0, 2)` is added to `q`.
	    *   `q.peek()[0] = 4 < 3` is false.
        *   `resultant[3] = 2`.

3.  **Final Result:**

    *   `resultant` is `[1, 1, 0, 2]`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** `O(K log K + M * N * log(M * N))`, where `K` is the number of queries and `M * N` is the number of cells in the grid. Sorting the queries takes `O(K log K)` time. The while loop with priority queue may take O(M * N * log(M * N)) because each cell can be enqueued and dequeued at most once, and priority queue operations take logarithmic time.
*   **Space Complexity:** `O(M * N + K)`, where `M * N` is the space for the priority queue and the modified grid, and `K` is the space for the sorted queries and the result array.
    