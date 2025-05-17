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

Given an array `nums` with `n` objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue. We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively. You must solve this problem without using the library's sort function.

**Example:**

Input: `nums = [2,0,2,1,1,0]`
Output: `nums = [0,0,1,1,2,2]`

Input: `nums = [2,0,1]`
Output: `nums = [0,1,2]`

---

## 📊 **Algorithm**

*   Initialize three pointers: `left`, `mid`, and `right`. `left` points to the beginning of the array (index 0), `mid` points to the current element being examined, and `right` points to the end of the array (index nums.length - 1).

*   Iterate through the array while `mid <= right`.

*   Use a `switch` statement on `nums[mid]` to handle the three possible cases:
    *   If `nums[mid] == 0`: Swap `nums[mid]` with `nums[left]`, increment both `mid` and `left`.
    *   If `nums[mid] == 1`: Increment `mid`.
    *   If `nums[mid] == 2`: Swap `nums[mid]` with `nums[right]`, decrement `right`.  (Note: Do NOT increment `mid` in this case, as the swapped element at `mid` needs to be examined in the next iteration).

*   The algorithm ensures that all 0s are moved to the left, all 2s are moved to the right, and all 1s end up in the middle.

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

This problem doesn't directly involve grids.

---

## 📊 **WORKING**

Let's trace the algorithm with the input `nums = [2,0,2,1,1,0]`:

1.  Initial state: `left = 0`, `mid = 0`, `right = 5`, `nums = [2,0,2,1,1,0]`
2.  `nums[mid] == 2`: Swap `nums[0]` and `nums[5]`. `nums = [0,0,2,1,1,2]`, `right = 4`
3.  `nums[mid] == 0`: Swap `nums[0]` and `nums[0]`. `nums = [0,0,2,1,1,2]`, `mid = 1`, `left = 1`
4.  `nums[mid] == 0`: Swap `nums[1]` and `nums[1]`. `nums = [0,0,2,1,1,2]`, `mid = 2`, `left = 2`
5.  `nums[mid] == 2`: Swap `nums[2]` and `nums[4]`. `nums = [0,0,1,1,2,2]`, `right = 3`
6.  `nums[mid] == 1`: `mid = 3`
7.  `nums[mid] == 1`: `mid = 4`
8.  `mid > right` (4 > 3), so the loop terminates.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where n is the length of the input array. We iterate through the array once.

*   **Space Complexity:** **O(1)**.  The algorithm sorts the array in-place, using only a constant amount of extra space for the pointers.
    