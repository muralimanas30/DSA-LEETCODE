# 03171 - Minimum Equal Sum Of Two Arrays After Replacing Zeros
    
**Language:** Java  
**Runtime:** 3 ms (Beats 88.73% of users)  
**Memory:** 60.6 MB (Beats 71.83% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3171 | MINIMUM EQUAL SUM OF TWO ARRAYS AFTER REPLACING ZEROS | [LeetCode Problem](https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/) |

---

## 💡 **Problem Explanation**

You are given two arrays, `nums1` and `nums2`. You can replace any zeros in either array with any positive integer. The goal is to find the minimum possible equal sum of the two arrays after replacing the zeros. If it's impossible to make the sums equal, return -1.

Essentially, we need to figure out how to replace the zeros in each array so that their sums become equal, minimizing that sum.

**Example:**

`nums1 = [3, 2, 0, 1, 0]`
`nums2 = [6, 5, 0]`

- We can replace the zeros in `nums1` with 1s to get `[3, 2, 1, 1, 1]` with a sum of 8.
- We can replace the zero in `nums2` with 2 to get `[6, 5, 2]` with a sum of 13.
To make the array sums equal we need `nums1` to be minimum 10 since it has 2 zeros so the numbers becomes `[3,2,5,1,5]`
-The output will be 13 if we made both array sums equal or return -1 if they cant be equal.

---

## 📊 **Algorithm**

*   Calculate the initial sum of each array (`ls` for `nums1`, `rs` for `nums2`), considering non-zero elements.
*   Count the number of zeros in each array (`lc` for `nums1`, `rc` for `nums2`).
*   Check if it's possible to make the sums equal:
    *   If `nums2`'s potential minimum sum (`rs + rc`) is greater than `nums1`'s initial sum (`ls`) and `nums1` has no zeros, it's impossible. Return -1.
    *   Similarly, if `nums1`'s potential minimum sum (`ls + lc`) is greater than `nums2`'s initial sum (`rs`) and `nums2` has no zeros, it's impossible. Return -1.
*   Calculate the minimum possible sum for each array by replacing zeros with 1s: `minL = ls + lc`, `minR = rs + rc`.
*   Return the maximum of `minL` and `minR`, as this will be the minimum equal sum achievable.

---

## 🔥 **Code Implementation**

```java
class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long ls = 0,rs = 0; // Initialize sums for nums1 and nums2
        int lc = 0,rc = 0;  // Initialize zero counts for nums1 and nums2
        for(int x : nums1)  if(x==0)    lc++; else ls+=x; // Calculate sum and count zeros in nums1
        for(int x : nums2)  if(x==0)    rc++; else rs+=x; // Calculate sum and count zeros in nums2
        if((rs+rc*1l)>ls && lc*1l==0)
            return -1; // If nums2's minimum sum is greater and nums1 has no zeros, return -1
        if((ls+lc*1l)>rs && rc*1l==0)
            return -1; // If nums1's minimum sum is greater and nums2 has no zeros, return -1
        
        long minL = ls + lc*1l; // Minimum possible sum for nums1
        long minR = rs + rc*1l; // Minimum possible sum for nums2
        return Math.max(minL,minR); // Return the maximum of the two minimum sums
    }
}
```

---

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees, so an ASCII representation isn't particularly relevant. However, to visualize the arrays themselves, you could imagine them laid out simply:

```
nums1: [ 3, 2, 0, 1, 0 ]
nums2: [ 6, 5, 0 ]
```

---

## 📊 **WORKING**

Let's trace the given example:

`nums1 = [3, 2, 0, 1, 0]`
`nums2 = [6, 5, 0]`

1.  **Initialize:** `ls = 0`, `rs = 0`, `lc = 0`, `rc = 0`
2.  **Process `nums1`:**
    *   `ls` becomes `3 + 2 + 1 = 6`
    *   `lc` becomes `2`
3.  **Process `nums2`:**
    *   `rs` becomes `6 + 5 = 11`
    *   `rc` becomes `1`
4.  **Check Conditions:**
    *   `rs + rc = 11 + 1 = 12`.  Is `12 > 6` and `lc == 0`? No, `lc` is 2.
    *   `ls + lc = 6 + 2 = 8`. Is `8 > 11` and `rc == 0`? No, `rc` is 1.
5.  **Calculate Minimum Sums:**
    *   `minL = ls + lc = 6 + 2 = 8`
    *   `minR = rs + rc = 11 + 1 = 12`
6.  **Return:** `max(8, 12) = 12`

Therefore, the minimum equal sum is 12.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(N + M)**, where N is the length of `nums1` and M is the length of `nums2`. We iterate through both arrays once.
*   **Space Complexity:** **O(1)**. We only use a few constant extra variables.
    