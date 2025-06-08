# 00207 - Course Schedule
    
**Language:** Java  
**Runtime:** 6 ms (Beats 73.92% of users)  
**Memory:** 45.2 MB (Beats 70.23% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 207 | COURSE SCHEDULE | [LeetCode Problem](https://leetcode.com/problems/course-schedule/) |

---

## 💡 **Problem Explanation**

The "Course Schedule" problem asks us to determine if it is possible to finish all courses you must take, given `numCourses` (the total number of courses, labeled from `0` to `numCourses-1`) and an array `prerequisites` where each `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` first if you want to take course `ai`. Essentially, we need to check if there is a cycle in the course dependency graph.  If there's a cycle, it's impossible to finish all courses.

**Example:**

*   **Input:** `numCourses = 2`, `prerequisites = [[1,0],[0,1]]`
*   **Output:** `false` (There's a cycle: 1 -> 0 -> 1)

*   **Input:** `numCourses = 2`, `prerequisites = [[0,1]]`
*   **Output:** `true` (You can take course 1 first, then course 0)

## 📊 **Algorithm**

*   Build an adjacency list representation of the course dependencies (graph).
*   Calculate the in-degree of each course (number of prerequisites).
*   Add all courses with an in-degree of 0 to a queue (these are courses we can start with).
*   While the queue is not empty:
    *   Remove a course from the queue.
    *   Decrement the in-degree of all its neighbors (courses that depend on this course).
    *   If any neighbor's in-degree becomes 0, add it to the queue.
*   If the number of courses we were able to take (courses added to the queue) equals `numCourses`, then it's possible to finish all courses. Otherwise, there's a cycle, and it's not possible.

## 🔥 **Code Implementation**

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

## 📊 **ASCII Representation**

N/A - This problem is best visualized as a directed graph rather than a grid.

## 📊 **TABLE Representation**

Consider the input `numCourses = 4`, `prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

| Course | Prerequisites | In-degree |
|---|---|---|
| 0 | - | 0 |
| 1 | 0 | 1 |
| 2 | 0 | 1 |
| 3 | 1, 2 | 2 |

## 📊 **WORKING**

Let's trace the execution with the example: `numCourses = 4`, `prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

1.  **Build Graph and In-degree:**

    *   `graph`:
        *   0: \[1, 2]
        *   1: \[3]
        *   2: \[3]
        *   3: \[ ]
    *   `indegree`: \[0, 1, 1, 2]
2.  **Initialize Queue:** `q = [0]` (course 0 has in-degree 0)
3.  **Process Queue:**
    *   `destNode = 0`
    *   Neighbors of 0 are 1 and 2.
    *   `indegree[1]` becomes 0, `q.add(1)`
    *   `indegree[2]` becomes 0, `q.add(2)`
    *   `q = [1, 2]`
    *   `destNode = 1`
    *   Neighbor of 1 is 3.
    *   `indegree[3]` becomes 1
    *   `q = [2]`
    *   `destNode = 2`
    *   Neighbor of 2 is 3
    *   `indegree[3]` becomes 0
    *   `q.add(3)`
    *   `q = [3]`
    *   `destNode = 3`
    *   3 has no neighbors.
    *   `q = []`
4.  `added = 0` (since the loop will iterate 4 times and initial value is 4)

Since `added == 0`, we return `true`.  All courses can be finished.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(V + E)**, where V is the number of courses (`numCourses`) and E is the number of dependencies (`prerequisites.length`). We traverse all courses and dependencies to build the graph and process the queue.
*   **Space Complexity:** **O(V + E)**. We use an adjacency list to represent the graph, which takes O(V + E) space, and an in-degree array of size V, taking O(V) space. The queue can, in the worst case, contain all courses, taking O(V) space.
    