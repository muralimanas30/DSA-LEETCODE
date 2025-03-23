# 02090 - Number Of Ways To Arrive At Destination
    
**Language:** Java  
**Runtime:** 8 ms (Beats 94.03% of users)  
**Memory:** 51.6 MB (Beats 5.24% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2090 | Number of Ways to Arrive at Destination | [LeetCode Problem](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/) |

---

## 💡 **Problem Explanation**

The problem requires us to find the number of ways to reach the destination city (city `n-1`) from the source city (city `0`) in a given road network. The road network is represented by an array of roads, where each road has a start city, an end city, and the time taken to travel between them. We need to find the number of paths that take the minimum time to reach the destination. Since the answer can be very large, we need to return the number of ways modulo `10^9 + 7`.

**Example:**

**Input:**
`n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[2,3,3],[6,3,3],[3,4,2],[4,5,3],[5,6,2]]`

**Output:**
`4`

The graph looks like:
```
                 7
      (0)----------------(6)
       |                /  \
     2 |               / 2  \
       |              /      \
      (1)----3----(2)----3----(3)
                      |       |
                   3  |   2   |
                      |       |
                     (4)---3---(5)
```

The optimal (shortest) paths from city 0 to city 6 are:
- 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 (length: 2+3+3+2+3+2 = 15)
- 0 -> 1 -> 2 -> 3 -> 6 (length: 2+3+3+3 = 11)
- 0 -> 6 (length: 7)

Shortest path is of length 7. Hence only path 0 -> 6 will be considered and output is 1.

## 📊 **Algorithm**
*   Initialize distances array with infinity and ways array with 0. Set distance of source to 0 and ways to 1.
*   Use a priority queue to store nodes to visit, prioritized by distance from the source.
*   While the priority queue is not empty:
    *   Get the node with the smallest distance from the source.
    *   If the current distance is greater than the recorded distance for the current node, skip it.
    *   Iterate through the neighbors of the current node:
        *   Calculate the new distance to the neighbor.
        *   If the new distance is smaller than the recorded distance for the neighbor:
            *   Update the recorded distance for the neighbor.
            *   Update the number of ways to reach the neighbor to be the same as the number of ways to reach the current node.
            *   Add the neighbor to the priority queue.
        *   If the new distance is equal to the recorded distance for the neighbor:
            *   Add the number of ways to reach the current node to the number of ways to reach the neighbor (modulo 10^9 + 7).
*   Return the number of ways to reach the destination modulo `10^9 + 7`.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int countPaths(int n, int[][] roads) {
        // Create an adjacency matrix to represent the graph
        int[][] graph = new int[n][n];
        // Populate the graph with the road information
        for (int[] edge : roads) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        // Initialize distances array with maximum possible value (infinity)
        long[] distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE);
        // Distance from source to source is 0
        distances[0] = 0;

        // Initialize ways array with 0, except for the source which is 1
        long[] ways = new long[n];
        ways[0] = 1;

        // Create a priority queue to store nodes to visit, prioritized by distance
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        // Add the source node to the priority queue with distance 0
        heap.add(new long[]{0, 0});

        // Perform Dijkstra's algorithm
        perform(heap, distances, graph, ways);

        // Return the number of ways to reach the destination (modulo 10^9 + 7)
        return (int) (ways[n - 1] % (1000000007));
    }

    public void perform(PriorityQueue<long[]> heap, long[] distances, int[][] graph, long[] ways) {
        // While there are nodes to visit in the priority queue
        while (!heap.isEmpty()) {
            // Get the node with the smallest distance
            long[] sub = heap.poll();
            int currNode = (int) sub[1];
            long distFromSrc = sub[0];

            // If the current distance is greater than the recorded distance, skip this node
            if (distFromSrc > distances[currNode]) continue;

            // Iterate through the neighbors of the current node
            for (int i = 0; i < graph.length; i++) {
                // If there is an edge between the current node and the neighbor
                if (graph[currNode][i] > 0) {
                    // Calculate the new distance to the neighbor
                    long newDistance = graph[currNode][i] + distFromSrc;
                    // If the new distance is smaller than the recorded distance
                    if (newDistance < distances[i]) {
                        // Update the recorded distance
                        distances[i] = newDistance;
                        // Update the number of ways to reach the neighbor
                        ways[i] = ways[currNode];
                        // Add the neighbor to the priority queue
                        heap.add(new long[]{newDistance, i});
                    } else if (newDistance == distances[i]) {
                        // If the new distance is equal to the recorded distance
                        // Add the number of ways to reach the current node to the number of ways to reach the neighbor (modulo 10^9 + 7)
                        ways[i] = (ways[i] + ways[currNode]) % (1000000007);
                    }
                }
            }
        }
    }
}
```

## 📊 **ASCII Representation**

The problem is based on a graph. Let's represent a simplified version of the example to show the algorithm flow:

```
(0)---2---(1)---2---(2)
```

## 📊 **WORKING**

Let's trace the algorithm with `n = 3, roads = [[0,1,2],[1,2,2]]`:

1.  Initialize `distances = [0, INF, INF]` and `ways = [1, 0, 0]`.
2.  Add `(0, 0)` to the priority queue.
3.  First iteration:
    *   Poll `(0, 0)`.
    *   Neighbor of `0` is `1` with distance `2`.
    *   `newDistance = 2`. Since `2 < INF`, update `distances[1] = 2` and `ways[1] = ways[0] = 1`.
    *   Add `(2, 1)` to the priority queue.
4.  Second iteration:
    *   Poll `(2, 1)`.
    *   Neighbor of `1` is `2` with distance `2`.
    *   `newDistance = 2 + 2 = 4`. Since `4 < INF`, update `distances[2] = 4` and `ways[2] = ways[1] = 1`.
    *   Add `(4, 2)` to the priority queue.
5.  Third iteration:
    *   Poll `(4, 2)`.
    *   `2` has no further unvisited neighbors.

Finally, `ways[2] = 1`, which is the number of ways to reach node `2` from node `0` with the shortest distance.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(E log V), where E is the number of edges (roads) and V is the number of vertices (cities). This is because we are using Dijkstra's algorithm with a priority queue.
*   **Space Complexity:** O(V + E), where V is the number of vertices (cities) and E is the number of edges (roads). This is due to the adjacency matrix and the distance and ways arrays.
    