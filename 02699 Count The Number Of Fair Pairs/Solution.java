class Solution {
    private boolean first = true;

    public long countFairPairs(int[] nums, int lower, int upper) {
        long count = 0;
        if(first){
            Arrays.sort(nums);
            first = false;
        }
        for (int i = 0; i < nums.length; i++) {
            int lesserNums = binarySearch(nums, i + 1, lower - nums[i], true);
            int higherNums = binarySearch(nums, i + 1, upper - nums[i], false);
            count += higherNums - lesserNums + 1;
        }
        return count;
    }

    private int binarySearch(int[] nums, int start, int val, boolean lower) {
        int low = start;
        int high = nums.length - 1;
        int res = lower ? nums.length : start - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (lower) {
                if (nums[mid] >= val) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] <= val) {
                    res = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return res;
    }
}
