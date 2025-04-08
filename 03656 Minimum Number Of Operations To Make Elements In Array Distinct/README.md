# 03656 - Minimum Number Of Operations To Make Elements In Array Distinct
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 44.1 MB (Beats 84.84% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| 3656 | Minimum Number of Operations to Make Elements in Array Distinct | [LeetCode Problem](https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/) |

---

## 💡 **Problem Explanation**

Given an array of integers `nums`, we want to make all elements distinct by performing the minimum number of operations. In each operation, we can increment any element of the array by 1.  The task is to find the minimum number of such operations.

Let's consider an example:

**Input:** `nums = [1, 2, 2, 3]`
**Output:** `1`

**Explanation:** We need to increment one of the 2's to either 0 or 4 to make all elements distinct. Hence the minimum number of operations is 1.

**Input:** `nums = [5, 5, 5, 5]`
**Output:** `1`

**Explanation:** We need increment index 1 to 6, index 2 to 7 ,  make all elements distinct. Hence the minimum number of operations is 1.

---

## 📊 **Algorithm**

*   Create a boolean array `vis` of size 128 to keep track of visited numbers.
*   Iterate through the `nums` array from right to left.
*   For each element, check if it has been visited.
    *   If the element has been visited, it means a duplicate has been found.
    *   Calculate the number of operations needed to make this element distinct: `i / 3 + 1`.  This is based on the index
    *   Return the number of operations.
*   If no duplicates are found, return 0.

## 🔥 **Code Implementation**

```java
class Solution {

    public int minimumOperations(int[] nums) {
        boolean[] vis = new boolean[128];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (vis[nums[i]]) {
                return i / 3 + 1;
            }
            vis[nums[i]] = true;
        }
        return 0;
    }
}
```

## 📊 **ASCII Representation**

N/A (This problem does not involve grids, trees, or movements)

## 📊 **WORKING**

Let's walk through an example: `nums = [1, 2, 2, 3]`

1.  Initialize `vis` array of size 128 with all `false`.
2.  Iterate from right to left:
    *   `i = 3`, `nums[3] = 3`. `vis[3]` is `false`. Set `vis[3] = true`.
    *   `i = 2`, `nums[2] = 2`. `vis[2]` is `false`. Set `vis[2] = true`.
    *   `i = 1`, `nums[1] = 2`. `vis[2]` is `true` (duplicate found).
        *   Return `1 / 3 + 1 = 0 + 1 = 1`.

Thus, the minimum number of operations is 1.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n), where n is the length of the `nums` array.  The loop iterates through the array once.
*   **Space Complexity:** O(1). The size of `vis` array is fixed (128), which means constant space.
    