# 00004 - Median Of Two Sorted Arrays
    
**Language:** Java  
**Runtime:** 2 ms (Beats 55.62% of users)  
**Memory:** 46 MB (Beats 72.98% of users)  

## 📝 LeetCode Problem
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 4 | Median of Two Sorted Arrays | [LeetCode Problem](https://leetcode.com/problems/median-of-two-sorted-arrays/) |

---

## 🧩 Problem Statement
Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return **the median** of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

**Constraints:**

- `nums1.length == m`
- `nums2.length == n`
- `0 <= m <= 1000`
- `0 <= n <= 1000`
- `1 <= m + n <= 2000`
- `-10^6 <= nums1[i], nums2[i] <= 10^6`

## 💡 Problem Explanation
The problem requires finding the median of two sorted arrays efficiently.  A naive approach of merging and then finding the median would be O(m+n).  The challenge lies in achieving a logarithmic time complexity. This specific implementation does not achieve the O(log(m+n)) requirement, instead using a merge and sort method that runs in O(m+n).

## 🧪 Examples

**Example 1:**

```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```

**Example 2:**

```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3) / 2 = 2.5
```

## 🧭 Algorithm Overview

*   Merge the two sorted arrays `nums1` and `nums2` into a single sorted array `nums3`.
*   Calculate the median of the merged array `nums3`.
*   If the length of `nums3` is odd, the median is the middle element.
*   If the length of `nums3` is even, the median is the average of the two middle elements.

This approach was chosen for simplicity and ease of understanding, although it doesn't meet the logarithmic time complexity requirement.

## 🧱 Variables & Data Structures

| Variable/Data Structure | Description                                                                 |
|--------------------------|-----------------------------------------------------------------------------|
| `nums1`                 | The first sorted array.                                                     |
| `nums2`                 | The second sorted array.                                                     |
| `nums3`                 | A new array to store the merged sorted elements of `nums1` and `nums2`.    |
| `i`                     | Index pointer for iterating through `nums1`.                                |
| `j`                     | Index pointer for iterating through `nums2`.                                |
| `k`                     | Index pointer for inserting elements into `nums3`.                            |

## 🔢 Step-by-Step Breakdown

Let's consider `nums1 = [1, 3]` and `nums2 = [2]`.

1.  Initialize `nums3` with a size of `nums1.length + nums2.length = 2 + 1 = 3`. Initialize `i = 0`, `j = 0`, `k = 0`.
2.  **First `while` loop**:
    *   Compare `nums1[0] = 1` and `nums2[0] = 2`.  Since `1 < 2`, `nums3[0] = 1`. Increment `i` to 1, `k` to 1.
    *   Now `i = 1`, `j = 0`, `k = 1`. Compare `nums1[1] = 3` and `nums2[0] = 2`. Since `3 > 2`, `nums3[1] = 2`. Increment `j` to 1, `k` to 2.
    *   Now `i = 1`, `j = 1`, `k = 2`. The first `while` loop terminates because `j` is no longer less than `nums2.length`.
3.  **Second `while` loop**:
    *   The second `while` loop (for `nums1`) executes. `nums3[2] = 3`. Increment `i` to 2, `k` to 3.
    *   Now `i = 2`, `j = 1`, `k = 3`. The second `while` loop terminates because `i` is no longer less than `nums1.length`.
4.  **Third `while` loop**:
    *   The third `while` loop (for `nums2`) does not execute because `j` is not less than `nums2.length`.
5.  `nums3` is now `[1, 2, 3]`. `k = nums3.length = 3`.
6.  Since `k` (3) is odd, the median is `nums3[k/2] = nums3[3/2] = nums3[1] = 2`.

## 🧰 Programming Workflow

1.  Initialize `nums3` to store the merged sorted array.
2.  Initialize index pointers `i`, `j`, and `k` for `nums1`, `nums2`, and `nums3` respectively.
3.  Merge `nums1` and `nums2` into `nums3` while both arrays have elements.
4.  Copy remaining elements from `nums1` (if any) into `nums3`.
5.  Copy remaining elements from `nums2` (if any) into `nums3`.
6.  Calculate the median based on whether the length of `nums3` is even or odd.
7.  Return the median.

```ascii
+---------------------+    +---------------------+    +---------------------+
| Initialize nums3, i, j, k |    | Merge nums1 and nums2 |    | Copy remaining from   |
+---------------------+    | into nums3           |    | nums1 or nums2      |
         |                  +---------------------+    +---------------------+
         |                           |                           |
         V                           V                           V
+---------------------+    +---------------------+    +---------------------+
| Calculate Median      |    | Check if Length Even  |    | Return Median           |
+---------------------+    | or Odd              |    +---------------------+
         |                  +---------------------+
         |
         V
     End
```

## 🔥 Code Implementation

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length+nums2.length]; // Create a new array to store the merged elements
        int i=0,j=0,k=0;                                   // Initialize index pointers
        while(i<nums1.length && j<nums2.length){           // Merge while both arrays have elements
            if(nums1[i]<nums2[j]){
                nums3[k++]=nums1[i++];
            }
            else{
                nums3[k++]=nums2[j++];
            }
        }
        while(i<nums1.length){                             // Copy remaining elements from nums1
            nums3[k++]=nums1[i++];
        }
        while(j < nums2.length) {                          // Copy remaining elements from nums2
            nums3[k++]=nums2[j++];
        }
        k = nums3.length;                                   // Update k to be the length of nums3
        if(k==1)    return nums3[0];                       // Handle the case where nums3 has only one element
        if(k%2==0)  return (nums3[k/2]+nums3[(k/2)-1])/2.0; // If length is even, average the two middle elements
        return nums3[k/2];                                 // If length is odd, return the middle element
    }
}
```

## 🗂️ Table Representation
N/A

## 🧪 Working Demo

For `nums1 = [1, 3]` and `nums2 = [2]`:

1.  `nums3` is initialized: `[]`
2.  After merging: `nums3 = [1, 2, 3]`
3.  Length of `nums3` is 3 (odd).
4.  Median is `nums3[3/2] = nums3[1] = 2`.

## 🚀 Time & Space Complexity

*   **Time Complexity: O(m+n)** - due to merging the two sorted arrays into a new array.
*   **Space Complexity: O(m+n)** -  due to the creation of a new array `nums3` to store the merged elements.

## 🔗 References

*   [Merge Sort Algorithm](https://en.wikipedia.org/wiki/Merge_sort)
    