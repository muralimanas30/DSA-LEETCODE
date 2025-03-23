# 00322 - Coin Change
    
**Language:** Java  
**Runtime:** 31 ms (Beats 21.97% of users)  
**Memory:** 46.1 MB (Beats 6.60% of users)  

## 💡 **Problem Explanation**

You are given an array of coin denominations `coins` and a total amount `amount`. You need to find the minimum number of coins required to make up that amount. If the amount cannot be made up by any combination of the coins, return -1. Assume that you have an infinite supply of each kind of coin.

**Example 1:**

*   `coins` = \[1, 2, 5], `amount` = 11
*   Output: 3
*   Explanation: 11 = 5 + 5 + 1

**Example 2:**

*   `coins` = \[2], `amount` = 3
*   Output: 2
*   Explanation: 3 = 2 + 1. Since we only have coin of denomination 2, the optimal solution is 2 coins

**Example 3:**

*   `coins` = \[1], `amount` = 0
*   Output: 0

---

## 📊 **Algorithm**

*   Sort the coins array in ascending order.
*   Initialize a 2D memoization table `memo` to store the results of subproblems. `memo[i][j]` will store the minimum number of coins to make up amount `j` using coins up to index `i` in the `coins` array.
*   Implement a recursive `backtrack` function with the following logic:
    *   Base cases:
        *   If `amount` is 0, return 0 (no coins needed).
        *   If `idx` is out of bounds or `amount` is negative, return `Integer.MAX_VALUE` (invalid state).
    *   Memoization:
        *   If `memo[idx][amount]` is not -1, return the stored value.
    *   Recursive step:
        *   `exclude`: Don't include the current coin. Recursively call `backtrack` with the next coin (index `idx - 1`) and the same `amount`.
        *   `include`: Include the current coin if possible (i.e., `amount >= coins[idx]`). Recursively call `backtrack` with the same coin (index `idx`) and reduced `amount` (`amount - coins[idx]`). Add 1 to the result to account for the included coin.
    *   Store the minimum of `exclude` and `include` in `memo[idx][amount]` and return it.
*   Call the `backtrack` function with the initial parameters (`coins`, last index of `coins`, `amount`, `memo`).
*   If the result of `backtrack` is `Integer.MAX_VALUE`, it means the amount cannot be made up by the coins. Return -1. Otherwise, return the result.

---

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[][] memo = new int[coins.length][amount + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        int result = backtrack(coins, coins.length - 1, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int backtrack(int[] coins, int idx, int amount, int[][] memo) {
        if (amount == 0) return 0;
        if (idx < 0 || amount < 0) return Integer.MAX_VALUE;
        if (memo[idx][amount] != -1) return memo[idx][amount];
        int exclude = backtrack(coins, idx - 1, amount, memo);
        int include = Integer.MAX_VALUE;
        if (amount >= coins[idx]) {
            int res = backtrack(coins, idx, amount - coins[idx], memo);
            if (res != Integer.MAX_VALUE) include = res + 1;
        }
        memo[idx][amount] = Math.min(exclude, include);
        return memo[idx][amount];
    }
}
```

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n \* amount), where n is the number of coin denominations. This is due to the memoization, which ensures that each subproblem is solved only once.
*   **Space Complexity:** O(n \* amount) due to the size of the memoization table. Additionally, the recursion stack can take up O(n) space in the worst case.
    