class Solution {
    public long maximumTripletValue(int[] nums) {
        long[] prefix = new long[nums.length];
        long res = 0;
        prefix[0] = nums[0];
        for(int i=1;i<nums.length;i++)
            prefix[i] = Math.max(prefix[i-1],(1l*nums[i]));
        long[] suffix = new long[nums.length];
        suffix[nums.length-1] = nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--)
            suffix[i] = Math.max(suffix[i+1],(1l*nums[i]));
        for(int i=1;i<nums.length-1;i++){
            res = Math.max(res,(prefix[i-1]-(1l*nums[i]))*suffix[i+1]);
        }
        return res;
    }
}