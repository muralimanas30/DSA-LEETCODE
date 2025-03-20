# 00090 - subsets-ii
    
**Language:** Java  
**Runtime:** 4 ms (Beats 13.97% of users)  
**Memory:** 44.4 MB (Beats 5.45% of users)  

## Solution
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<ArrayList<Integer>> res = new HashSet<>();
        // res.add(new ArrayList<Integer>());
        int n = nums.length;
        Arrays.sort(nums);
        for(int i=0;i<(1<<n);i++){
            int index =0 ;
            ArrayList<Integer> subset = new ArrayList<>();
            int num = i;
            while(num>0){
                if((num&1)==1)
                    subset.add(nums[index]);
                num>>=1;
                index++;
            }
            // Collections.sort(subset);
            res.add(new ArrayList<>(subset));
        }
        return new ArrayList<>(res);
    }
}
```
    