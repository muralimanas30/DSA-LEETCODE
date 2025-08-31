# 00785 - Is Graph Bipartite
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 45.3 MB (Beats 39.96% of users)  

# 📝 LeetCode 785: Is Graph Bipartite?

| 🔢 **Problem Number** | 📌 **Title**             | 🔗 **Link**                                                                                                   |
| :------------------ | :----------------------- | :------------------------------------------------------------------------------------------------------------ |
| 785                 | Is Graph Bipartite?      | [LeetCode Link](https://leetcode.com/problems/is-graph-bipartite/) |

---

## 🧩 Problem Statement

Given an **undirected graph**, determine if it is **bipartite**.

A graph is **bipartite** if we can partition its set of vertices into two independent sets, `A` and `B`, such that every edge in the graph connects a vertex in set `A` to a vertex in set `B`.

In simpler terms, we need to check if we can color the nodes of the graph with two different colors (e.g., Red and Blue) such that no two adjacent nodes have the same color.

### Constraints:

-   `graph.length == n`
-   `1 <= n <= 100`
-   `0 <= graph[i].length < n`
-   `0 <= graph[i][j] <= n - 1`
-   `graph[i][j] != i`
-   All the values `graph[i]` are unique.
-   The graph is undirected.

---

## 💡 Problem Explanation

The core challenge is to determine if the graph can be "2-colored". If we can successfully color every node with one of two colors, say Red ('R') or Blue ('B'), without any two connected nodes sharing the same color, the graph is bipartite. If we find even one edge connecting two nodes of the same color, it's not bipartite.

The graph might also be disconnected, meaning it consists of multiple separate components. Our algorithm must correctly handle this by checking each component independently.

---

## 🧪 Examples

### Example 1: Bipartite Graph

**Input:** `graph = [[1,3],[0,2],[1,3],[0,2]]`
**Output:** `true`

**Explanation:**
We can partition the nodes into two sets: `{0, 2}` and `{1, 3}`.
-   Node 0 (Set A) is connected to 1 and 3 (Set B).
-   Node 1 (Set B) is connected to 0 and 2 (Set A).
-   Node 2 (Set A) is connected to 1 and 3 (Set B).
-   Node 3 (Set B) is connected to 0 and 2 (Set A).
No edge connects two nodes within the same set.

### Example 2: Non-Bipartite Graph

**Input:** `graph = [[1,2,3],[0,2],[0,1,3],[0,2]]`
**Output:** `false`

**Explanation:**
This graph contains a "triangle" (an odd-length cycle) formed by nodes 0, 1, and 2.
-   Let's color node 0 Red.
-   Its neighbors, 1 and 2, must be Blue.
-   However, nodes 1 and 2 are also connected to each other. Since they are both Blue, this violates the bipartite condition.

---

## 🧭 Algorithm Overview

The problem is a classic application of graph traversal algorithms, specifically **Depth-First Search (DFS)**. The strategy is to model this as a 2-coloring problem.

-   **Graph Coloring**: We'll use an array, `colours`, to keep track of the color of each node. We can use three states: uncolored, Color 'R', and Color 'B'.
-   **DFS Traversal**: We will traverse the graph using DFS. When we visit an uncolored node, we assign it a color (e.g., 'R'). Then, we recursively visit all its neighbors and assign them the opposite color (e.g., 'B').
-   **Conflict Detection**: During the traversal, if we encounter a neighbor that has already been colored, we check its color. If it has the *same* color as the current node, we have found a conflict, and the graph is not bipartite. If its color is the opposite, we continue.
-   **Handling Disconnected Components**: The main function iterates through all nodes. If a node hasn't been visited (i.e., colored) yet, it means it belongs to a new, undiscovered component of the graph, and we start a new DFS from there.

This approach is chosen because DFS naturally explores connected components of a graph, and its recursive structure makes it easy to propagate color assignments and check for conflicts.

---

## 🧱 Variables & Data Structures

| Variable  | Type       | Description                                                                        |
| :-------- | :--------- | :--------------------------------------------------------------------------------- |
| `graph`   | `int[][]`  | The input graph represented as an adjacency list.                                  |
| `n`       | `int`      | The total number of nodes in the graph.                                            |
| `colours` | `char[]`   | An array to store the color of each node. `'\0'` means uncolored, 'R'/'B' for colors. |
| `node`    | `int`      | The current node being processed in the `dfs` function.                            |
| `colour`  | `char`     | The color that should be assigned to the current `node`.                           |
| `neighbor`| `int`      | A node adjacent to the current `node`.                                             |

---

## 🧮 ASCII Representation

Let's visualize the graph from **Example 1**: `graph = [[1,3],[0,2],[1,3],[0,2]]`.

This graph forms a square (a cycle of length 4).

```
      (R)
       0 ----------- 1 (B)
       |             |
       |             |
       3 ----------- 2
      (B)           (R)
```

We can color node 0 Red, its neighbors 1 and 3 Blue, and node 2 (neighbor of 1 and 3) Red. This coloring is valid.

Now, let's visualize the graph from **Example 2**: `graph = [[1,2,3],[0,2],[0,1,3],[0,2]]`.

This graph has an inner triangle `0-1-2`.

```
      (R) ?
       0 ----------- 1 (B) ?
       | \         / |
       |  \       /  |
       |   \     /   |
       |    \   /    |
       |     \ /     |
       3 --- 2 (B) ?
```
If we color 0 Red, then 1 and 2 must be Blue. But 1 and 2 are connected, creating a Blue-Blue edge, which is a conflict.

---

## 🧪 Working Demo

Let's trace the algorithm with `graph = [[1,3],[0,2],[1,3],[0,2]]`.

1.  **Initialization**: `n = 4`, `colours = ['\0', '\0', '\0', '\0']`.
2.  The main loop starts at `i = 0`. `colours[0]` is `'\0'`, so we call `dfs(graph, 0, 'R', colours)`.
3.  **`dfs(node=0, colour='R')`**:
    *   `colours[0]` is uncolored. Set `colours[0] = 'R'`.
    *   `colours` state: `['R', '\0', '\0', '\0']`.
    *   Explore neighbors of 0: `[1, 3]`.
    *   **Call `dfs(node=1, colour='B')`**:
        *   `colours[1]` is uncolored. Set `colours[1] = 'B'`.
        *   `colours` state: `['R', 'B', '\0', '\0']`.
        *   Explore neighbors of 1: `[0, 2]`.
        *   Check neighbor 0: `colours[0]` is `'R'`. Expected color is `'R'`. This is fine. Continue.
        *   **Call `dfs(node=2, colour='R')`**:
            *   `colours[2]` is uncolored. Set `colours[2] = 'R'`.
            *   `colours` state: `['R', 'B', 'R', '\0']`.
            *   Explore neighbors of 2: `[1, 3]`.
            *   Check neighbor 1: `colours[1]` is `'B'`. Expected color is `'B'`. Fine.
            *   **Call `dfs(node=3, colour='B')`**:
                *   `colours[3]` is uncolored. Set `colours[3] = 'B'`.
                *   `colours` state: `['R', 'B', 'R', 'B']`.
                *   Explore neighbors of 3: `[0, 2]`.
                *   Check neighbor 0: `colours[0]` is `'R'`. Expected is `'R'`. Fine.
                *   Check neighbor 2: `colours[2]` is `'R'`. Expected is `'R'`. Fine.
                *   No more neighbors. `dfs(3)` returns `true`.
            *   No more neighbors for 2. `dfs(2)` returns `true`.
        *   No more neighbors for 1. `dfs(1)` returns `true`.
    *   Back to `dfs(0)`. Explore next neighbor 3.
    *   Check neighbor 3: `colours[3]` is `'B'`. Expected color is `'B'`. Fine.
    *   No more neighbors for 0. `dfs(0)` returns `true`.
4.  The call from the main loop returns `true`. The main loop continues for `i = 1, 2, 3`, but since all nodes are now colored, the `if` condition is false.
5.  The loop finishes. The function returns `true`.

### 🗂️ Final `colours` Array State

| Node Index | 0     | 1     | 2     | 3     |
| :--------: | :---: | :---: | :---: | :---: |
| Color      | `'R'` | `'B'` | `'R'` | `'B'` |

---

## 🧰 Programming Workflow

### Numbered List

1.  **Initialize**: In `isBipartite`, get the number of nodes `n` and create a `colours` array of size `n`, initialized with a null character `\0` to mark all nodes as uncolored.
2.  **Iterate Over All Nodes**: Loop from `i = 0` to `n-1`. This ensures that we check all nodes, covering disconnected components of the graph.
3.  **Check if Uncolored**: Inside the loop, if `colours[i]` is still `\0`, it means we've found a new, uncolored component.
4.  **Start DFS**: Begin a DFS traversal from this node `i` by calling `dfs(graph, i, 'R', colours)`. We arbitrarily start with color 'R'.
5.  **Check for Conflict**: If the `dfs` call returns `false`, it means a conflict was found in that component. Immediately return `false` from `isBipartite`.
6.  **`dfs` Logic**:
    a.  **Base Case (Already Colored)**: If the current `node` is already colored (`colours[node] != '\0'`), check if its current color matches the `colour` it's *supposed* to have. If they don't match, return `false`. If they do, return `true`.
    b.  **Color the Node**: If the node is uncolored, assign it the given `colour`.
    c.  **Recursive Step**: Iterate through all `neighbors` of the current `node`. For each `neighbor`, recursively call `dfs` with the opposite color (`colour == 'R' ? 'B' : 'R'`).
    d.  **Propagate Failure**: If any of the recursive calls return `false`, immediately stop and return `false`.
7.  **Return Success**: If the loops in both `isBipartite` and `dfs` complete without returning `false`, it means no conflicts were found. `isBipartite` returns `true`.

### ASCII Flowchart

```mermaid
graph TD
    A[Start isBipartite] --> B{Initialize colours array};
    B --> C{i = 0};
    C --> D{i < n?};
    D -- Yes --> E{colours[i] == uncolored?};
    D -- No --> L[Return true];
    
    E -- Yes --> F[Call dfs(i, 'R')];
    E -- No --> K{i++};
    K --> C;

    F --> G{dfs returned false?};
    G -- Yes --> H[Return false];
    G -- No --> K;

    subgraph DFS Function
        S_DFS[Start dfs(node, color)] --> C1{node is colored?};
        C1 -- Yes --> C2{colours[node] == color?};
        C2 -- Yes --> R_T[Return true];
        C2 -- No --> R_F[Return false];
        C1 -- No --> P1[Color node with 'color'];
        P1 --> L1{For each neighbor};
        L1 --> L2{Call dfs(neighbor, opposite_color)};
        L2 --> C3{dfs returned false?};
        C3 -- Yes --> R_F;
        C3 -- No --> L1;
        L1 -- End Loop --> R_T;
    end
```
*Note: Mermaid flowchart for visualization.*

---

## 🔥 Code Implementation

```java
/**
 * Solution using Depth-First Search (DFS) for 2-coloring the graph.
 */
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // colours array stores the color of each node.
        // '\0' (null) means uncolored.
        // 'R' and 'B' are the two colors.
        char[] colours = new char[n];

        // Iterate through all nodes to handle disconnected graphs.
        for (int i = 0; i < n; i++) {
            // If a node is uncolored, start a DFS from it.
            if (colours[i] == '\0') {
                if (!dfs(graph, i, 'R', colours)) {
                    // If DFS finds a conflict, the graph is not bipartite.
                    return false;
                }
            }
        }
        // If all components are successfully 2-colored, the graph is bipartite.
        return true;
    }

    private boolean dfs(int[][] graph, int node, char colour, char[] colours) {
        // If the node is already colored, check for conflicts.
        if (colours[node] != '\0') {
            // If the existing color is different from the expected color, it's a conflict.
            return colours[node] == colour;
        }

        // Color the current node.
        colours[node] = colour;

        // Recursively visit all neighbors.
        for (int neighbor : graph[node]) {
            // Assign the opposite color to the neighbor.
            if (!dfs(graph, neighbor, colour == 'R' ? 'B' : 'R', colours)) {
                // If a conflict is found in any sub-branch, propagate the failure.
                return false;
            }
        }
        
        // If no conflicts were found in this path, return true.
        return true;
    }
}
```

---

## 🚀 Time & Space Complexity

-   **Time Complexity:** **O(V + E)**
    -   Where `V` is the number of vertices (nodes) and `E` is the number of edges.
    -   The algorithm visits each vertex and each edge exactly once during the DFS traversal across all components.

-   **Space Complexity:** **O(V)**
    -   `O(V)` for the `colours` array to store the state of each vertex.
    -   `O(V)` for the recursion call stack in the worst-case scenario (for a skewed graph like a long path). Therefore, the total space complexity is dominated by `V`.

---

## 🔗 References

-   [Wikipedia: Bipartite Graph](https://en.wikipedia.org/wiki/Bipartite_graph)
-   [GeeksforGeeks: Check if a graph is bipartite or not](https://www.geeksforgeeks.org/bipartite-graph/)
-   [Graph Traversal Algorithms (DFS/BFS)](https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/breadth-first-search-and-depth-first-search)
    