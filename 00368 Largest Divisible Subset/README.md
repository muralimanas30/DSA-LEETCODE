# 00368 - Largest Divisible Subset
    
**Language:** Java  
**Runtime:** 13 ms (Beats 99.64% of users)  
**Memory:** 43.6 MB (Beats 20.31% of users)  

## 💡 **Problem Explanation**

The problem asks us to find the largest divisible subset from a given set of **distinct** positive integers. A divisible subset is a subset where every pair of elements (subset\[i], subset\[j]) satisfies the condition: subset\[i] % subset\[j] == 0 or subset\[j] % subset\[i] == 0.

**Example 1:**

*   **Input:** `nums = [1,2,3]`
*   **Output:** `[1,2]` or `[1,3]`

Explanation: Both `[1,2]` and `[1,3]` are valid largest divisible subsets because:

*   In `[1,2]`: 2 % 1 == 0
*   In `[1,3]`: 3 % 1 == 0

**Example 2:**

*   **Input:** `nums = [1,2,4,8]`
*   **Output:** `[1,2,4,8]`

Explanation: `[1,2,4,8]` is a valid largest divisible subset because:

*   2 % 1 == 0
*   4 % 2 == 0 and 4 % 1 == 0
*   8 % 4 == 0 and 8 % 2 == 0 and 8 % 1 == 0

## 💡 **Algorithm**

1.  **Sort the Input:** Sort the input array `nums` in ascending order. Sorting is crucial because it allows us to only check divisibility in one direction (larger number % smaller number).
2.  **Initialize DP Array:** Create a `dp` array of the same length as `nums`. `dp[i]` will store the length of the largest divisible subset ending with `nums[i]`. Initialize all elements of `dp` to 1, because a single number itself forms a divisible subset of length 1.
3.  **Initialize Previous Index Array:** Create a `prevInd` array to store the index of the previous element in the largest divisible subset ending at each index.  This will help us reconstruct the subset.  Initialize all elements to -1.
4.  **Dynamic Programming:** Iterate through the sorted `nums` array. For each number `nums[i]`, iterate through all the previous numbers `nums[j]` (where `j < i`).
    *   If `nums[i]` is divisible by `nums[j]`, and adding `nums[i]` to the subset ending at `nums[j]` results in a larger subset than the current largest subset ending at `nums[i]`, then update `dp[i]` and `prevInd[i]`.
5.  **Find the Maximum Index:** Find the index `maxInd` of the element with the largest value in the `dp` array. This `maxInd` will be the ending index of the largest divisible subset.
6.  **Reconstruct the Subset:** Starting from `nums[maxInd]`, backtrack using the `prevInd` array to reconstruct the largest divisible subset.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);

        int prevInd[] = new int[n];
        Arrays.fill(prevInd,-1);

        int maxInd = 0;

        for(int i=1;i<n;i++) {
            for(int j=0;j<i;j++) {
                if(nums[i] % nums[j] == 0 && dp[j]+1 > dp[i]) {
                    dp[i] = dp[j]+1;
                    prevInd[i] = j;
                } 
            }
            if(dp[i] > dp[maxInd]) {
                maxInd = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while(maxInd != -1) {
            ans.add(nums[maxInd]);
            maxInd = prevInd[maxInd];
        }
        return ans;
    }
}
```

## 📊 **WORKING**

Let's walk through the example `nums = [1,2,4,8]`.

1.  **Sort `nums`:** `nums` becomes `[1, 2, 4, 8]`.

2.  **Initialize `dp` and `prevInd`:**

    ```
    dp:      [1, 1, 1, 1]
    prevInd: [-1, -1, -1, -1]
    ```

3.  **DP Iteration:**

    *   `i = 1` (`nums[1] = 2`):
        *   `j = 0` (`nums[0] = 1`): `2 % 1 == 0`, `dp[0] + 1 = 2 > dp[1] = 1`. Update `dp[1] = 2`, `prevInd[1] = 0`.
        ```
        dp:      [1, 2, 1, 1]
        prevInd: [-1, 0, -1, -1]
        ```

    *   `i = 2` (`nums[2] = 4`):
        *   `j = 0` (`nums[0] = 1`): `4 % 1 == 0`, `dp[0] + 1 = 2 > dp[2] = 1`. Update `dp[2] = 2`, `prevInd[2] = 0`.
        *   `j = 1` (`nums[1] = 2`): `4 % 2 == 0`, `dp[1] + 1 = 3 > dp[2] = 2`. Update `dp[2] = 3`, `prevInd[2] = 1`.
        ```
        dp:      [1, 2, 3, 1]
        prevInd: [-1, 0, 1, -1]
        ```

    *   `i = 3` (`nums[3] = 8`):
        *   `j = 0` (`nums[0] = 1`): `8 % 1 == 0`, `dp[0] + 1 = 2 > dp[3] = 1`. Update `dp[3] = 2`, `prevInd[3] = 0`.
        *   `j = 1` (`nums[1] = 2`): `8 % 2 == 0`, `dp[1] + 1 = 3 > dp[3] = 2`. Update `dp[3] = 3`, `prevInd[3] = 1`.
        *   `j = 2` (`nums[2] = 4`): `8 % 4 == 0`, `dp[2] + 1 = 4 > dp[3] = 3`. Update `dp[3] = 4`, `prevInd[3] = 2`.

        ```
        dp:      [1, 2, 3, 4]
        prevInd: [-1, 0, 1, 2]
        ```

4.  **Find `maxInd`:** `maxInd = 3` (because `dp[3] = 4` is the maximum).

5.  **Reconstruct Subset:**

    *   Start with `ans = [8]`. `maxInd = 3`.
    *   `maxInd = prevInd[3] = 2`. `ans = [8, 4]`.
    *   `maxInd = prevInd[2] = 1`. `ans = [8, 4, 2]`.
    *   `maxInd = prevInd[1] = 0`. `ans = [8, 4, 2, 1]`.
    *   `maxInd = prevInd[0] = -1`. Stop.

6.  **Return `ans`:** `[8, 4, 2, 1]` (or `[1, 2, 4, 8]` if you reverse it).

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n<sup>2</sup>)**, where n is the length of the input array `nums`.  The nested loops in the dynamic programming step dominate the time complexity. The sorting step takes O(n log n), but it is asymptotically smaller than O(n<sup>2</sup>).
*   **Space Complexity:** **O(n)**, where n is the length of the input array `nums`.  We use `dp` and `prevInd` arrays, each of size n, to store the lengths of the divisible subsets and the previous indices, respectively.  The `ans` list also takes at most O(n) space.
    