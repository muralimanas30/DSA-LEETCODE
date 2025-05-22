# 03639 - Zero Array Transformation I
    
**Language:** Java  
**Runtime:** 3 ms (Beats 85.06% of users)  
**Memory:** 95.9 MB (Beats 29.06% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title                       | 🔗 Link                                                                    |
| ------------------ | ----------------------------- | ------------------------------------------------------------------------- |
| 3639               | ZERO ARRAY TRANSFORMATION I | [LeetCode Problem](https://leetcode.com/problems/zero-array-transformation-i/) |

---

## 💡 **Problem Explanation**

The problem asks us to determine whether it's possible to transform an array `nums` into an array of all zeros by applying a series of range decrements specified by the `queries`. Each query `[start, end]` indicates that we should decrement all elements within the range `start` to `end` (inclusive) by 1. The function should return `true` if it's possible to make all elements zero or `false` otherwise.

**Example:**

Let's say we have `nums = [3, 5, 2, 4]` and `queries = [[0, 2], [1, 3], [0, 1]]`.

Applying the queries:

1.  `[0, 2]`: `nums` becomes `[2, 4, 1, 4]`
2.  `[1, 3]`: `nums` becomes `[2, 3, 0, 3]`
3.  `[0, 1]`: `nums` becomes `[1, 2, 0, 3]`

If after applying all necessary decrements dictated by the queries, all the array element are ZERO, then return `true`.

---

## 📊 **Algorithm**

*   We use a prefix array to keep track of the net decrement that applies to each index of the array due to the queries.
*   Iterate through the queries and update the prefix array to reflect the decrements. The `prefix[start]` is incremented by 1, and `prefix[end+1]` is decremented by 1 (if `end+1` is within bounds).
*   Calculate the actual decrement for each element of `nums` by summing up the prefix values up to that index.
*   For each element in `nums`, check if after applying the decrement, it becomes non-negative. If it becomes negative at any point, it's impossible to make the array zero, so return `false`.
*   If after processing all elements, none became negative, it's possible to make the array zero, so return `true`.

## 🔥 **Code Implementation**

```java
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int[] sub : queries) {
            prefix[sub[0]] += 1;
            if (sub[1] + 1 < prefix.length) {
                prefix[sub[1] + 1] -= 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += prefix[i];
            if (Math.max(0, nums[i] - sum) != 0) return false;
        }

        return true;
    }
}
```

## 📊 **ASCII Representation**

Not applicable as this is not a grid-based problem.

## 📊 **WORKING**

Let's trace the execution with `nums = [3, 5, 2, 4]` and `queries = [[0, 2], [1, 3], [0, 1]]`.

1.  **Initialization:**
    `nums = [3, 5, 2, 4]`
    `prefix = [0, 0, 0, 0, 0]`

2.  **Processing queries:**

    *   `[0, 2]`: `prefix` becomes `[1, 0, 0, -1, 0]`
    *   `[1, 3]`: `prefix` becomes `[1, 1, 0, 0, -1]`
    *   `[0, 1]`: `prefix` becomes `[2, 1, -1, 0, -1]`

3.  **Calculating decrements and checking:**

    *   `i = 0`: `sum = 2`. `nums[0] - sum = 3 - 2 = 1`. `max(0, 1) != 0` (true)
    *   `i = 1`: `sum = 2 + 1 = 3`. `nums[1] - sum = 5 - 3 = 2`. `max(0, 2) != 0` (true)
    *   `i = 2`: `sum = 3 + (-1) = 2`. `nums[2] - sum = 2 - 2 = 0`. `max(0, 0) != 0` (false)
    *   `i = 3`: `sum = 2 + 0 = 2`. `nums[3] - sum = 4 - 2 = 2`. `max(0, 2) != 0` (true)

    Since none of the array elements become negative after applying the net decrements at each index, the loops exits and the program returns `true`

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n + q), where n is the length of `nums` and q is the number of queries in `queries`. This is because we iterate through the queries once and then iterate through `nums` once.
*   **Space Complexity:** O(n), where n is the length of `nums`, because we use a prefix array of size n+1.
    