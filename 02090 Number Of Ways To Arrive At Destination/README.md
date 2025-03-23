# 02090 - Number Of Ways To Arrive At Destination
    
**Language:** Java  
**Runtime:** 8 ms (Beats 94.03% of users)  
**Memory:** 51.6 MB (Beats 5.24% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 1976 | Number of Ways to Arrive at Destination | [LeetCode Problem](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/) |

---

## 💡 **Problem Explanation**

The problem asks you to find the number of shortest paths from city `0` to city `n-1` in a weighted graph represented by the `roads` array. Each `road[i] = [ui, vi, timei]` indicates a bidirectional road between cities `ui` and `vi` that takes `timei` to traverse. Since the number of paths can be very large, you need to return the answer modulo `10^9 + 7`.

**Example:**

Input: `n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[2,3,3],[6,3,3],[3,4,2],[4,5,3],[5,6,2]]`

Output: `4`

Explanation: The shortest time from city 0 to city 6 is 7.
The ways to get there are:
- 0 -> 6
- 0 -> 1 -> 2 -> 3 -> 6
- 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
- 0 -> 1 -> 2 -> 3 -> 6
- 0 -> 6

## 📊 **Algorithm**

*   Build an adjacency matrix representation of the graph from the `roads` input.
*   Initialize an array `distances` to store the shortest distance from the source city (0) to each city. Initially, all distances are set to infinity, except for the source city, which is set to 0.
*   Initialize an array `ways` to store the number of shortest paths from the source city to each city. Initially, the number of ways to reach the source city is set to 1, and all other cities are set to 0.
*   Use a priority queue (min-heap) to implement Dijkstra's algorithm. The priority queue stores nodes as pairs of `(distance, node_id)`, allowing us to always visit the city with the smallest distance first.
*   Iterate while the priority queue is not empty:
    *   Extract the city with the smallest distance from the priority queue.
    *   For each neighbor of the current city:
        *   Calculate the new distance to the neighbor through the current city.
        *   If the new distance is shorter than the current shortest distance to the neighbor, update the `distances` array and the `ways` array with the number of ways to reach the current city. Add the neighbor to the priority queue.
        *   If the new distance is equal to the current shortest distance to the neighbor, increment the `ways` array by the number of ways to reach the current city (modulo 10^9 + 7).
*   Return the number of ways to reach the destination city (n-1) modulo 10^9 + 7.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static final int MOD = 1000000007;

    public int countPaths(int n, int[][] roads) {
        // Adjacency list to represent the graph.  Each element is a pair (neighbor, time)
        List<List<Pair<Integer, Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];
            adj.get(u).add(new Pair<>(v, time));
            adj.get(v).add(new Pair<>(u, time));
        }

        // dist[i] stores the shortest distance from 0 to i. Initialized to infinity.
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        // ways[i] stores the number of shortest paths from 0 to i.
        int[] ways = new int[n];
        ways[0] = 1;

        // Priority queue to store (distance, node).  Used for Dijkstra's algorithm.
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));
        pq.offer(new Pair<>(0L, 0));  // Start from node 0 with distance 0.

        while (!pq.isEmpty()) {
            Pair<Long, Integer> curr = pq.poll();
            long d = curr.getKey();
            int u = curr.getValue();

            if (d > dist[u]) {
                continue; // Skip if we've already found a shorter path to u.
            }

            for (Pair<Integer, Integer> edge : adj.get(u)) {
                int v = edge.getKey();
                int time = edge.getValue();
                long newDist = d + time;

                if (newDist < dist[v]) {
                    // Found a shorter path to v.
                    dist[v] = newDist;
                    ways[v] = ways[u];  // Reset the number of ways to reach v.
                    pq.offer(new Pair<>(newDist, v));
                } else if (newDist == dist[v]) {
                    // Found another shortest path to v.
                    ways[v] = (ways[v] + ways[u]) % MOD;  // Update number of ways (modulo MOD).
                }
            }
        }

        return ways[n - 1];
    }
}


// Helper class to represent a pair of values
class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
```

## 📊 **ASCII Representation**

While this problem doesn't directly involve grids, we can visualize the graph using ASCII art for the provided example:

```
      2
   1-----2
  / \   / \
 0   | 3---4
  \ /   \ / \
   6-----5    
      3
```

## 📊 **WORKING**

Let's trace the provided example: `n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[2,3,3],[6,3,3],[3,4,2],[4,5,3],[5,6,2]]`

1.  **Initialization:**
    *   `dist = [0, INF, INF, INF, INF, INF, INF]`
    *   `ways = [1, 0, 0, 0, 0, 0, 0]`
    *   `pq = [(0, 0)]`

2.  **Iteration 1:**
    *   `curr = (0, 0)`
    *   Neighbors of 0: 1 (time 2), 6 (time 7)
    *   Update `dist[1] = 2`, `ways[1] = 1`, add `(2, 1)` to `pq`
    *   Update `dist[6] = 7`, `ways[6] = 1`, add `(7, 6)` to `pq`
    *   `dist = [0, 2, INF, INF, INF, INF, 7]`
    *   `ways = [1, 1, 0, 0, 0, 0, 1]`
    *   `pq = [(2, 1), (7, 6)]`

3.  **Iteration 2:**
    *   `curr = (2, 1)`
    *   Neighbors of 1: 0 (time 2), 2 (time 3)
    *   `dist[0] = 0`, no change
    *   Update `dist[2] = 2 + 3 = 5`, `ways[2] = 1`, add `(5, 2)` to `pq`
    *   `dist = [0, 2, 5, INF, INF, INF, 7]`
    *   `ways = [1, 1, 1, 0, 0, 0, 1]`
    *   `pq = [(5, 2), (7, 6)]`

4.  **Iteration 3:**
    *   `curr = (5, 2)`
    *   Neighbors of 2: 1 (time 3), 3 (time 3)
    *   `dist[1] = 2`, no change
    *   Update `dist[3] = 5 + 3 = 8`, `ways[3] = 1`, add `(8, 3)` to `pq`
    *   `dist = [0, 2, 5, 8, INF, INF, 7]`
    *   `ways = [1, 1, 1, 1, 0, 0, 1]`
    *   `pq = [(7, 6), (8, 3)]`

5.  **Iteration 4:**
    *   `curr = (7, 6)`
    *   Neighbors of 6: 0 (time 7), 3 (time 3), 5 (time 2)
    *   `dist[0] = 0`, no change
    *   Update `dist[3] = min(8, 7 + 3) = 8`, ways[3] = 1 as newDist == dist[3] ways[3] = (ways[3]+ways[6])%MOD = (1+1)%MOD = 2
    *   add (10, 3) in pq.
    *   Update `dist[5] = min(INF, 7+2) = 9`, ways[5] = 1 as newDist < dist[5] ways[5] = ways[6] = 1
    *   add (9, 5) in pq.
    *   `dist = [0, 2, 5, 8, INF, 9, 7]`
    *   `ways = [1, 1, 1, 2, 0, 1, 1]`

6.  ... The algorithm continues, updating `dist` and `ways` until the priority queue is empty. Finally, `ways[6]` will store the number of shortest paths from 0 to 6.

7.  **Result:** The final value of `ways[6]` is 4 (after all iterations are complete).

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  _O(E log V)_, where E is the number of edges (roads) and V is the number of vertices (cities). This is due to Dijkstra's algorithm with a priority queue.
*   **Space Complexity:** _O(V + E)_, where V is the number of vertices (cities) and E is the number of edges (roads).  _O(V)_  for the distance and ways arrays, and _O(E)_ in the worst case for the adjacency list representation of the graph.
    