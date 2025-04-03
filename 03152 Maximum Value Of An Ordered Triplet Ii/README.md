# 03152 - Maximum Value Of An Ordered Triplet Ii
    
**Language:** Java  
**Runtime:** 3 ms (Beats 61.29% of users)  
**Memory:** 59.6 MB (Beats 45.70% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3152 | MAXIMUM VALUE OF AN ORDERED TRIPLET II | [LeetCode Problem](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/) |

---

## 💡 **Problem Explanation**

Given an array `nums`, the goal is to find the maximum value of the ordered triplet `(nums[i] - nums[j]) * nums[k]` where `i < j < k`. If no such triplet exists, the result should be 0.  Essentially, we need to find three indices such that the expression yields the largest possible value.

**Example:**

Let's say `nums = [1, 2, 3]`.

-   For `i=0`, `j=1`, `k=2`, the value is `(1-2)*3 = -3`.
Since there are no other possible triplets, the maximum value is -3 and since we should return 0 if result is negative the answer is 0.

Let's say `nums = [1, 10, 3, 4, 5]`

We are looking for the maximum of `(nums[i] - nums[j]) * nums[k]`

*  i=1, j=2, k=3 gives (10-3)*4 = 28
*  i=1, j=2, k=4 gives (10-3)*5 = 35
*  i=1, j=3, k=4 gives (10-4)*5 = 30

```cpp
Input: nums = [1,2,3]
Output: 0
Explanation: The only triplet that can be found is (0, 1, 2)
(nums[0] - nums[1]) * nums[2] = (1 - 2) * 3 = -3. Since -3 < 0, we return 0.
```

```cpp
Input: nums = [1,10,3,4,5]
Output: 35
Explanation:
The triplet (1, 2, 4) gives the maximum value.
(nums[1] - nums[2]) * nums[4] = (10 - 3) * 5 = 35.
```

---

## 📊 **Algorithm**

1.  Create a `prefix` array to store the maximum value from the beginning of the array up to each index.
2.  Create a `suffix` array to store the maximum value from the end of the array up to each index.
3.  Iterate through the `nums` array from index 1 to `nums.length - 2`.
4.  For each index `i`, calculate `(prefix[i-1] - nums[i]) * suffix[i+1]`.
5.  Update the `res` variable with the maximum value encountered so far.
6.  Return the final `res`.

## 🔥 **Code Implementation**

```java
class Solution {
    public long maximumTripletValue(int[] nums) {
        long[] prefix = new long[nums.length];
        long res = 0;
        prefix[0] = nums[0];
        for(int i=1;i<nums.length;i++)
            prefix[i] = Math.max(prefix[i-1],(1l*nums[i]));
        long[] suffix = new long[nums.length];
        suffix[nums.length-1] = nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--)
            suffix[i] = Math.max(suffix[i+1],(1l*nums[i]));
        for(int i=1;i<nums.length-1;i++){
            res = Math.max(res,(prefix[i-1]-(1l*nums[i]))*suffix[i+1]);
        }
        return res;
    }
}
```

---

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees, so an ASCII representation isn't applicable. It's primarily an array manipulation problem.

---

## 📊 **WORKING**

Let's trace the execution with the input `nums = [1, 10, 3, 4, 5]`.

1. **Initialization:**
   - `prefix` array: `[0, 0, 0, 0, 0]`
   - `suffix` array: `[0, 0, 0, 0, 0]`
   - `res = 0`

2. **Build `prefix` array:**
   - `prefix[0] = nums[0] = 1`
   - `i = 1`: `prefix[1] = Math.max(prefix[0], nums[1]) = Math.max(1, 10) = 10`
   - `i = 2`: `prefix[2] = Math.max(prefix[1], nums[2]) = Math.max(10, 3) = 10`
   - `i = 3`: `prefix[3] = Math.max(prefix[2], nums[3]) = Math.max(10, 4) = 10`
   - `i = 4`: `prefix[4] = Math.max(prefix[3], nums[4]) = Math.max(10, 5) = 10`
   - `prefix` is now `[1, 10, 10, 10, 10]`

3. **Build `suffix` array:**
   - `suffix[4] = nums[4] = 5`
   - `i = 3`: `suffix[3] = Math.max(suffix[4], nums[3]) = Math.max(5, 4) = 5`
   - `i = 2`: `suffix[2] = Math.max(suffix[3], nums[2]) = Math.max(5, 3) = 5`
   - `i = 1`: `suffix[1] = Math.max(suffix[2], nums[1]) = Math.max(5, 10) = 10`
   - `i = 0`: `suffix[0] = Math.max(suffix[1], nums[0]) = Math.max(10, 1) = 10`
   - `suffix` is now `[10, 10, 5, 5, 5]`

4. **Iterate to find the maximum triplet value:**
   - `i = 1`: `res = Math.max(res, (prefix[0] - nums[1]) * suffix[2]) = Math.max(0, (1 - 10) * 5) = Math.max(0, -45) = 0`
   - `i = 2`: `res = Math.max(res, (prefix[1] - nums[2]) * suffix[3]) = Math.max(0, (10 - 3) * 5) = Math.max(0, 35) = 35`
   - `i = 3`: `res = Math.max(res, (prefix[2] - nums[3]) * suffix[4]) = Math.max(35, (10 - 4) * 5) = Math.max(35, 30) = 35`

5. **Return `res` (35)**

---

## 🚀 **Time & Space Complexity**

-   **Time Complexity:** **O(n)**, where n is the length of the `nums` array. This is because we iterate through the array three times: once to build the `prefix` array, once to build the `suffix` array, and once to find the maximum triplet value.
-   **Space Complexity:** **O(n)**, where n is the length of the `nums` array.  We use two additional arrays, `prefix` and `suffix`, each of size n, to store intermediate results.
    