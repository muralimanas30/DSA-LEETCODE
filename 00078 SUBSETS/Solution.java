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