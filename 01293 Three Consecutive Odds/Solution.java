class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        for(int i=1;i<arr.length-1;i++){
            if((arr[i]&1)==0) i++;
            else{
                if((arr[i+1]&1)==0) i+=2;
                else if((arr[i-1]&1)==0)
                    continue;
                else
                    return true;
            }
        }
        return false;
    }
}