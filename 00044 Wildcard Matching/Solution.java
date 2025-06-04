class Solution {
    public boolean isMatch(String s, String p) {

        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();

        boolean[][] dp = new boolean[str.length + 1][ptr.length + 1];
        dp[0][0] = true;

        for (int j = 1; j <= ptr.length; j++) {
            if (ptr[j - 1] == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= str.length; i++) {
            for (int j = 1; j <= ptr.length; j++) {
                if (ptr[j - 1] == '?' || ptr[j - 1] == str[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ptr[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[str.length][ptr.length];
    }
}
