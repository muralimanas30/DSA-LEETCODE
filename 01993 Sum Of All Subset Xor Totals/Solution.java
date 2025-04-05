class Solution {
    public int subsetXORSum(int[] nums) {
        Map<String, Integer> dp = new HashMap<>();
        return backtrack(nums, 0, dp, 0);
    }

    private int backtrack(int[] nums, int index, Map<String, Integer> dp, int res) {
        if (index == nums.length) {
            return res;
        }

        String key = index + "," + res;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int consider = backtrack(nums, index + 1, dp, res ^ nums[index]);
        int notConsider = backtrack(nums, index + 1, dp, res);

        int total = consider + notConsider;
        dp.put(key, total);
        return total;
    }
}
