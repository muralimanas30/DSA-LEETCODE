import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length==2){
            return new int[]{0,1};
        }
        Map<Integer , Integer > hs = new HashMap<>();
        
        for(int i =0 ; i< nums.length ; i++){
            int difference = target - nums[i];
            
            if(hs.containsKey(difference)){
                return new int[]{ hs.get(difference) , i };
            }
            hs.put(nums[i] , i);
        }

    return new int[]{1,2};
    }
}
