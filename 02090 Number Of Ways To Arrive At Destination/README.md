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

Imagine you're in a city with `n` intersections, numbered from `0` to `n - 1`, and some roads connecting these intersections. You are given a list of roads where each road `[u, v, time]` means you can travel between intersections `u` and `v` in `time` minutes. Your goal is to find out how many ways you can reach intersection `n - 1` from intersection `0` in the *shortest* amount of time.  Since the number of ways can be large, return it modulo `10^9 + 7`.

For example:

Input: `n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[2,3,3],[6,3,3],[3,4,2],[4,5,3],[5,6,2]]`
Output: `4`

Explanation:
The shortest time to reach destination (node 6) is 7.
The four possible paths are:
- 0 -> 6
- 0 -> 1 -> 2 -> 3 -> 6
- 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
- 0 -> 1 -> 2 -> 3 -> 4 -> 6

---

## 📊 **Algorithm**
*   Initialize a graph represented as an adjacency matrix, where `graph[i][j]` stores the time it takes to travel from node `i` to node `j`.
*   Create arrays `distances` and `ways` to store the shortest distance from the source (node 0) to each node, and the number of ways to reach each node in the shortest time, respectively.
*   Initialize `distances` with `Long.MAX_VALUE` for all nodes except the source, which is initialized to 0.  Initialize `ways[0]` = 1.
*   Use a priority queue to implement Dijkstra's algorithm, where elements are sorted based on their distance from the source.
*   Iterate while the priority queue is not empty:
    *   Extract the node with the smallest distance from the priority queue.
    *   If the current shortest distance to the node is greater than the distance from the source, skip this node.
    *   For each neighbor of the current node:
        *   Calculate the new distance to the neighbor through the current node.
        *   If the new distance is shorter than the current shortest distance to the neighbor, update the shortest distance and set the number of ways to reach the neighbor to the number of ways to reach the current node.  Add the neighbor to the priority queue.
        *   If the new distance is equal to the current shortest distance to the neighbor, increment the number of ways to reach the neighbor by the number of ways to reach the current node (modulo 10^9 + 7).
*   Return the number of ways to reach the destination node (n - 1) modulo `10^9 + 7`.

## 🔥 **Code Implementation**
```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int countPaths(int n, int[][] roads) {
        // Initialize the graph as an adjacency matrix
        int[][] graph = new int[n][n];
        for (int[] edge : roads) {
            graph[edge[0]][edge[1]] = edge[2]; // Store the time to travel between nodes
            graph[edge[1]][edge[0]] = edge[2]; // Since it's an undirected graph
        }

        // Array to store the shortest distances from source (node 0)
        long[] distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE); // Initialize all distances to infinity
        distances[0] = 0; // Distance from source to source is 0

        // Array to store the number of ways to reach each node in the shortest time
        long[] ways = new long[n];
        ways[0] = 1; // There is one way to reach the source from the source

        // Priority queue to implement Dijkstra's algorithm
        // Stores {distance from source, node}
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        heap.add(new long[]{0, 0}); // Add the source node to the priority queue

        perform(heap, distances, graph, ways);

        return (int) (ways[n - 1] % (1000000007)); // Return the number of ways to reach the destination modulo 10^9 + 7
    }

    public void perform(PriorityQueue<long[]> heap, long[] distances, int[][] graph, long[] ways) {
        while (!heap.isEmpty()) {
            long[] sub = heap.poll(); // Get the node with the smallest distance from the priority queue
            int currNode = (int) sub[1]; // Current node
            long distFromSrc = sub[0]; // Distance from the source to the current node

            if (distFromSrc > distances[currNode]) continue; // If we have already found a shorter path, skip

            for (int i = 0; i < graph.length; i++) {
                if (graph[currNode][i] > 0) { // If there is an edge between the current node and node i
                    long newDistance = graph[currNode][i] + distFromSrc; // Calculate the new distance to node i

                    if (newDistance < distances[i]) { // If the new distance is shorter than the current shortest distance to node i
                        distances[i] = newDistance; // Update the shortest distance to node i
                        ways[i] = ways[currNode]; // Reset the number of ways to reach node i
                        heap.add(new long[]{newDistance, i}); // Add node i to the priority queue
                    } else if (newDistance == distances[i]) { // If the new distance is equal to the current shortest distance to node i
                        ways[i] = (ways[i] + ways[currNode]) % (1000000007); // Increment the number of ways to reach node i
                    }
                }
            }
        }
    }
}
```

## 📊 **ASCII Representation**

Let's consider a small example with `n = 4` and the following roads:

```
roads = [[0,1,1],[0,2,2],[1,2,1],[2,3,1]]
```

The graph can be visualized as follows:

```
      1      2
   (0)-----(1)
    \     /  \
     \   /    \ 1
      \ /      \
       (2)-----(3)
         1
```

## 📊 **WORKING**

Let's trace the execution with the given example: `n = 4, roads = [[0,1,1],[0,2,2],[1,2,1],[2,3,1]]`

1.  **Initialization**:
    *   `distances = [0, INF, INF, INF]`
    *   `ways = [1, 0, 0, 0]`
    *   `heap = [{0, 0}]`

2.  **Iteration 1**:
    *   `currNode = 0`, `distFromSrc = 0`
    *   Neighbors of 0: 1 (time 1), 2 (time 2)
    *   Update `distances[1] = 1`, `ways[1] = 1`, `heap.add({1, 1})`
    *   Update `distances[2] = 2`, `ways[2] = 1`, `heap.add({2, 2})`

3.  **Iteration 2**:
    *   `currNode = 1`, `distFromSrc = 1`
    *   Neighbors of 1: 0 (time 1), 2 (time 1)
    *   Node 0 is already visited with shorter distance.
    *   New distance to 2: `1 + 1 = 2`. `2 == distances[2]`, so `ways[2] = (1 + 1) % MOD = 2`

4.  **Iteration 3**:
    *   `currNode = 2`, `distFromSrc = 2`
    *   Neighbors of 2: 0 (time 2), 1 (time 1), 3 (time 1)
    *   Node 0 and 1 are already visited with shorter distances.
    *   New distance to 3: `2 + 1 = 3`. Update `distances[3] = 3`, `ways[3] = 2`, `heap.add({3, 3})`

5.  **Iteration 4**:
    *   `currNode = 3`, `distFromSrc = 3`
    *   Neighbors of 3: 2 (time 1)
    *   Node 2 already has a shorter distance.

Final result: `ways[3] = 2`

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: *O(E log V)*, where `E` is the number of roads and `V` is the number of intersections.  This is due to the use of Dijkstra's algorithm with a priority queue.
*   **Space Complexity**: *O(V + E)*, where `V` is the number of intersections and `E` is the number of roads.  This is due to storing the graph as an adjacency matrix, the distances array, the ways array, and the priority queue.
    