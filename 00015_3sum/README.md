# 00015 - 3sum
    
**Language:** Java  
**Runtime:** 52 ms (Beats 26.30% of users)  
**Memory:** 52.2 MB (Beats 32.25% of users)  

## Solution
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int sum=0 , current = 0 , left = 0 ,right = nums.length-1;
        Arrays.sort(nums);
        Set<List<Integer>> final_list = new HashSet<>();
        for(int i =0; i < nums.length-2 ;i++){
            left = i+1;right = nums.length - 1;
            current = nums[i];
            while(left<right){
                sum = nums[left] + nums[right] + current;
                // System.out.println( current+" "+nums[left] +" "+ nums[right]+" "+sum);
            if(sum==0){
                List<Integer> l = new ArrayList<>();
                l.add(current); l.add(nums[left]); l.add(nums[right]);
                final_list.add(l); 
                // final_list.add(Arrays.asList(current, nums[left],nums[right]));




                while( left < right && nums[left] == nums[left+1]) left++;
                while( left < right && nums[right] == nums[right-1]) right--;
                    
                left++;right--;
            }
            else{
                if(sum<0){
                    left++;
                }
                else{
                    right--;
                }
            }
            }
        }
    return new ArrayList<List<Integer>>(final_list);
    }
}
```
    