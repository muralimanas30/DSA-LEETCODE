# 00207 - Course Schedule
    
**Language:** Java  
**Runtime:** 6 ms (Beats 73.74% of users)  
**Memory:** 45.2 MB (Beats 70.13% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 207 | COURSE SCHEDULE | [LeetCode Problem](https://leetcode.com/problems/course-schedule/) |

---

## 💡 **Problem Explanation**

The "Course Schedule" problem asks whether it's possible to finish all courses given a set of prerequisites. You are given `numCourses`, the total number of courses labeled from `0` to `numCourses - 1`, and an array `prerequisites` where each `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` first if you want to take course `ai`.  In essence, you need to determine if a directed graph representing course dependencies contains a cycle. If a cycle exists, it's impossible to finish all courses.

**Example:**

*   **Input:** `numCourses = 2, prerequisites = [[1,0]]`
*   **Output:** `true`
    *   Explanation: There are 2 courses in total. To take course 1 you should have finished course 0. So it is possible.

*   **Input:** `numCourses = 2, prerequisites = [[1,0],[0,1]]`
*   **Output:** `false`
    *   Explanation: There are 2 courses in total. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

## 📊 **Algorithm**

*   Represent the courses and their dependencies as a directed graph, where each course is a node, and a directed edge from course `b` to course `a` exists if course `a` has course `b` as a prerequisite.
*   Calculate the in-degree of each course (the number of incoming edges).
*   Use a queue to store all courses with an in-degree of 0 (courses that have no prerequisites).
*   While the queue is not empty:
    *   Dequeue a course from the queue. This course can be taken.
    *   For each neighbor (dependent course) of the dequeued course, reduce its in-degree by 1.
    *   If a neighbor's in-degree becomes 0, enqueue it, as it can now be taken.
*   After processing all courses, if the number of courses taken (dequeued) is equal to the total number of courses, it's possible to finish all courses. Otherwise, there's a cycle, and it's impossible.

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

Consider `numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

The graph can be visualized as:

```
      0
     / \
    v   v
   1   2
   \   /
    v v
     3
```

## 📊 **TABLE Representation**

Let `numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]`

| Course | Prerequisites | In-degree |
|---|---|---|
| 0 | - | 0 |
| 1 | 0 | 1 |
| 2 | 0 | 1 |
| 3 | 1, 2 | 2 |

## 📊 **WORKING**

Let's trace the execution for the input `numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]`.

1.  **Graph Initialization:**

    *   `graph` is initialized as an adjacency list: `[[], [], [], []]`
    *   `indegree` is initialized as `[0, 0, 0, 0]`

2.  **Building the Graph and In-degree Array:**

    *   `prerequisites = [[1,0],[2,0],[3,1],[3,2]]`
    *   After processing prerequisites:
        *   `graph = [[1, 2], [], [], []]`   (Course 0 has neighbors 1 and 2)
        *   `indegree = [0, 1, 1, 2]`        (Course 1 and 2 have in-degree 1, Course 3 has in-degree 2)

3.  **Initialization:**

    *   `added = 4`
    *   `queue = [0]`  (Courses with in-degree 0 are added)
    *   `added = 3`

4.  **BFS Traversal:**

    *   **Iteration 1:**
        *   `destNode = 0` (Dequeue 0)
        *   Neighbors of 0: 1, 2
        *   `indegree[1]--`: `indegree[1] = 0`
        *   `queue.add(1)`, `added--`: `queue = [1]`, `added = 2`
        *   `indegree[2]--`: `indegree[2] = 0`
        *   `queue.add(2)`, `added--`: `queue = [1, 2]`, `added = 1`

    *   **Iteration 2:**
        *   `destNode = 1` (Dequeue 1)
        *   Neighbors of 1: 3
        *   `indegree[3]--`: `indegree[3] = 1`

    *   **Iteration 3:**
        *   `destNode = 2` (Dequeue 2)
        *   Neighbors of 2: 3
        *   `indegree[3]--`: `indegree[3] = 0`
        *   `queue.add(3)`, `added--`: `queue = [3]`, `added = 0`

    *   **Iteration 4:**
        *   `destNode = 3` (Dequeue 3)
        *   Neighbors of 3: None

5.  **Result:**

    *   `added == 0`: `true`

Therefore, it is possible to finish all courses.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(V + E)**, where V is the number of courses (vertices) and E is the number of prerequisites (edges). We iterate through all courses and prerequisites once.
*   **Space Complexity:** **O(V + E)**, to store the graph as an adjacency list and the in-degree array.
    