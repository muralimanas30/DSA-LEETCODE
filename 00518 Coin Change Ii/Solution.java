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
