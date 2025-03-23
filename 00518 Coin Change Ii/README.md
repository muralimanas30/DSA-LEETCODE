# 00518 - Coin Change Ii
    
**Language:** Java  
**Runtime:** 11 ms (Beats 36.17% of users)  
**Memory:** 52.3 MB (Beats 6.12% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 518 | Coin Change II | [LeetCode Problem](https://leetcode.com/problems/coin-change-ii/) |

---

## 💡 **Problem Explanation**

You are given an integer `amount` representing a total amount of money, and an array of integers `coins` representing different denominations of coins. You need to find out how many unique combinations of coins you can use to make up that amount.

Let's break it down with an example:

**Example:**

```
Amount = 5
Coins = [1, 2, 5]
```

The combinations to make up the amount 5 are:

1.  5 = 5
2.  5 = 2 + 2 + 1
3.  5 = 2 + 1 + 1 + 1
4.  5 = 1 + 1 + 1 + 1 + 1

So, the output should be 4.

---

## 💡 **Algorithm**

*   Create a 2D DP (Dynamic Programming) array `dp` to store the number of combinations. `dp[i][j]` will represent the number of ways to make up amount `j` using the first `i+1` coins.
*   Initialize the first row and column of the DP array.
*   Iterate through the DP array, filling it in using the following logic:
    *   If the current coin's value is less than or equal to the current amount, then the number of combinations is the sum of:
        *   The number of combinations without using the current coin (`dp[i-1][j]`).
        *   The number of combinations using the current coin (`dp[i][j - coins[i]]`).
    *   If the current coin's value is greater than the current amount, then the number of combinations is the same as the number of combinations without using the current coin (`dp[i-1][j]`).
*   The final result is stored in `dp[coins.length-1][amount]`.

## 🔥 **Code Implementation**

```java
class Solution {
    public int change(int amount, int[] coins) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        return backtrack(dp, coins, coins.length - 1, amount);
    }

    private int backtrack(Integer[][] dp, int[] coins, int idx, int amount) {
        if (amount == 0) return 1;
        if (idx < 0 || amount < 0) return 0;
        if (dp[idx][amount] != null) return dp[idx][amount];

        int exclude = backtrack(dp, coins, idx - 1, amount);
        int include = backtrack(dp, coins, idx, amount - coins[idx]);

        dp[idx][amount] = exclude + include;
        return dp[idx][amount];
    }
}
```

## 📊 **ASCII Representation**

Not applicable for this problem.

## 📊 **WORKING**

Let's walk through the example where `amount = 5` and `coins = [1, 2, 5]`.

1.  **Initialization:**

The `dp` table will be of size `3x6` (coins.length x amount+1).

2.  **Iteration:**

We fill the table based on whether to include a coin or not.

| Coin | Amount = 0 | Amount = 1 | Amount = 2 | Amount = 3 | Amount = 4 | Amount = 5 |
|------|------------|------------|------------|------------|------------|------------|
| 1    | 1          | 1          | 1          | 1          | 1          | 1          |
| 2    | 1          | 1          | 2          | 2          | 3          | 3          |
| 5    | 1          | 1          | 2          | 2          | 3          | 4          |

*   **Coin 1:** We can always add 1 more way to make the amount.
*   **Coin 2:** We either exclude it (use the value above) or include it if the amount is sufficient.
*   **Coin 5:** Same logic as coin 2.  For amount 5, we either exclude it (3 ways) or include it (1 way: using only the coin 5 itself). 3+1 = 4.

3.  **Final Result:**

`dp[2][5]` (last coin, amount 5) = 4.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n * amount)**, where `n` is the number of coins. We iterate through the DP table once.
*   **Space Complexity:** **O(n * amount)**,  for storing the DP table.
    