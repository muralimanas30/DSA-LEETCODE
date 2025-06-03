# 01424 - Maximum Candies You Can Get From Boxes
    
**Language:** Java  
**Runtime:** 2 ms (Beats 98.51% of users)  
**Memory:** 56.9 MB (Beats 62.69% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 1424 | Maximum Candies You Can Get From Boxes | [LeetCode Problem](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/) |

---

## 💡 **Problem Explanation**

Imagine you have a collection of boxes. Each box might contain candies, keys to other boxes, and even more boxes inside! You start with some initial boxes. You can only open a box if you have its key or if you already own it. The goal is to maximize the number of candies you can collect by opening boxes you can access.

Let's consider a simple example:

**Input:**
`status = [1,0,1,0]`, `candies = [7,5,4,100]`, `keys = [[],[],[1],[]]`, `containedBoxes = [[1,2],[3],[],[]]`, `initialBoxes = [0]`

**Explanation:**

-   We start with box 0 (`initialBoxes = [0]`).
-   Box 0 is open (`status[0] = 1`), so we collect 7 candies.
-   Box 0 contains boxes 1 and 2 (`containedBoxes[0] = [1,2]`).
-   We now have boxes 1 and 2 to explore.
-   Box 2 is open (`status[2] = 1`), so we collect 4 candies.
-   Box 2 contains key to box 1 (`keys[2] = [1]`). So we can open box 1 now.
-   Box 1 contains box 3 (`containedBoxes[1] = [3]`).
-   Box 1 gives us key to open box 1.
-   Box 1 is locked (`status[1] = 0`), we can not open it yet.
-   Box 3 is locked (`status[3] = 0`), so we can't open it.
-   The total candies collected are 7 + 4 = 11.  But we need box 1 to access box 3.

**Output:** 11

---

## 📊 **Algorithm**

*   Initialize a queue `toOpen` with the `initialBoxes`.
*   Keep track of opened boxes using a boolean array `boxesOpened`.
*   Maintain a `candyCount` to store the total candies collected.
*   Iterate through the `toOpen` queue:
    *   If the current box can be opened (either `status[currentBoxIndex] == 1` or it was already opened), proceed.
    *   Collect candies from the box and mark it as opened.
    *   Acquire keys from the box and update the `status` array.
    *   Add contained boxes to the `toOpen` queue.
    *   If no boxes were opened in a pass, break the loop to prevent infinite loops.
*   Return the `candyCount`.

## 🔥 **Code Implementation**

```java
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        Deque<Integer> toOpen = new LinkedList<>();
        boolean[] boxesOpened = new boolean[status.length];
        int candyCount = 0;

        for(int i = 0; i < initialBoxes.length; i++)
            toOpen.offer(initialBoxes[i]);

        while(!toOpen.isEmpty()) {

            boolean openedAny = false;
            int size = toOpen.size();

            for(int i = 0; i < size; i++) {
                int currentBoxIndex = toOpen.poll();

                if(status[currentBoxIndex] == 0 || boxesOpened[currentBoxIndex]) {
                    toOpen.offer(currentBoxIndex);
                    continue;
                }

                for(int x : keys[currentBoxIndex]) {
                    status[x] = 1;
                }

                candyCount += candies[currentBoxIndex];
                candies[currentBoxIndex] = 0;
                boxesOpened[currentBoxIndex] = true;
                openedAny = true;

                for(int subBoxIndex : containedBoxes[currentBoxIndex])
                    toOpen.offer(subBoxIndex);
            }

            if (!openedAny) break;
        }

        return candyCount;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees in a visual way that ASCII art would greatly benefit. The key relationships are between boxes and their contents (candies, keys, other boxes), which are better represented through the code and explanation.

## 📊 **WORKING**

Let's trace the execution with the example:
`status = [1,0,1,0]`, `candies = [7,5,4,100]`, `keys = [[],[],[1],[]]`, `containedBoxes = [[1,2],[3],[],[]]`, `initialBoxes = [0]`

1.  **Initialization**: `toOpen = [0]`, `boxesOpened = [false, false, false, false]`, `candyCount = 0`

2.  **Loop 1**: `currentBoxIndex = 0`
    *   `status[0] == 1`, so open box 0.
    *   `candyCount += 7; candyCount = 7`
    *   `boxesOpened[0] = true`
    *   `containedBoxes[0] = [1, 2]`, so `toOpen = [1, 2]`

3.  **Loop 2**: `currentBoxIndex = 1`
    *   `status[1] == 0`, so can't open box 1. `toOpen = [2, 1]`

4.  **Loop 3**: `currentBoxIndex = 2`
    *   `status[2] == 1`, so open box 2.
    *   `candyCount += 4; candyCount = 11`
    *   `boxesOpened[2] = true`
    *   `keys[2] = [1]`, so `status[1] = 1`
    *   `containedBoxes[2] = []`, so `toOpen = [1]`

5.  **Loop 4**: `currentBoxIndex = 1`
    *   `status[1] == 1`, so open box 1.
    *   `candyCount += 5; candyCount = 16`
    *   `boxesOpened[1] = true`
    *   `containedBoxes[1] = [3]`, so `toOpen = [3]`

6.  **Loop 5**: `currentBoxIndex = 3`
    *   `status[3] == 0`, so can't open box 3. `toOpen = [3]`

7.  **Loop 6**:
    *  No boxes opened. Exit.

Final `candyCount = 16`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(N + K), where N is the number of boxes and K is the total number of keys and contained boxes across all boxes. We visit each box at most once, and iterate through its keys and contained boxes.
*   **Space Complexity:** O(N), where N is the number of boxes.  This is due to the `toOpen` queue and the `boxesOpened` boolean array potentially storing all box indices in the worst case.
    