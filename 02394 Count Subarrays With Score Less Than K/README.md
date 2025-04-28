# 02394 - Count Subarrays With Score Less Than K
    
**Language:** Java  
**Runtime:** 2 ms (Beats 100.00% of users)  
**Memory:** 61.6 MB (Beats 35.48% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2394 | Count Subarrays With Score Less Than K | [LeetCode Problem](https://leetcode.com/problems/count-subarrays-with-score-less-than-k/) |

---

## 💡 **Problem Explanation**

Given an array `nums` of positive integers, the score of a subarray is defined as the product of its length and the sum of its elements. The task is to count the number of non-empty subarrays whose score is strictly less than `k`.

For instance, consider `nums = [2, 1, 4, 3, 5]` and `k = 10`.

Subarray `[2]` has score `1 * 2 = 2 < 10`.

Subarray `[1]` has score `1 * 1 = 1 < 10`.

Subarray `[2, 1]` has score `2 * (2 + 1) = 6 < 10`.

Subarray `[1, 4]` has score `2 * (1 + 4) = 10` which is not strictly less than 10.

Subarray `[2, 1, 4]` has score `3 * (2 + 1 + 4) = 21` which is not strictly less than 10.

**Example 1:**

`nums = [2, 1, 4, 3, 5], k = 10`  Output: `7`

**Example 2:**

`nums = [1, 1, 1], k = 5` Output: `6`

---

## 📊 **Algorithm**

*   Initialize `currLength = 1`, `score = 0`, `currSum = 0`, and `left = 0`.
*   Iterate through the `nums` array using the `right` pointer.
*   Add `nums[right]` to `currSum`.
*   While `currSum * currLength >= k`, subtract `nums[left]` from `currSum`, increment `left`, and decrement `currLength`. This shrinks the window from the left until the condition is met.
*   Add `currLength` to the `score`.
*   Increment `currLength`.
*   Return the final `score`.

## 🔥 **Code Implementation**

```java
class Solution {
    public long countSubarrays(int[] nums, long k) {
        int currLength = 1;
        long score = 0;
        long currSum = 0;
        int left = 0;
        for(int right=0;right<nums.length;right++){
            currSum += nums[right];
            while(currSum*currLength>=k){
                currSum -= nums[left++];
                currLength--;
            }
            score += currLength;
            currLength++;
        }
        return score;
    }
}
```

## 📊 **ASCII Representation**

N/A - This problem does not directly involve grids or trees.

## 📊 **WORKING**

Let's trace the execution with `nums = [2, 1, 4, 3, 5]` and `k = 10`.

1.  `right = 0`: `currSum = 2`, `currLength = 1`, `currSum * currLength = 2 < 10`, `score = 1`, `currLength = 2`
2.  `right = 1`: `currSum = 3`, `currLength = 2`, `currSum * currLength = 6 < 10`, `score = 1 + 2 = 3`, `currLength = 3`
3.  `right = 2`: `currSum = 7`, `currLength = 3`, `currSum * currLength = 21 >= 10`, `currSum -= nums[0] = 2`, `currSum = 5`, `left = 1`, `currLength = 2`, `currSum * currLength = 10 >= 10`, `currSum -= nums[1] = 1`, `currSum = 4`, `left = 2`, `currLength = 1`, `currSum * currLength = 4 < 10`, `score = 3 + 1 = 4`, `currLength = 2`
4.  `right = 3`: `currSum = 7`, `currLength = 2`, `currSum * currLength = 14 >= 10`, `currSum -= nums[2] = 4`, `currSum = 3`, `left = 3`, `currLength = 1`, `currSum * currLength = 3 < 10`, `score = 4 + 1 = 5`, `currLength = 2`
5.  `right = 4`: `currSum = 8`, `currLength = 2`, `currSum * currLength = 16 >= 10`, `currSum -= nums[3] = 3`, `currSum = 5`, `left = 4`, `currLength = 1`, `currSum * currLength = 5 < 10`, `score = 5 + 1 = 6`, `currLength = 2`

The function returns 6.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** *O(n)*, where n is the length of the `nums` array.  Although there is a nested while loop, each element is visited at most twice (once by the right pointer and at most once by the left pointer).
*   **Space Complexity:** *O(1)*, as we are only using a constant amount of extra space.
    