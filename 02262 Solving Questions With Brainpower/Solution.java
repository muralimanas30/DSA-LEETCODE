class Solution {
    public long mostPoints(int[][] questions) {
        long[] dp = new long[questions.length];
        Arrays.fill(dp,-1);
        return recursion(questions,0,dp);
    }
    public long recursion(int[][] questions,int i,long[] dp){
        if(i>=dp.length)    return 0l;
        if(dp[i]!=-1) return dp[i];
        long consider = questions[i][0];
        if(i+questions[i][1]+1<questions.length)
            consider += recursion(questions,i+questions[i][1]+1,dp);
        long dontConsider = recursion(questions,i+1,dp);
        dp[i] = Math.max(consider,dontConsider);
        return dp[i];
    }
}