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