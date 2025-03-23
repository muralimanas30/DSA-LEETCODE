# 00001 - Two Sum
    
**Language:** Java  
**Runtime:** 2 ms (Beats 98.83% of users)  
**Memory:** 45.3 MB (Beats 17.88% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title   | 🔗 Link                                                        |
| ------------------ | ----------- | ------------------------------------------------------------ |
| 1                  | Two Sum | [LeetCode Problem](https://leetcode.com/problems/two-sum/) |

---

## 💡 **Problem Explanation**

Given an array of integers `nums` and an integer `target`, return *indices of the two numbers such that they add up to `target`*.

You may assume that each input would have **exactly one solution**, and you may not use the *same* element twice.

You can return the answer in any order.

## 📊 **Algorithm**

*   Create a HashMap to store each number from the input array `nums` and its index.
*   Iterate through the `nums` array:
    *   For each number `nums[i]`, calculate the `complement` needed to reach the `target` (i.e., `target - nums[i]`).
    *   Check if the `complement` exists as a key in the HashMap.
    *   If the `complement` exists in the HashMap, it means we have found the two numbers that add up to the `target`. Return the indices of the current number and the `complement`.
*   If no such pair is found, return a default array (this should not happen according to the problem constraints).

## 🔥 **Code Implementation**

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Finds two numbers in an array that add up to a target value.
     *
     * @param nums   The input array of integers.
     * @param target The target sum.
     * @return An array containing the indices of the two numbers that add up to the target.
     *         Returns null if no such pair exists (though the problem statement guarantees a solution).
     */
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store each number and its index.
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If the complement exists in the HashMap, we have found our pair.
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }

            // Store the number and its index in the HashMap.
            numMap.put(nums[i], i);
        }

        // Should never happen, as the problem guarantees a solution.
        return null;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees, so an ASCII representation isn't particularly applicable.

## 📊 **WORKING**

Let's walk through a simple example:

`nums = [2, 7, 11, 15], target = 9`

1.  **Iteration 1:**
    *   `nums[0] = 2`, `complement = 9 - 2 = 7`
    *   `numMap` is empty, so `7` is not found.
    *   Store `(2, 0)` in `numMap`.

2.  **Iteration 2:**
    *   `nums[1] = 7`, `complement = 9 - 7 = 2`
    *   `numMap` contains `2` at index `0`.
    *   Return `[0, 1]`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)** - We iterate through the array once. HashMap operations (put and get) take constant time on average.

*   **Space Complexity:** **O(n)** - In the worst case, we might store all `n` elements of the array in the HashMap.
    