# 03621 - Minimum Operations To Make Array Values Equal To K
    
**Language:** Java  
**Runtime:** 1 ms (Beats 100.00% of users)  
**Memory:** 45 MB (Beats 8.47% of users)  

## 💡 **Problem Explanation**

The problem asks to find the minimum operations to make all array values equal to `k`.
Essentially, for each number in `nums`, if it's less than k, you should return -1 because its an impossible problem since you cannot decrease the numbers.
Otherwise, if the number is greater than or equal to k, determine the operations, the operations is to count the distinct numbers in nums that are not k.

**Example 1:**

```
Input: nums = [2, 1, 3, 4, 6], k = 1
Output: 4
```

Explanation:
All array element should be equal to k(1).
2 > 1
1 = 1
3 > 1
4 > 1
6 > 1
So the solution is to just return the numbers greater than k(1), which is 4.

**Example 2:**

```
Input: nums = [1, 1, 2, 2, 3], k = 2
Output: 2
```

Explanation:
All array element should be equal to k(2).
1 < 2, so return -1

**Example 3:**

```
Input: nums = [1, 2, 2, 3, 4], k = 2
Output: -1
```

Explanation:
All array element should be equal to k(2).
1 < 2, so return -1

## 📊 **Algorithm**

*   Initialize a boolean array `hs` of size 101 to keep track of numbers encountered.
*   Initialize a counter `count` to 0.
*   Iterate through the `nums` array.
    *   If any element is less than `k`, return -1.
    *   If an element is greater than or equal to `k` and has not been seen before (not in `hs`), mark it as seen in `hs` and increment the counter `count`.
*   If `k` is present in `hs`, return `count - 1`, otherwise return `count`.

## 🔥 **Code Implementation**

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        boolean[] hs = new boolean[100+1];
        int count = 0;
        for(int i=0 ; i < nums.length; i++)
            if(nums[i]<k)   return -1;
            else
                if(!hs[nums[i]]){
                    hs[nums[i]] = true;
                    count++;
                }
        return hs[k]?count-1:count;
        
    }
}
```

## 📊 **ASCII Representation**

N/A. This problem doesn't involve grids, trees, or movements.

## 📊 **WORKING**

Let's trace the execution with `nums = [2, 2, 3, 4, 5]`, `k = 2`.

1.  `hs = [false, false, ..., false]` (size 101), `count = 0`
2.  Loop through `nums`:
    *   `nums[0] = 2`: `2 >= 2`. `hs[2] == false`. `hs[2] = true`, `count = 1`. `hs = [false, false, true, ...]`
    *   `nums[1] = 2`: `2 >= 2`. `hs[2] == true`. Skip.
    *   `nums[2] = 3`: `3 >= 2`. `hs[3] == false`. `hs[3] = true`, `count = 2`. `hs = [false, false, true, true, ...]`
    *   `nums[3] = 4`: `4 >= 2`. `hs[4] == false`. `hs[4] = true`, `count = 3`. `hs = [false, false, true, true, true, ...]`
    *   `nums[4] = 5`: `5 >= 2`. `hs[5] == false`. `hs[5] = true`, `count = 4`. `hs = [false, false, true, true, true, true, ...]`
3.  `hs[k] == hs[2] == true`.  Return `count - 1 = 4 - 1 = 3`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n), where n is the length of the `nums` array. We iterate through the array once.
*   **Space Complexity:** O(1). Because the boolean array has fixed size.
    