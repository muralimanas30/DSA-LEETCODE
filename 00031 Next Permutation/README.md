# 00031 - Next Permutation
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 43.1 MB (Beats 56.36% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 31 | NEXT PERMUTATION | [LeetCode Problem](https://leetcode.com/problems/next-permutation/) |

---

## 💡 **Problem Explanation**

The "Next Permutation" problem asks us to find the next lexicographically greater permutation of a given array of integers. If no such greater permutation exists (i.e., the array is in descending order), we should rearrange the array into the smallest possible order (ascending order).

**Example 1:**

Input: `nums = [1,2,3]`
Output: `[1,3,2]`

**Example 2:**

Input: `nums = [3,2,1]`
Output: `[1,2,3]`

**Example 3:**

Input: `nums = [1,1,5]`
Output: `[1,5,1]`

---

## 📊 **Algorithm**

*   **Find the First Decreasing Element:** Starting from the right, find the first element `nums[i-1]` that is smaller than `nums[i]`.  This marks the point where we can potentially create a larger permutation.
*   **Find the Element to Swap:**  Again, starting from the right, find the first element `nums[j]` that is greater than `nums[i-1]`.
*   **Swap:** Swap `nums[i-1]` and `nums[j]`.
*   **Reverse the Suffix:** Reverse the subarray starting from index `i` to the end of the array. This ensures that the suffix is in ascending order, resulting in the next lexicographically greater permutation.
*   **Handle Descending Order:** If no decreasing element is found in the first step (i.e., the array is in descending order), simply reverse the entire array to get the smallest possible permutation (ascending order).

---

## 🔥 **Code Implementation**

```java
class Solution {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                for (int j = nums.length - 1; j >= i; j--) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, i - 1, j);
                        break;
                    }
                }
                reverseRange(nums, i, nums.length - 1);
                return;
            }
        }
        reverseRange(nums, 0, nums.length - 1);
    }

    public void reverseRange(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
```

---

## 📊 **ASCII Representation**

This problem is best understood conceptually and doesn't lend itself well to a grid-based ASCII representation.

---

## 📊 **WORKING**

Let's trace the execution with `nums = [1,2,3]`.

1.  **Find the First Decreasing Element:**
    *   Start from the right: `3 > 2` (i=3, i-1=2). No decreasing element yet.
    *   `2 > 1` (i=2, i-1=1). We found `nums[0] = 1` is smaller than `nums[1] = 2`.  So `i-1 = 0`.

2.  **Find the Element to Swap:**
    *   Start from the right: `3 > 1` (j=2). We found an element to swap.

3.  **Swap:**
    *   Swap `nums[0]` (1) and `nums[2]` (3). `nums` becomes `[3, 2, 1]`.

4.  **Reverse the Suffix:**
    *   Reverse the subarray from index `1` to the end: `[2, 1]` becomes `[1, 2]`.
    *   `nums` becomes `[3, 1, 2]`.

Therefore, the next permutation of `[1,2,3]` is `[1,3,2]`.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where n is the length of the array.  The algorithm involves at most one linear scan to find the decreasing element, another linear scan to find the element to swap, and a reversal of a subarray.
*   **Space Complexity:** **O(1)**. The algorithm operates in place and uses a constant amount of extra space.
    