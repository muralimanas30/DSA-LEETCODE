class Solution {
    int finalLow = 0;
    int finalHigh = 0;
    int ans = 0;

    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][n+1];
        for(int i=0;i<=n;i++)   dp[i][i] = true;
        int maxLength = 1, start = 0;

        for(int length = 2; length <= n; length++){
            for(int i=0; i+length-1 < n; i++ ){
                int j = i+length-1;
                dp[i][j] = arr[i]==arr[j] && (length<=2 || dp[i+1][j-1]);
                if(dp[i][j] && length>maxLength){
                    maxLength = length;
                    start = i;
                }
            }
        }
        return s.substring(start,start+maxLength);
    }
}
