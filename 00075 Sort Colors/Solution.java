class Solution {
    public void sortColors(int[] nums) {
        int mid=0,left=0,right =nums.length-1;
        while(mid<=right){
            switch(nums[mid]){
                case 0 : {
                    int temp = nums[left];
                    nums[left] = nums[mid];
                    nums[mid] = temp;
                    mid++;left++;
                    break;
                }
                case 1 : mid++;break;
                case 2 : {
                    int temp = nums[right];
                    nums[right] = nums[mid];
                    nums[mid] = temp;
                    right--;
                    break;
                }
            }
        }
    }
}