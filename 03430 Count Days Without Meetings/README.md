# 03430 - Count Days Without Meetings
    
**Language:** Java  
**Runtime:** 36 ms (Beats 89.90% of users)  
**Memory:** 101 MB (Beats 17.85% of users)  

## 💡 **Problem Explanation**

The problem asks us to calculate the number of days in a given range (1 to `days`) where no meetings are scheduled. We are given a list of meetings, where each meeting is defined by a start day and an end day (inclusive). The goal is to count the days that do not fall within any meeting interval.

**Example:**

Let's say `days = 10` and `meetings = [[2, 4], [6, 8]]`.

This means there are meetings scheduled from day 2 to day 4, and from day 6 to day 8.  Therefore, days 1, 5, 9, and 10 are without meetings. The output should be 4.

## 📊 **Algorithm**

1.  **Sort the meetings:** Sort the meetings based on their start days in ascending order. This helps in processing the meetings chronologically.
2.  **Initialize variables:**
    *   `count = 0`:  This variable will store the number of days without meetings.
    *   `currentDay = 1`: This variable tracks the current day we are checking for meeting conflicts.
3.  **Iterate through the sorted meetings:**
    *   For each meeting `[start, end]`:
        *   Calculate the number of free days between the `currentDay` and the `start` of the meeting:  `Math.max(start - currentDay, 0)`. Add this to `count`.
        *   Update `currentDay` to the day after the end of the meeting, ensuring that it is not less than the current value of `currentDay`: `currentDay = Math.max(end + 1, currentDay)`.
4.  **Handle remaining days:** After processing all meetings, if `currentDay` is less than or equal to `days`, it means there are free days remaining from `currentDay` to `days`. Add these to `count`.
5.  **Return `count`:** This is the total number of days without meetings.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
    Arrays.sort(meetings,(a,b)->a[0]-b[0]); // Sort meetings by start day
    int count = 0; // Initialize count of free days
    int currentDay = 1; // Initialize the current day being checked

    for(int [] arr : meetings){
        count += Math.max(arr[0]-currentDay,0); // Add free days before the meeting
        currentDay = Math.max(arr[1]+1,currentDay); // Update currentDay to after the meeting
    }
    if(currentDay<=days)
        count += days-currentDay+1; // Add remaining free days at the end
        return count;
    }
}
```

## 📊 **ASCII Representation**

Not applicable for this problem.

## 📊 **WORKING**

Let's take an example: `days = 10`, `meetings = [[2, 4], [6, 8]]`

1. **Sort Meetings:** `[[2, 4], [6, 8]]` (already sorted)

2. **Initialization:** `count = 0`, `currentDay = 1`

3. **Iteration:**

   *   **Meeting 1: `[2, 4]`**
        *   `count += Math.max(2 - 1, 0) = Math.max(1, 0) = 1`  (Days before meeting: day 1 is free)
        *   `currentDay = Math.max(4 + 1, 1) = 5`
   *   **Meeting 2: `[6, 8]`**
        *   `count += Math.max(6 - 5, 0) = Math.max(1, 0) = 1`  (Days between meetings: day 5 is free)
        *   `currentDay = Math.max(8 + 1, 5) = 9`

4. **Remaining Days:**

   *   `currentDay = 9`, `days = 10`
   *   `count += 10 - 9 + 1 = 2` (Days after all meetings: days 9 and 10 are free)

5. **Final Count:** `count = 1 + 1 + 2 = 4`

Therefore, there are 4 days without meetings.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The dominant operation is sorting the `meetings` array. Therefore, the time complexity is **O(n log n)**, where n is the number of meetings.
*   **Space Complexity:** The space complexity is **O(1)**, as we are only using a few extra variables. The sorting happens in place, so it doesn't use extra space.
    