class Solution {
    public long continuousSubarrays(int[] nums) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        long res = 0;
        int left = 0;
        map.put(nums[left],1);
        for(int right = 1;right<nums.length;right++){   
            if(Math.abs(map.firstEntry().getKey()-nums[right])<=2
                && Math.abs(map.lastEntry().getKey()-nums[right])<=2){
                map.put(nums[right],map.getOrDefault(nums[right],0)+1);
                continue;
            }
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            while(Math.abs(map.firstEntry().getKey()-map.lastEntry().getKey())>2){
                    res += (right-left);
                    map.put(nums[left],map.getOrDefault(nums[left],0)-1);
                    if(map.get(nums[left])==0)
                        map.remove(nums[left]);
                    left++;
                }
        }
        int lastValid = map.values().stream().reduce(0, Integer::sum);
        res+= (1l*lastValid*(lastValid+1l))/2;
        return res;
    }
}