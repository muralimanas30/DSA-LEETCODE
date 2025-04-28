class Solution {
    public long countSubarrays(int[] nums, long k) {
        int currLength = 1;
        long score = 0;
        long currSum = 0;
        int left = 0;
        for(int right=0;right<nums.length;right++){
            currSum += nums[right];
            while(currSum*currLength>=k){
                currSum -= nums[left++];
                currLength--;
            }
            score += currLength;
            currLength++;
        }
        return score;
    }
}
