# 00416 - Partition Equal Subset Sum
    
**Language:** Java  
**Runtime:** 149 ms (Beats 7.82% of users)  
**Memory:** 46.1 MB (Beats 68.56% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 416 | PARTITION EQUAL SUBSET SUM | [LeetCode Problem](https://leetcode.com/problems/partition-equal-subset-sum/) |

---

## 💡 **Problem Explanation**

Given a non-empty array `nums` containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

In simpler terms, can you divide the array into two groups where each group has the same total value?

**Example 1:**

```
Input: nums = [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
```

**Example 2:**

```
Input: nums = [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
```

This problem is a classic example of a knapsack-like problem and can be solved using dynamic programming or, as shown below, using a HashSet approach which optimizes space a bit.

## 📊 **Algorithm**

*   Calculate the total sum of the input array `nums`.
*   If the total sum is odd, the array cannot be partitioned into two equal sum subsets. Return `false`.
*   Calculate the target sum, which is half of the total sum.
*   Use a HashSet `hs` to keep track of possible subset sums.
*   Iterate through the input array `nums`.
    *   For each element `num`, create a new HashSet `newSet` based on the current `hs`.
    *   For each existing sum `x` in `hs`, check if `x + num` equals the `target`. If it does, return `true` because a partition is found.
    *   Add `x + num` to the `newSet`.
    *   Update `hs` with `newSet`.
*   After iterating through all elements, check if `hs` contains the `target`. Return `true` if it does; otherwise, return `false`.

## 🔥 **Code Implementation**

```java
import java.util.HashSet;

class Solution {
    public boolean canPartition(int[] nums) {
        int tot = 0;
        for (int num : nums) tot += num;

        if ((tot & 1) == 1) return false;

        int target = tot / 2;
        HashSet<Integer> hs = new HashSet<>();
        hs.add(0);

        for (int num : nums) {
            HashSet<Integer> newSet = new HashSet<>(hs);
            for (int x : hs) {
                if (x + num == target) return true;
                newSet.add(x + num);
            }
            hs = newSet;
        }

        return hs.contains(target);
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids, trees, or movements, so an ASCII representation is not applicable.

## 📊 **WORKING**

Let's trace the execution with the input `nums = [1, 5, 11, 5]`.

1.  `tot = 1 + 5 + 11 + 5 = 22`
2.  `tot & 1 == 0` (22 is even)
3.  `target = 22 / 2 = 11`
4.  `hs = {0}`

*   **num = 1:**
    *   `newSet = {0}`
    *   `x = 0`: `0 + 1 == 11` (false), `newSet.add(1)`
    *   `hs = {0, 1}`
*   **num = 5:**
    *   `newSet = {0, 1}`
    *   `x = 0`: `0 + 5 == 11` (false), `newSet.add(5)`
    *   `x = 1`: `1 + 5 == 11` (false), `newSet.add(6)`
    *   `hs = {0, 1, 5, 6}`
*   **num = 11:**
    *   `newSet = {0, 1, 5, 6}`
    *   `x = 0`: `0 + 11 == 11` (true).  Return `true`.

The algorithm finds a partition early when `num = 11` and `x = 0`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n*sum)**, where n is the number of elements in the input array and sum is the total sum of elements. This is because, in the worst case, the HashSet `hs` can contain sums up to the `target`, and we iterate through all elements of `nums`.

*   **Space Complexity:** **O(sum)**, where sum is the total sum of elements. This is because the HashSet `hs` stores the possible subset sums, which can be up to `sum / 2` in the worst case.
    