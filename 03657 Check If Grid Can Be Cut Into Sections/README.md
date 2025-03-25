# 03657 - Check If Grid Can Be Cut Into Sections
    
**Language:** Java  
**Runtime:** 112 ms (Beats 34.65% of users)  
**Memory:** 127.1 MB (Beats 28.51% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3657 | CHECK IF GRID CAN BE CUT INTO SECTIONS | [LeetCode Problem](https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/) |

---

## 💡 **Problem Explanation**

The problem asks us to determine if a grid, represented by a set of rectangles, can be cut into at least three sections either horizontally or vertically.  Each rectangle in the input array represents a section of the grid. The goal is to check if we can make two cuts either along the x-axis or y-axis such that we have at least three separate sections.

For example:

Input: `n = 1`, `rectangles = [[0,0,1,1],[0,2,1,3],[2,0,3,1],[2,2,3,3]]`

Output: `false`

Explanation: The given rectangles do not allow cutting the grid into three sections neither horizontally nor vertically.

Input: `n = 2`, `rectangles = [[0,0,1,1],[0,2,1,3],[2,0,3,1]]`

Output: `false`

Input: `n = 3`, `rectangles = [[0,0,1,1],[0,2,1,3],[2,0,3,1],[2,2,3,3],[4,0,5,1],[4,2,5,3]]`

Output: `true`

---

## 📊 **Algorithm**

*   Sort the rectangles based on their x-coordinates to check for vertical cuts.
*   Sort the rectangles based on their y-coordinates to check for horizontal cuts.
*   Iterate through the sorted rectangles to determine the number of cuts possible along both axes.
*   Maintain a `prevX` and `prevY` to track the last cut position for x and y axis respectively.
*   Increment `countX` and `countY` whenever a new cut can be made.
*   If either `countX` or `countY` is greater than or equal to 3, return `true`.
*   Return `false` if neither horizontal nor vertical cuts can produce at least three sections.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int[][] forY = rectangles.clone();
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(forY, (a, b) -> Integer.compare(a[1], b[1]));
        int prevX = 0, prevY = 0, countX = 0, countY = 0;
        for (int i = 0; i < rectangles.length; i++) {
            if (prevX <= rectangles[i][0]) {
                countX++;
                prevX = rectangles[i][2];
            } else {
                prevX = Math.max(prevX, rectangles[i][2]);
            }
            if (prevY <= forY[i][1]) {
                countY++;
                prevY = forY[i][3];
            } else {
                prevY = Math.max(prevY, forY[i][3]);
            }
            if (countX >= 3 || countY >= 3) return true;
        }
        return false;
    }
}
```

## 📊 **ASCII Representation**

Let's consider a simplified grid and the sorting based on x and y.

```
Initial Grid:
+-------+-------+
|       |       |
|   A   |   B   |
|       |       |
+-------+-------+
|       |       |
|   C   |   D   |
|       |       |
+-------+-------+
```

Sorting by X (left edge): A, C, B, D
Sorting by Y (top edge): A, B, C, D

## 📊 **WORKING**

Let's walk through the code with the example: `rectangles = [[0,0,1,1],[0,2,1,3],[2,0,3,1],[2,2,3,3]]`

1.  **Initialization**:
    *   `forY` becomes `[[0,0,1,1],[0,2,1,3],[2,0,3,1],[2,2,3,3]]` (a clone of rectangles)
    *   `prevX = 0`, `prevY = 0`, `countX = 0`, `countY = 0`

2.  **Sorting**:
    *   `rectangles` is sorted by x-coordinate: `[[0,0,1,1],[0,2,1,3],[2,0,3,1],[2,2,3,3]]`
    *   `forY` is sorted by y-coordinate: `[[0,0,1,1],[2,0,3,1],[0,2,1,3],[2,2,3,3]]`

3.  **Looping**:

    *   **X-Axis:**

        *   `i = 0`: `rectangles[0] = [0,0,1,1]`. `prevX (0) <= rectangles[0][0] (0)` is true. `countX++` (countX = 1), `prevX = rectangles[0][2] = 1`.
        *   `i = 1`: `rectangles[1] = [0,2,1,3]`. `prevX (1) <= rectangles[1][0] (0)` is false. `prevX = Math.max(1, 1) = 1`.
        *   `i = 2`: `rectangles[2] = [2,0,3,1]`. `prevX (1) <= rectangles[2][0] (2)` is true. `countX++` (countX = 2), `prevX = rectangles[2][2] = 3`.
        *   `i = 3`: `rectangles[3] = [2,2,3,3]`. `prevX (3) <= rectangles[3][0] (2)` is false. `prevX = Math.max(3, 3) = 3`.

    *   **Y-Axis:**

        *   `i = 0`: `forY[0] = [0,0,1,1]`. `prevY (0) <= forY[0][1] (0)` is true. `countY++` (countY = 1), `prevY = forY[0][3] = 1`.
        *   `i = 1`: `forY[1] = [2,0,3,1]`. `prevY (1) <= forY[1][1] (0)` is false. `prevY = Math.max(1, 1) = 1`.
        *   `i = 2`: `forY[2] = [0,2,1,3]`. `prevY (1) <= forY[2][1] (2)` is true. `countY++` (countY = 2), `prevY = forY[2][3] = 3`.
        *   `i = 3`: `forY[3] = [2,2,3,3]`. `prevY (3) <= forY[3][1] (2)` is false. `prevY = Math.max(3, 3) = 3`.

4.  **Check**: `countX >= 3` (2 >= 3) and `countY >= 3` (2 >= 3) are both false.

5.  **Return**: `false`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**:  The time complexity is dominated by the sorting operations. We sort two arrays of size `n` (number of rectangles). Therefore, the time complexity is **O(n log n)**.
*   **Space Complexity**: The space complexity is **O(n)** because we create a clone of the input array `rectangles` called `forY`. The sorting is done in place, but creating the clone takes O(n) space.
    