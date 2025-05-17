# 00075 - Sort Colors
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 42 MB (Beats 65.31% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 75 | SORT COLORS | [LeetCode Problem](https://leetcode.com/problems/sort-colors/) |

---

## 💡 **Problem Explanation**

Given an array `nums` with `n` objects colored red, white, or blue, sort them **in-place** so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue, respectively. You must solve this problem without using the library's sort function.

**Example:**

**Input:** `nums = [2,0,2,1,1,0]`
**Output:** `[0,0,1,1,2,2]`

In this example, we take an array that is unsorted, and we rearrange it such that all the `0`s (red) are at the beginning, followed by the `1`s (white), and then the `2`s (blue).  The rearrangement must happen in-place, meaning we modify the original array directly without creating a new one.

---

## 📊 **Algorithm**
*   Initialize three pointers: `left`, `mid`, and `right`.
*   `left` points to the beginning of the array (index 0).
*   `mid` is used to traverse the array.
*   `right` points to the end of the array (index nums.length - 1).
*   While `mid` is less than or equal to `right`, perform the following:
    *   If `nums[mid]` is 0:
        *   Swap `nums[mid]` with `nums[left]`.
        *   Increment both `mid` and `left`.
    *   If `nums[mid]` is 1:
        *   Increment `mid`.
    *   If `nums[mid]` is 2:
        *   Swap `nums[mid]` with `nums[right]`.
        *   Decrement `right`.
*The array will be sorted in-place.

---

## 🔥 **Code Implementation**

```java
class Solution {
    public void sortColors(int[] nums) {
        int mid=0,left=0,right =nums.length-1;
        while(mid<=right){
            switch(nums[mid]){
                case 0 : {
                    int temp = nums[left];
                    nums[left] = nums[mid];
                    nums[mid] = temp;
                    mid++;left++;
                    break;
                }
                case 1 : mid++;break;
                case 2 : {
                    int temp = nums[right];
                    nums[right] = nums[mid];
                    nums[mid] = temp;
                    right--;
                    break;
                }
            }
        }
    }
}
```

---

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees. However, it helps to visualize the movements of the `left`, `mid`, and `right` pointers.

Initial state for input `[2, 0, 2, 1, 1, 0]`:

```
     l
     m
[ 2, 0, 2, 1, 1, 0 ]
                 r
```

---

## 📊 **WORKING**

Let's trace the example `nums = [2, 0, 2, 1, 1, 0]`

1.  **Initial State:** `left = 0`, `mid = 0`, `right = 5`
    `nums = [2, 0, 2, 1, 1, 0]`

2.  `nums[mid] = 2`. Swap `nums[mid]` and `nums[right]`.  `right--`.
    `nums = [0, 0, 2, 1, 1, 2]`
    `left = 0`, `mid = 0`, `right = 4`

3.  `nums[mid] = 0`. Swap `nums[mid]` and `nums[left]`. `left++`, `mid++`.
    `nums = [0, 0, 2, 1, 1, 2]` (no change since they are the same)
    `left = 1`, `mid = 1`, `right = 4`

4.  `nums[mid] = 0`. Swap `nums[mid]` and `nums[left]`. `left++`, `mid++`.
    `nums = [0, 0, 2, 1, 1, 2]`
    `left = 2`, `mid = 2`, `right = 4`

5.  `nums[mid] = 2`. Swap `nums[mid]` and `nums[right]`. `right--`.
    `nums = [0, 0, 1, 1, 2, 2]`
    `left = 2`, `mid = 2`, `right = 3`

6.  `nums[mid] = 1`. `mid++`.
    `nums = [0, 0, 1, 1, 2, 2]`
    `left = 2`, `mid = 3`, `right = 3`

7.  `nums[mid] = 1`. `mid++`.
     `nums = [0, 0, 1, 1, 2, 2]`
    `left = 2`, `mid = 4`, `right = 3`

8.  `mid > right`, so we exit.

Final sorted array: `[0, 0, 1, 1, 2, 2]`

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where `n` is the length of the input array `nums`. This is because we iterate through the array at most once.

*   **Space Complexity:** **O(1)**, as the sorting is done in-place, meaning we don't use any extra space that scales with the input size. We only use a constant amount of extra space for the `left`, `mid`, and `right` pointers.
    