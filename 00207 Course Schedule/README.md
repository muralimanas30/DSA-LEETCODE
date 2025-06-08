# 00207 - Course Schedule

**Language:** Java  
**Runtime:** 6 ms (Beats 73.92% of users)  
**Memory:** 45.2 MB (Beats 70.23% of users)  

---

## 📝 Problem Statement

Given `numCourses` (numbered from `0` to `numCourses - 1`) and a list of prerequisite pairs `prerequisites`, determine if it is possible to finish all courses. Each prerequisite pair `[a, b]` means you must take course `b` before course `a`. The task is to check if the course dependency graph contains a cycle.

**Example 1:**  
- Input: `numCourses = 2`, `prerequisites = [[1,0],[0,1]]`  
- Output: `false`  
- Explanation: There is a cycle (0 → 1 → 0), so it's impossible to finish all courses.

**Example 2:**  
- Input: `numCourses = 2`, `prerequisites = [[0,1]]`  
- Output: `true`  
- Explanation: You can take course 1, then course 0.

---

## 💡 Approach Overview

This problem is a classic application of **cycle detection in a directed graph**. If the dependency graph has a cycle, not all courses can be completed. We use **Kahn's Algorithm** (BFS-based topological sort) to detect cycles.

---

## 🧩 Variable & Data Structure Explanation

| Variable      | Type                               | Description                                                                 |
|---------------|------------------------------------|-----------------------------------------------------------------------------|
| `numCourses`  | `int`                             | Total number of courses                                                     |
| `prerequisites` | `int[][]`                       | List of prerequisite pairs `[a, b]`                                         |
| `graph`       | `ArrayList<ArrayList<Integer>>`    | Adjacency list: `graph.get(i)` lists courses that depend on course `i`      |
| `indegree`    | `int[]`                           | `indegree[i]` is the number of prerequisites for course `i`                 |
| `q`           | `Queue<Integer>`                  | Queue for BFS traversal (courses with no remaining prerequisites)           |
| `added`       | `int`                             | Number of courses left to process (should be 0 if all can be finished)      |

---

## 📊 Algorithm Steps

1. **Build the Graph:**
   - Initialize an adjacency list `graph` for all courses.
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
   - If not, a cycle exists; return `false`.

---

## 📈 Visual Diagram

### Example: `numCourses = 4`, `prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

**Dependency Graph:**

```
    0
   / \
  1   2
   \ /
    3
```

- Course 0 is a prerequisite for 1 and 2.
- Courses 1 and 2 are prerequisites for 3.

**Adjacency List:**
| Course | Neighbors   |
|--------|-------------|
| 0      | 1, 2        |
| 1      | 3           |
| 2      | 3           |
| 3      |             |

**In-degree Array:**
| Course | In-degree   |
|--------|-------------|
| 0      | 0           |
| 1      | 1           |
| 2      | 1           |
| 3      | 2           |

---

## 🧮 Step-by-Step Execution

1. **Initialization:**
   - `graph = [[1,2], [3], [3], []]`
   - `indegree = [0,1,1,2]`
   - `q = [0]` (only course 0 has no prerequisites)
   - `added = 4`

2. **Processing:**
   - Pop 0: add 1 and 2 to queue (`indegree[1] = 0`, `indegree[2] = 0`), `added = 2`
   - Pop 1: decrement `indegree[3]` to 1
   - Pop 2: decrement `indegree[3]` to 0, add 3 to queue, `added = 1`
   - Pop 3: no neighbors, `added = 0`

3. **Result:**
   - All courses processed (`added == 0`), so return `true`.

---

## 🔥 Code Implementation

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

## 🧭 Programming Workflow

The following outlines the logical flow of the Java implementation:

1. **Graph and Indegree Initialization**
   - Create an adjacency list `graph` to represent course dependencies.
   - Initialize an `indegree` array to count prerequisites for each course.

2. **Build the Graph**
   - For each prerequisite pair `[a, b]`:
     - Add an edge from course `u` to course `v` (`graph.get(u).add(v)`).
     - Increment `indegree[v]` to reflect the new prerequisite.

3. **Queue Initialization**
   - Add all courses with `indegree == 0` (no prerequisites) to the BFS queue.
   - Decrement `added` for each course added to the queue.

4. **Breadth-First Search (BFS) Traversal**
   - While the queue is not empty:
     - Remove a course (`destNode`) from the queue.
     - For each neighbor (dependent course) of `destNode`:
       - Decrement its `indegree`.
       - If `indegree` becomes 0, add it to the queue and decrement `added`.

5. **Cycle Detection and Result**
   - After BFS, if `added == 0`, all courses have been processed (no cycle): return `true`.
   - If `added > 0`, a cycle exists: return `false`.

---

## 🗂️ Programming Flowchart

Below is a simple flowchart representing the main logic:

```
+-----------------------------+
|  Initialize graph & indegree|
+-------------+---------------+
              |
              v
+-----------------------------+
|  Build graph & fill indegree|
+-------------+---------------+
              |
              v
+-----------------------------+
|  Add courses with           |
|  indegree == 0 to queue     |
+-------------+---------------+
              |
              v
+-----------------------------+
|  While queue not empty:     |
|    - Pop course             |
|    - For each neighbor:     |
|        - Decrement indegree |
|        - If indegree==0,    |
|          add to queue       |
|        - Decrement added    |
+-------------+---------------+
              |
              v
+-----------------------------+
|  If added == 0:             |
|    return true              |
|  else:                      |
|    return false             |
+-----------------------------+
```

This workflow ensures that all courses can be completed only if there are no cycles in the dependency graph.

---

## 🚀 Complexity Analysis

- **Time Complexity:** O(V + E), where V = number of courses, E = number of prerequisite pairs.
- **Space Complexity:** O(V + E) for the adjacency list, in-degree array, and queue.

---

## 📚 References

- [Kahn's Algorithm - Topological Sorting](https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm)
- [LeetCode Problem 207 - Course Schedule](https://leetcode.com/problems/course-schedule/)

---