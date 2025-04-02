# 03154 - Maximum Value Of An Ordered Triplet I
    
**Language:** Java  
**Runtime:** 3 ms (Beats 53.95% of users)  
**Memory:** 42 MB (Beats 72.34% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3154 | MAXIMUM VALUE OF AN ORDERED TRIPLET I | [LeetCode Problem](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/) |

---

## 💡 **Problem Explanation**

Given an array of integers, `nums`, the goal is to find the maximum value of the triplet `(nums[i] - nums[j]) * nums[k]` where `i < j < k`. If all such triplets result in a negative value, the answer should be 0.

**Example:**

`nums = [12, 6, 1, 2, 7]`

We need to iterate over all possible triplets (i, j, k) such that i < j < k and calculate `(nums[i] - nums[j]) * nums[k]`.  We then return the maximum value found.

For instance:

*   (i=0, j=1, k=2): (12 - 6) * 1 = 6
*   (i=0, j=1, k=3): (12 - 6) * 2 = 12
*   (i=0, j=1, k=4): (12 - 6) * 7 = 42

and so on.  The largest value among all such triplets is the answer.

`nums = [1, 10, 3, 4, 5]`

The result is (1 - 3) * 5 = -10
(1 - 4) * 5 = -15
(1 - 5) * 4 = -16
(10 - 3) * 4 = 28
(10 - 4) * 5 = 30
(10 - 5) * 4 = 20
(3 - 4) * 5 = -5
and so on.  The largest value among all such triplets is 30.

If the maximum value is negative, or zero, the result is zero.

---

## 📊 **Algorithm**

*   Initialize a variable `res` to 0. This variable will store the maximum triplet value.
*   Iterate through all possible indices `i`, `j`, and `k` such that `0 <= i < n-2`, `i+1 <= j < n-1`, and `j+1 <= k < n`.
*   For each triplet `(i, j, k)`, calculate the value `(nums[i] - nums[j]) * nums[k]`.
*   Update `res` to be the maximum of its current value and the calculated triplet value. Since the result might be larger than `int` convert nums[k] to `long` before the multiplication.
*   After iterating through all possible triplets, return `res`.

## 🔥 **Code Implementation**

```java
class Solution {
    public long maximumTripletValue(int[] nums) {
        long res = 0;
        int n = nums.length;
        for(int i=0; i< n-2; i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    res = Math.max(res,(nums[i]-nums[j])*(1l*nums[k]));
                }
            }
        }
        return res;
    }
}
```

## 📊 **ASCII Representation**

N/A - This problem does not involve grids, trees, or movements that are conducive to ASCII representation.

## 📊 **WORKING**

Let's take the example `nums = [12, 6, 1, 2, 7]` and trace the execution.

1.  `res = 0`
2.  Outer loop (`i`):
    *   `i = 0`:
        *   Middle loop (`j`):
            *   `j = 1`:
                *   Inner loop (`k`):
                    *   `k = 2`: `(12 - 6) * 1 = 6`. `res = max(0, 6) = 6`
                    *   `k = 3`: `(12 - 6) * 2 = 12`. `res = max(6, 12) = 12`
                    *   `k = 4`: `(12 - 6) * 7 = 42`. `res = max(12, 42) = 42`
            *   `j = 2`:
                *   Inner loop (`k`):
                    *   `k = 3`: `(12 - 1) * 2 = 22`. `res = max(42, 22) = 42`
                    *   `k = 4`: `(12 - 1) * 7 = 77`. `res = max(42, 77) = 77`
        *   `j = 3`:
                *   Inner loop (`k`):
                    *   `k = 4`: `(12 - 2) * 7 = 70`. `res = max(77, 70) = 77`
    *   `i = 1`:
        *   Middle loop (`j`):
            *   `j = 2`:
                *   Inner loop (`k`):
                    *   `k = 3`: `(6 - 1) * 2 = 10`. `res = max(77, 10) = 77`
                    *   `k = 4`: `(6 - 1) * 7 = 35`. `res = max(77, 35) = 77`
            *   `j = 3`:
                *   Inner loop (`k`):
                    *   `k = 4`: `(6 - 2) * 7 = 28`. `res = max(77, 28) = 77`
    *   `i = 2`:
        *   Middle loop (`j`):
            *   `j = 3`:
                *   Inner loop (`k`):
                    *   `k = 4`: `(1 - 2) * 7 = -7`. `res = max(77, -7) = 77`
3.  `i = 3`:
    *   Middle loop (`j`): will not be executed because j needs to be i + 1 and less than n - 1 (which is 4). Hence, j can only be 4 which is impossible, the middle loop condition requires to be less than 4.

Finally, the algorithm returns `77`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The time complexity is **O(n^3)** because of the three nested loops, where n is the length of the input array `nums`.

*   **Space Complexity:** The space complexity is **O(1)** because we only use a constant amount of extra space to store the `res` variable.
    