# 00088 - Merge Sorted Array
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 42.2 MB (Beats 77.54% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title              | 🔗 Link                                                  |
| :---------------- | :------------------- | :-------------------------------------------------------- |
| 88                | MERGE SORTED ARRAY | [LeetCode Problem](https://leetcode.com/problems/merge-sorted-array/) |

---

## 💡 **Problem Explanation**

Given two sorted integer arrays `nums1` and `nums2`, merge `nums2` into `nums1` as one sorted array.

**Note:**
*   The number of elements initialized in `nums1` and `nums2` are `m` and `n` respectively.
*   You may assume that `nums1` has enough space (size greater or equal to `m + n`) to hold additional elements from `nums2`.

**Example:**

```
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

In this example, we have `nums1` with the initial `[1, 2, 3]` and sufficient space at the end, and `nums2` with `[2, 5, 6]`. The goal is to merge `nums2` into `nums1` to obtain a single sorted array `[1, 2, 2, 3, 5, 6]`.

---

## 📊 **Algorithm**

*   Initialize pointers: `m` pointing to the last element of `nums1` (before the zeros), `n` pointing to the last element of `nums2`, and `k` pointing to the last available position in `nums1`.
*   Iterate while `n` is non-negative (i.e., there are still elements in `nums2` to merge).
*   In each iteration, compare the elements at `nums1[m]` and `nums2[n]`.
    *   If `nums1[m]` is greater than `nums2[n]`, copy `nums1[m]` to `nums1[k]` and decrement both `m` and `k`.
    *   Otherwise (including the case when `m` is negative, which means we've exhausted `nums1`), copy `nums2[n]` to `nums1[k]` and decrement both `n` and `k`.
*   The loop continues until all elements from `nums2` are merged into `nums1`.

## 🔥 **Code Implementation**

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        m -= 1;
        n -= 1;
        
        int k = nums1.length - 1;
        while (n >= 0) {
            if (m >= 0 && nums1[m] > nums2[n]) {
                nums1[k--] = nums1[m--];
            } else {
                nums1[k--] = nums2[n--];
            }
        }
    }
}
```

## 📊 **ASCII Representation**

Not applicable for this problem.

## 📊 **TABLE Representation**

Let's trace the execution with `nums1 = [1,2,3,0,0,0]`, `m = 3`, `nums2 = [2,5,6]`, `n = 3`.

| Iteration | m    | n    | k    | nums1                   | Condition              |
| :-------- | :--- | :--- | :--- | :---------------------- | :--------------------- |
| Initial   | 2    | 2    | 5    | [1, 2, 3, 0, 0, 0]      |                       |
| 1         | 2    | 2    | 5    | [1, 2, 3, 0, 0, **6**]  | nums1[2] < nums2[2]    |
| 2         | 2    | 1    | 4    | [1, 2, 3, 0, **5**, 6]  | nums1[2] < nums2[1]    |
| 3         | 2    | 0    | 3    | [1, 2, 3, **3**, 5, 6]  | nums1[2] == nums2[0]  |
| 4         | 2    | 0    | 3    | [1, 2, 3, **3**, 5, 6] | nums1[2] < nums2[0]    |
| 5         | 2    | -1    | 2    | [1, 2, 2, 3, 5, 6] | n < 0 loop termination  |

## 📊 **WORKING**

Given `nums1 = [1,2,3,0,0,0]`, `m = 3`, `nums2 = [2,5,6]`, `n = 3`

1.  `m` points to 2 (3-1), `n` points to 2 (3-1), `k` points to 5 (`nums1.length - 1`).
2.  Compare `nums1[2]` (3) and `nums2[2]` (6). Since 3 < 6, `nums1[5] = 6`, `k` becomes 4, `n` becomes 1. `nums1` is now `[1,2,3,0,0,6]`.
3.  Compare `nums1[2]` (3) and `nums2[1]` (5). Since 3 < 5, `nums1[4] = 5`, `k` becomes 3, `n` becomes 0. `nums1` is now `[1,2,3,0,5,6]`.
4. Compare `nums1[2]` (3) and `nums2[0]` (2). Since 3 > 2, `nums1[3] = 3`, `k` becomes 2, `m` becomes 1. `nums1` is now `[1,2,3,3,5,6]`.
5. Compare `nums1[1]` (2) and `nums2[0]` (2). Since 2 == 2, `nums1[2] = 2`, `k` becomes 1, `n` becomes -1. `nums1` is now `[1,2,2,3,5,6]`.
6. The loop terminates because `n` is -1. The merged array is `[1,2,2,3,5,6]`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(m + n)**, where *m* and *n* are the lengths of the two input arrays. This is because, in the worst case, we iterate through both arrays once.
*   **Space Complexity:** **O(1)**, as the merging is done in-place, using only a constant amount of extra space for the pointers.
    