class Solution {
    public int minOperations(int[] nums, int k) {
        boolean[] hs = new boolean[100+1];
        int count = 0;
        for(int i=0 ; i < nums.length; i++)
            if(nums[i]<k)   return -1;
            else
                if(!hs[nums[i]]){
                    hs[nums[i]] = true;
                    count++;
                }
        return hs[k]?count-1:count;
        
    }
}