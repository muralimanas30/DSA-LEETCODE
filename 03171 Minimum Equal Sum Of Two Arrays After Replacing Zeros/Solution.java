class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long ls = 0,rs = 0;
        int lc = 0,rc = 0;
        for(int x : nums1)  if(x==0)    lc++; else ls+=x;
        for(int x : nums2)  if(x==0)    rc++; else rs+=x;
        if((rs+rc*1l)>ls && lc*1l==0)
            return -1;
        if((ls+lc*1l)>rs && rc*1l==0)
            return -1;
        
        long minL = ls + lc*1l;
        long minR = rs + rc*1l;
        return Math.max(minL,minR);
    }
}