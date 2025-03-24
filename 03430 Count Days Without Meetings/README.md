# 03430 - Count Days Without Meetings
    
**Language:** Java  
**Runtime:** 36 ms (Beats 89.90% of users)  
**Memory:** 101 MB (Beats 17.85% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3430 | COUNT DAYS WITHOUT MEETINGS | [LeetCode Problem](https://leetcode.com/problems/count-days-without-meetings/) |

---

## 💡 **Problem Explanation**

The problem asks us to count the number of days without meetings, given the total number of days and a list of meeting schedules. Each meeting is defined by a start and end day (inclusive). We need to find the days that don't overlap with any meetings.

For example:

*   `days = 10`, `meetings = [[2, 3], [7, 9]]`
    *   Meetings occur on days 2, 3, 7, 8, and 9.
    *   Days without meetings: 1, 4, 5, 6, 10.
    *   Result: 5

*   `days = 5`, `meetings = [[1, 1], [2, 2]]`
    *   Meetings occur on days 1 and 2.
    *   Days without meetings: 3, 4, 5.
    *   Result: 3

## 📊 **Algorithm**
* Sort the meetings based on their start days.
* Initialize `count` to 0 and `currentDay` to 1. `count` keeps track of the days without meetings, and `currentDay` represents the day we are currently checking.
* Iterate through the sorted meetings:
    * Add the number of days between `currentDay` and the meeting's start day to `count`. We use `Math.max(arr[0] - currentDay, 0)` to ensure that we don't add negative days if `currentDay` is already past the meeting's start day.
    * Update `currentDay` to be the day after the meeting ends. We use `Math.max(arr[1] + 1, currentDay)` to advance `currentDay`.
* After iterating through all meetings, add the number of days from `currentDay` to the end of the schedule to `count`. We use `days - currentDay + 1`.
* Return `count`.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

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
```

## 📊 **ASCII Representation**

Since this problem doesn't involve grids, trees, or movements, an ASCII representation is not applicable.

## 📊 **WORKING**

Let's walk through an example: `days = 10`, `meetings = [[2, 3], [7, 9]]`

1.  **Sort meetings:** `meetings` remains `[[2, 3], [7, 9]]` since it's already sorted.
2.  **Initialize:** `count = 0`, `currentDay = 1`.
3.  **First meeting `[2, 3]`:**
    *   `count += Math.max(2 - 1, 0) = 1`
    *   `currentDay = Math.max(3 + 1, 1) = 4`
4.  **Second meeting `[7, 9]`:**
    *   `count += Math.max(7 - 4, 0) = 3`
    *   `currentDay = Math.max(9 + 1, 4) = 10`
5.  **After the loop:**
    *   `count += 10 - 10 + 1 = 1`
6.  **Return:** `count = 1 + 3 + 1 = 5`

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The dominant operation is sorting the meetings, which takes **O(N log N)** time, where N is the number of meetings. The rest of the code iterates through the meetings once, which takes **O(N)** time.  Therefore, the overall time complexity is **O(N log N)**.

*   **Space Complexity:** The space complexity is **O(1)** since we are only using a few extra variables. The sorting is done in place, and the auxiliary space used does not depend on the input size.
    