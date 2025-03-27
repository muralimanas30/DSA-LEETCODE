class Solution {
    public int minimumIndex(List<Integer> nums) {
        int[] ans = getFrequency(0, nums.size() - 1, nums);
        int highF = ans[1];
        int ele = ans[0];

        int lcount = 0, rcount = highF;

        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) == ele) {
                lcount++;
                rcount = highF - lcount;
            }

            int leftSize = i + 1;
            int rightSize = nums.size() - leftSize;

            if (lcount * 2 > leftSize && rcount * 2 > rightSize) {
                return i;
            }
        }
        return -1;
    }


    public int[] getFrequency(int i, int j, List<Integer> nums) {
        int minReqLength = (j-i+1)/2;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (; i <= j; i++)
            hm.put(nums.get(i), hm.getOrDefault(nums.get(i), 0) + 1);

        int highF = 0;
        int ele = 0;
        for (Map.Entry<Integer, Integer> e : hm.entrySet()) {
            if (e.getValue() > minReqLength) {
                highF = e.getValue();
                ele = e.getKey();
                return new int[]{ele, highF};        
            }
        }
        return new int[]{ele, highF};
    }
}



// BRUTE FORCE APPROACH 
    // public int minimumIndex(List<Integer> nums) {
    //     int[] ans = getFrequency(0, nums.size() - 1, nums);
    //     int highF = ans[1];
    //     int ele = ans[0];

    //     for (int i = 0; i < nums.size() - 1; i++) {
    //         int[] left = getFrequency(0, i, nums);
    //         int[] right = getFrequency(i + 1, nums.size() - 1, nums);
    //         int e1 = left[0];
    //         int e2 = right[0];

    //         if (e1 != e2 || e1 != ele || e2 != ele)
    //             continue;
    //         return i;
    //     }
    //     return -1;
    // }