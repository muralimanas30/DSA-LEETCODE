# 00207 - Course Schedule

**Language:** Java  
**Runtime:** 6 ms (Beats 73.74% of users)  
**Memory:** 45.2 MB (Beats 70.13% of users)  

---

## 📎 External Link

- [LeetCode Problem 207: Course Schedule](https://leetcode.com/problems/course-schedule/)

---

## 📝 Problem Statement

Given `numCourses` (an integer representing the total number of courses labeled from `0` to `numCourses - 1`) and an array `prerequisites` where each `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` before course `ai`, determine if it is possible to finish all courses.  
This is equivalent to checking if the directed graph formed by the prerequisites contains a cycle. If a cycle exists, it is impossible to finish all courses.

---

## 📚 Example Inputs & Outputs

| Input | Output | Explanation |
|-------|--------|-------------|
| `numCourses = 2, prerequisites = [[1,0]]` | `true` | You can take course 0, then course 1. |
| `numCourses = 2, prerequisites = [[1,0],[0,1]]` | `false` | There is a cycle: 0 → 1 → 0. Impossible to finish all courses. |
| `numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]` | `true` | All courses can be completed in some order. |

---

## 🏆 Solution Overview

This problem is a classic example of **cycle detection in a directed graph**.  
We use **Kahn's Algorithm** (BFS-based topological sort) to check for cycles:

- **Why this approach?**  
  - Efficiently detects cycles in a directed graph.
  - Allows us to process courses in an order that respects prerequisites.
  - If all courses can be processed (i.e., no cycle), return `true`; otherwise, return `false`.

---

## 🧩 Variables & Data Structures

| Name         | Type                              | Purpose                                                                 |
|--------------|-----------------------------------|-------------------------------------------------------------------------|
| `graph`      | `ArrayList<ArrayList<Integer>>`   | Adjacency list representing course dependencies.                        |
| `indegree`   | `int[]`                           | Array storing the number of prerequisites (incoming edges) for each course. |
| `q`          | `Queue<Integer>`                  | Queue for BFS traversal; holds courses with no remaining prerequisites.  |
| `added`      | `int`                             | Counter for the number of courses left to process.                      |
| `numCourses` | `int`                             | Total number of courses.                                                |
| `prerequisites` | `int[][]`                      | List of prerequisite pairs.                                             |

---

## 🛠️ Step-by-Step Algorithm

1. **Build the Graph:**
   - Create an adjacency list for all courses.
   - For each prerequisite pair `[a, b]`, add an edge from `b` to `a` and increment `indegree[a]`.

2. **Initialize the Queue:**
   - Add all courses with `indegree == 0` (no prerequisites) to the queue.

3. **Process Courses (BFS):**
   - While the queue is not empty:
     - Remove a course from the queue.
     - For each neighbor (dependent course), decrement its `indegree`.
     - If a neighbor's `indegree` becomes 0, add it to the queue.

4. **Check for Cycles:**
   - If all courses are processed (`added == 0`), return `true`.
   - Otherwise, a cycle exists; return `false`.

---

## 🎨 Visual Diagrams

### Graph Example

For `numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]`:

```
      0
     / \
    v   v
   1     2
    \   /
     v v
      3
```

### Table Representation

| Course | Prerequisites | In-degree |
|--------|---------------|-----------|
| 0      | -             | 0         |
| 1      | 0             | 1         |
| 2      | 0             | 1         |
| 3      | 1, 2          | 2         |

---

## 🧮 Step-by-Step Walkthrough

**Input:** `numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

1. **Build Graph & In-degree:**
   - `graph = [[1, 2], [3], [3], []]`
   - `indegree = [0, 1, 1, 2]`

2. **Initialize Queue:**
   - Courses with `indegree == 0`: `[0]`
   - `added = 3` (since course 0 is ready to take)

3. **BFS Traversal:**
   - Dequeue `0`: neighbors `1`, `2` → `indegree[1]=0`, `indegree[2]=0`, queue becomes `[1,2]`, `added=1`
   - Dequeue `1`: neighbor `3` → `indegree[3]=1`
   - Dequeue `2`: neighbor `3` → `indegree[3]=0`, queue becomes `[3]`, `added=0`
   - Dequeue `3`: no neighbors

4. **Result:**  
   - All courses processed (`added == 0`) → **return `true`**

---

## 💻 Java Code Implementation

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for(int i=0;i<numCourses;i++)
            graph.add(new ArrayList<>());
        for(int[] needed : prerequisites){
            int u = needed[0];
            int v = needed[1];
            indegree[v]++;
            graph.get(u).add(v);
        }
        int added = numCourses;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++)
            if(indegree[i]==0){
                q.add(i);
                added--;
            }
        while(!q.isEmpty()){
            int destNode = q.poll();
            for(int adj : graph.get(destNode))
                if(--indegree[adj]==0){
                    q.add(adj);
                    added--;
                }
        }
        return added==0;
    }
}
```

---

## 🛠️ Programming Workflow

### Logical Flow (Numbered List)

1. Initialize adjacency list and in-degree array.
2. Populate the graph and in-degree array from prerequisites.
3. Add all courses with zero in-degree to the queue.
4. While the queue is not empty:
    - Dequeue a course.
    - For each neighbor, decrement in-degree.
    - If in-degree becomes zero, enqueue neighbor.
5. If all courses are processed, return `true`; else, return `false`.

### Flowchart (ASCII Art)

```
+-----------------------------+
| 1. Build graph & indegree   |
+-------------+---------------+
              |
              v
+-----------------------------+
| 2. Enqueue courses with     |
|    indegree == 0            |
+-------------+---------------+
              |
              v
+-----------------------------+
| 3. While queue not empty:   |
|   a. Dequeue course         |
|   b. For each neighbor:     |
|      - Decrement indegree   |
|      - If indegree==0,      |
|        enqueue neighbor     |
+-------------+---------------+
              |
              v
+-----------------------------+
| 4. All courses processed?   |
|   Yes -> return true        |
|   No  -> return false       |
+-----------------------------+
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** `O(V + E)`  
  - V = number of courses, E = number of prerequisites.
  - Each course and edge is processed once.

- **Space Complexity:** `O(V + E)`  
  - For the adjacency list and in-degree array.

---

## 📚 References

- [Kahn's Algorithm (Wikipedia)](https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm)
- [LeetCode Discuss: Course Schedule](https://leetcode.com/problems/course-schedule/discuss/)
- [Topological Sorting (GeeksforGeeks)](https://www.geeksforgeeks.org/topological-sorting/)