# 02699 - Count The Number Of Fair Pairs
    
**Language:** Java  
**Runtime:** 65 ms (Beats 34.08% of users)  
**Memory:** 57.4 MB (Beats 41.82% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2699 | COUNT THE NUMBER OF FAIR PAIRS | [LeetCode Problem](https://leetcode.com/problems/count-the-number-of-fair-pairs/) |

---

## 💡 **Problem Explanation**

The problem asks you to count the number of "fair pairs" in a given array `nums`.  A pair `(i, j)` is considered fair if `i < j` and `lower <= nums[i] + nums[j] <= upper`, where `lower` and `upper` are given integer bounds. In simpler terms, you need to find how many pairs of numbers in the array sum up to a value within the specified range.

**Example:**

Let's say `nums = [0, 1, 7, 4, 4, 5]`, `lower = 3`, and `upper = 6`.

We need to find pairs `(i, j)` such that `nums[i] + nums[j]` falls between 3 and 6 (inclusive).

- `nums[0] + nums[1] = 0 + 1 = 1` (not fair)
- `nums[0] + nums[2] = 0 + 7 = 7` (not fair)
- `nums[0] + nums[3] = 0 + 4 = 4` (fair)
- `nums[0] + nums[4] = 0 + 4 = 4` (fair)
- `nums[0] + nums[5] = 0 + 5 = 5` (fair)
- `nums[1] + nums[2] = 1 + 7 = 8` (not fair)
- `nums[1] + nums[3] = 1 + 4 = 5` (fair)
- `nums[1] + nums[4] = 1 + 4 = 5` (fair)
- `nums[1] + nums[5] = 1 + 5 = 6` (fair)
- `nums[2] + nums[3] = 7 + 4 = 11` (not fair)
- `nums[2] + nums[4] = 7 + 4 = 11` (not fair)
- `nums[2] + nums[5] = 7 + 5 = 12` (not fair)
- `nums[3] + nums[4] = 4 + 4 = 8` (not fair)
- `nums[3] + nums[5] = 4 + 5 = 9` (not fair)
- `nums[4] + nums[5] = 4 + 5 = 9` (not fair)

The fair pairs are: (0, 3), (0, 4), (0, 5), (1, 3), (1, 4), (1, 5).  Therefore, the output would be 6.

## 📊 **Algorithm**

Here's the algorithm to solve this problem:

*   **Sort the Array:** Sort the input array `nums` in ascending order. This allows us to use binary search efficiently.
*   **Iterate and Binary Search:**
    *   Iterate through each element `nums[i]` in the sorted array.
    *   For each `nums[i]`, use binary search to find the range of indices `j` (where `j > i`) such that `lower <= nums[i] + nums[j] <= upper`.  This can be done by finding the first `j` where `nums[i] + nums[j] >= lower` and the last `j` where `nums[i] + nums[j] <= upper`.
    *   The number of elements within that range is the number of fair pairs involving `nums[i]`.
*   **Accumulate the Count:**  Sum up the counts of fair pairs found for each `nums[i]` to get the total number of fair pairs.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    private boolean first = true;

    public long countFairPairs(int[] nums, int lower, int upper) {
        long count = 0;
        if(first){
            Arrays.sort(nums);
            first = false;
        }
        for (int i = 0; i < nums.length; i++) {
            int lesserNums = binarySearch(nums, i + 1, lower - nums[i], true);
            int higherNums = binarySearch(nums, i + 1, upper - nums[i], false);
            count += higherNums - lesserNums + 1;
        }
        return count;
    }

    private int binarySearch(int[] nums, int start, int val, boolean lower) {
        int low = start;
        int high = nums.length - 1;
        int res = lower ? nums.length : start - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (lower) {
                if (nums[mid] >= val) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] <= val) {
                    res = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return res;
    }
}
```

## 📊 **ASCII Representation**
Not applicable, as this isn't a grid-based or tree-based problem.

## 📊 **WORKING**

Let's trace the execution with the example `nums = [0, 1, 7, 4, 4, 5]`, `lower = 3`, and `upper = 6`.

1.  **Sort `nums`:** `nums` becomes `[0, 1, 4, 4, 5, 7]`.

2.  **Outer Loop (i = 0): `nums[0] = 0`**
    *   `lower - nums[0] = 3 - 0 = 3`
    *   `upper - nums[0] = 6 - 0 = 6`
    *   `binarySearch(nums, 1, 3, true)`:  Finds the index of the first element >= 3, which is `nums[2] = 4` at index 2.  So, `lesserNums = 2`.
    *   `binarySearch(nums, 1, 6, false)`: Finds the index of the last element <= 6, which is `nums[4] = 5` at index 4. So, `higherNums = 4`.
    *   `count += 4 - 2 + 1 = 3`. (Pairs: (0, 2), (0, 3), (0, 4))

3.  **Outer Loop (i = 1): `nums[1] = 1`**
    *   `lower - nums[1] = 3 - 1 = 2`
    *   `upper - nums[1] = 6 - 1 = 5`
    *   `binarySearch(nums, 2, 2, true)`: Finds the index of the first element >= 2, which is `nums[2] = 4` at index 2.  So, `lesserNums = 2`.
    *   `binarySearch(nums, 2, 5, false)`: Finds the index of the last element <= 5, which is `nums[4] = 5` at index 4.  So, `higherNums = 4`.
    *   `count += 4 - 2 + 1 = 3`. (Pairs: (1, 2), (1, 3), (1, 4))

4.  **Outer Loop (i = 2): `nums[2] = 4`**
    *   `lower - nums[2] = 3 - 4 = -1`
    *   `upper - nums[2] = 6 - 4 = 2`
    *   `binarySearch(nums, 3, -1, true)`: Finds the index of the first element >= -1, which is `nums[3] = 4` at index 3.  So, `lesserNums = 3`.
    *   `binarySearch(nums, 3, 2, false)`: Finds the index of the last element <= 2. There is no number less than 2 so that will return `2-1 = 1` that not in range. In this case the function will return `start -1` which is `3-1 = 2` So, `higherNums = 2`.
    *   `count += 2 - 3 + 1 = 0`. (no pairs)

5.  **Outer Loop (i = 3): `nums[3] = 4`**
    *   `lower - nums[3] = 3 - 4 = -1`
    *   `upper - nums[3] = 6 - 4 = 2`
    *   `binarySearch(nums, 4, -1, true)`: Finds the index of the first element >= -1, which is `nums[4] = 5` at index 4.  So, `lesserNums = 4`.
    *   `binarySearch(nums, 4, 2, false)`: Finds the index of the last element <= 2. There is no number less than 2 so that will return `3`
    *   `count += 3 - 4 + 1 = 0`. (no pairs)

6.  **Outer Loop (i = 4): `nums[4] = 5`**
    *   `lower - nums[4] = 3 - 5 = -2`
    *   `upper - nums[4] = 6 - 5 = 1`
    *   `binarySearch(nums, 5, -2, true)`: Finds the index of the first element >= -2, which is `nums[5] = 7` at index 5. So, `lesserNums = 5`.
    *   `binarySearch(nums, 5, 1, false)`: Finds the index of the last element <= 1. It is `4`.
    *   `count += 2 - 5 + 1 = 0`. (no pairs)
7.  **Outer Loop (i = 5): `nums[5] = 7`**
    *   `lower - nums[5] = 3 - 7 = -4`
    *   `upper - nums[5] = 6 - 7 = -1`
    *   `binarySearch(nums, 6, -4, true)`: Finds the index of the first element >= -4. index out of range so that will return `6` So, `lesserNums = 6`.
    *   `binarySearch(nums, 6, -1, false)`: Finds the index of the last element <= -1. index out of range so that will return `5`.
    *   `count += 5 - 6 + 1 = 0`. (no pairs)

Finally, the accumulated `count` is `3 + 3 + 0 + 0 + 0+ 0 = 6`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The dominant operation is the sorting which takes **O(n log n)** time. The binary search within the loop contributes an additional **O(n log n)** since we do a binary search for each of the n element. Therefore the total time complexity is **O(n log n)**.

*   **Space Complexity:** The space complexity is **O(1)**. because the sorting is done in place, and binary search is only using variables, without data structures.
    