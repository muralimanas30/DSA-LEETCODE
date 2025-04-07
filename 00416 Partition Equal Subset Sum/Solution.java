class Solution {
    public boolean canPartition(int[] nums) {
        int tot = 0;
        for (int num : nums) tot += num;

        if ((tot & 1) == 1) return false;

        int target = tot / 2;
        HashSet<Integer> hs = new HashSet<>();
        hs.add(0);

        for (int num : nums) {
            HashSet<Integer> newSet = new HashSet<>(hs);
            for (int x : hs) {
                if (x + num == target) return true;
                newSet.add(x + num);
            }
            hs = newSet;
        }

        return hs.contains(target);
    }
}
