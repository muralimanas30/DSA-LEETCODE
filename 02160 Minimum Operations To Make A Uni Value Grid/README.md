# 02160 - Minimum Operations To Make A Uni Value Grid
    
**Language:** Java  
**Runtime:** 126 ms (Beats 8.54% of users)  
**Memory:** 74.7 MB (Beats 61.31% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2160 | MINIMUM OPERATIONS TO MAKE A UNI VALUE GRID | [LeetCode Problem](https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/) |

---

## 💡 **Problem Explanation**

Given a 2D integer grid and an integer `x`, the task is to determine the minimum number of operations needed to make all elements in the grid equal. In each operation, you can either add or subtract `x` from any element in the grid. If it is not possible to make all elements equal, return `-1`.

For example:

Input: `grid = [[2,4],[6,8]], x = 2`
Output: `4`

Explanation: We can make all elements equal to `4`.
- Convert 2 to 4: 1 operation (2 + 2 = 4)
- Convert 6 to 4: 1 operation (6 - 2 = 4)
- Convert 8 to 4: 2 operations (8 - 2 - 2 = 4)
Total operations: 1 + 1 + 2 = 4

Input: `grid = [[1,2],[3,4]], x = 2`
Output: `-1`

Explanation: It is not possible to make all elements equal by adding or subtracting 2.

---

## 📊 **Algorithm**
*   Flatten the 2D grid into a list.
*   Sort the list.
*   Check if the difference between any two elements is divisible by `x`. If not, return `-1`.
*   Find the median of the sorted list.
*   Calculate the number of operations for each element to reach the median. The number of operations is the absolute difference between the element and the median divided by `x`.
*   Return the total number of operations.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> values = new ArrayList<>();
        
        for (int[] row : grid) {
            for (int val : row) {
                values.add(val);
            }
        }

        Collections.sort(values);

        // Check if the difference between any two elements is divisible by x
        for (int val : values) {
            if (Math.abs(val - values.get(0)) % x != 0) {
                return -1;
            }
        }

        // Find the median
        int median = values.get(values.size() / 2);
        int operations = 0;

        // Calculate total operations
        for (int val : values) {
            operations += Math.abs(val - median) / x;
        }

        return operations;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids in a way that benefits from ASCII representation. The core logic revolves around calculations on a flattened list.

## 📊 **WORKING**

Let's consider the input: `grid = [[2,4],[6,8]], x = 2`.

1.  **Flatten the Grid:**
    `values = [2, 4, 6, 8]`

2.  **Sort the List:**
    `values = [2, 4, 6, 8]` (already sorted)

3.  **Check Divisibility by x:**
    *   | Value | Difference from 2 | Divisible by 2? |
        |-------|--------------------|-----------------|
        | 2     | 0                  | Yes             |
        | 4     | 2                  | Yes             |
        | 6     | 4                  | Yes             |
        | 8     | 6                  | Yes             |

    Since all differences are divisible by 2, we proceed.

4.  **Find Median:**
    `median = values[4 / 2] = values[2] = 6` if size is 4 median is (n/2 - 1 + n/2) / 2 = (4+6)/2 = 5 when even
    In our case, lets pick either 4 or 6, it does not affect operations.
    Median = 4

5.  **Calculate Operations:**
    *   | Value | Absolute Difference | Operations |
        |-------|---------------------|------------|
        | 2     | 2                   | 1          |
        | 4     | 0                   | 0          |
        | 6     | 2                   | 1          |
        | 8     | 4                   | 2          |

    Total operations = 1 + 0 + 1 + 2 = 4

Therefore, the output is `4`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**
    *   Flattening the grid takes O(m * n) time, where m and n are the dimensions of the grid.
    *   Sorting the list takes O(N log N) time, where N = m * n is the number of elements in the grid.
    *   Checking divisibility takes O(N) time.
    *   Calculating operations takes O(N) time.
    *   Therefore, the overall time complexity is **O(m*n log(m*n))**.

*   **Space Complexity:**
    *   The space complexity is **O(m*n)** because we store the flattened grid in a list.
    