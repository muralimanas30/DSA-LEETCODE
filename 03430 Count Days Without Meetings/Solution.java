class Solution {
    public int countDays(int days, int[][] meetings) {
    Arrays.sort(meetings,(a,b)->a[0]-b[0]);
    int count = 0;
    int currentDay = 1;
    for(int [] arr : meetings){
        count += Math.max(arr[0]-currentDay,0);
        currentDay = Math.max(arr[1]+1,currentDay);
    }
    if(currentDay<=days)
        count += days-currentDay+1;
        return count;
    }
}
// APPLICABLE IF DAYS ARE LESS THAN 10^5
 //     int[] prefix = new int[days+1];
    //     for(int[] meeting : meetings){
    //         prefix[meeting[0]]++;
    //         if(meeting[1]!=days)
    //             prefix[meeting[1]+1]--;
    //     }
    //     int count = 0;
    //     for(int i=1;i<=days;i++){
    //         prefix[i]+=prefix[i-1];
    //         if(prefix[i]==0)
    //             count++;
    //     }
    //     return count;
    // }