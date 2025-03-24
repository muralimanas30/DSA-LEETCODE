# 03430 - Count Days Without Meetings
    
**Language:** Java  
**Runtime:** 39 ms (Beats 53.87% of users)  
**Memory:** 100.4 MB (Beats 55.56% of users)  

## 💡 **Problem Explanation**

The problem asks us to count the number of days where no meetings are scheduled. Given a total number of `days` and a 2D array `meetings` representing meeting schedules (where `meetings[i] = [startDayi, endDayi]`), we need to find the days with no meetings. Meeting days are considered busy and we want to count the free days.

**Example:**

*   **Input:** `days = 10`, `meetings = [[2,5],[8,8]]`
*   **Output:** `5`

**Explanation:**

Meetings are scheduled from days 2 to 5 and on day 8. So, days 1, 6, 7, 9, and 10 are free.

## 📊 **Algorithm**

*   **Sort Meetings:** Sort the `meetings` array based on the start day to process meetings in chronological order.
*   **Iterate Through Meetings:**
    *   Keep track of `currentDay` which indicates the earliest day that is free.
    *   For each meeting, calculate the free days before the meeting by `max(arr[0] - currentDay, 0)`.
    *   Update the `currentDay` to be the day after the meeting ends using `max(arr[1] + 1, currentDay)`.
*   **Calculate Remaining Free Days:** After processing all meetings, calculate any remaining free days from `currentDay` to `days`.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        // Sort the meetings based on the start day
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int count = 0; // Initialize the count of free days
        int currentDay = 1; // Initialize the current day

        // Iterate through each meeting
        for (int[] arr : meetings) {
            // Add the free days before the current meeting
            count += Math.max(arr[0] - currentDay, 0);
            // Update the current day to the day after the meeting ends
            currentDay = Math.max(arr[1] + 1, currentDay);
        }

        // Add the remaining free days after all meetings
        if (currentDay <= days)
            count += days - currentDay + 1;

        return count; // Return the total count of free days
    }
}
```

## 📊 **WORKING**

Let's consider an example: `days = 10`, `meetings = [[2, 5], [1, 3], [8, 8]]`

1.  **Sort Meetings:** `meetings` becomes `[[1, 3], [2, 5], [8, 8]]`
2.  **Initialization:** `count = 0`, `currentDay = 1`
3.  **First Meeting `[1, 3]`:**
    *   Free days before: `max(1 - 1, 0) = 0`. `count` remains `0`.
    *   `currentDay = max(3 + 1, 1) = 4`
4.  **Second Meeting `[2, 5]`:**
    *   Free days before: `max(2 - 4, 0) = 0`. `count` remains `0`.
    *   `currentDay = max(5 + 1, 4) = 6`
5.  **Third Meeting `[8, 8]`:**
    *   Free days before: `max(8 - 6, 0) = 2`. `count = 0 + 2 = 2`.
    *   `currentDay = max(8 + 1, 6) = 9`
6.  **Remaining Free Days:**
    *   `currentDay = 9`, `days = 10`. So `10 - 9 + 1 = 2`. `count = 2 + 2 = 4`.

Therefore, the total free days are 4.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(N log N)** due to sorting the meetings, where N is the number of meetings.  The rest of the operations are linear, O(N).
*   **Space Complexity:** **O(1)** as it uses a constant amount of extra space. The sorting is done in-place (depending on the sorting algorithm used by the language).
    