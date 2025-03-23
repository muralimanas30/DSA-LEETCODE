
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
