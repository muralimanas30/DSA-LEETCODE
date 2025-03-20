# 00078 - subsets
    
**Language:** Java  
**Runtime:** 1 ms (Beats 53.11% of users)  
**Memory:** 42.9 MB (Beats 30.23% of users)  

## Solution
```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        // res.add(new ArrayList<Integer>());
        int n = nums.length;
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
            res.add(new ArrayList<>(subset));
        }
        return new ArrayList<>(res);
    }
}
```
    