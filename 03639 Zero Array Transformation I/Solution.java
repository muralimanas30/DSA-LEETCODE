class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int[] sub : queries) {
            prefix[sub[0]] += 1;
            if (sub[1] + 1 < prefix.length) {
                prefix[sub[1] + 1] -= 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += prefix[i];
            if (Math.max(0, nums[i] - sum) != 0) return false;
        }

        return true;
    }
}
