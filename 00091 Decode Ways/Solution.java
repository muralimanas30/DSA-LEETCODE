class Solution {

    public int numDecodings(String s) {
        char[] arr = s.toCharArray();
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return decode(arr, dp, 0);
    }

    public int decode(char[] arr, int[] dp, int idx) {
        if (idx == arr.length) return 1;
        if (arr[idx] == '0') return 0;
        if (dp[idx] != -1) return dp[idx];

        int ways = decode(arr, dp, idx + 1);

        if (idx + 1 < arr.length) {
            int num = (arr[idx] - '0') * 10 + (arr[idx + 1] - '0');
            if (num >= 10 && num <= 26) {
                ways += decode(arr, dp, idx + 2);
            }
        }

        dp[idx] = ways;
        return dp[idx];
    }
}
