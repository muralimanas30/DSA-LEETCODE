# 03430 - Count Days Without Meetings
    
**Language:** Java  
**Runtime:** 36 ms (Beats 89.90% of users)  
**Memory:** 101 MB (Beats 17.85% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3430 | Count Days Without Meetings | [LeetCode Problem](https://leetcode.com/problems/count-days-without-meetings/) |

---

## 💡 **Problem Explanation**

The problem asks us to determine the number of days in a given range (1 to `days`) where no meetings are scheduled. We are provided with a list of meetings, each specified by a start day and an end day. A day is considered free if it does not fall within any meeting's duration.

**Example:**

Let's say `days = 10` and `meetings = [[2, 5], [8, 8]]`.

This means there are 10 days in total.  Meetings occur from day 2 to day 5, and on day 8. Therefore, days 1, 6, 7, 9, and 10 are free.  The output should be 5.

---

## 📊 **Algorithm**

*   Sort the meetings by their start days. This allows us to process meetings in chronological order.
*   Initialize `count` to 0, which will store the number of free days.
*   Initialize `currentDay` to 1, representing the first day.
*   Iterate through the sorted meetings:
    *   Add the number of free days between `currentDay` and the meeting's start day to `count`. Use `Math.max(arr[0]-currentDay,0)` to ensure that if meetings overlap the count doesn't decrease.
    *   Update `currentDay` to the day after the current meeting ends. Use `Math.max(arr[1]+1,currentDay)` to ensure the current day remains updated even if meetings overlap.
*   After processing all meetings, add any remaining free days from `currentDay` to `days` to `count`.
*   Return the final `count`.

---

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        // Sort the meetings by start day
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int count = 0; // Initialize the count of free days
        int currentDay = 1; // Start from the first day

        // Iterate through the sorted meetings
        for (int[] meeting : meetings) {
            // Add the number of free days before the current meeting
            count += Math.max(meeting[0] - currentDay, 0);

            // Update the current day to the day after the current meeting ends
            currentDay = Math.max(meeting[1] + 1, currentDay);
        }

        // Add any remaining free days after the last meeting
        if (currentDay <= days) {
            count += days - currentDay + 1;
        }

        return count;
    }
}
```

---

## 📊 **ASCII Representation**

Consider `days = 10` and `meetings = [[2, 5], [8, 8]]`.

```
Days:   1  2  3  4  5  6  7  8  9  10
Meeting1:    XXXXX
Meeting2:                X
Free:   O

O - Free Day
X - Meeting Day
```

---

## 📊 **WORKING**

Let's trace the example `days = 10` and `meetings = [[2, 5], [8, 8]]`.

1.  **Sort Meetings:** The meetings are already sorted: `[[2, 5], [8, 8]]`.

2.  **Initialization:** `count = 0`, `currentDay = 1`.

3.  **First Meeting `[2, 5]`:**
    *   Free days before meeting: `Math.max(2 - 1, 0) = 1`.  `count = 1`.
    *   Update `currentDay`: `Math.max(5 + 1, 1) = 6`.

4.  **Second Meeting `[8, 8]`:**
    *   Free days before meeting: `Math.max(8 - 6, 0) = 2`.  `count = 1 + 2 = 3`.
    *   Update `currentDay`: `Math.max(8 + 1, 6) = 9`.

5.  **Remaining Days:**
    *   Free days after the last meeting: `10 - 9 + 1 = 2`.  `count = 3 + 2 = 5`.

6.  **Return:** The function returns `5`.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The dominant operation is sorting the meetings, which takes **O(N log N)** time, where N is the number of meetings.  The subsequent iteration through the meetings takes O(N) time. Therefore, the overall time complexity is **O(N log N)**.

*   **Space Complexity:**  The space complexity is **O(1)**, as we are only using a few integer variables. The `Arrays.sort()` method might use some extra space depending on the implementation, but generally, it can be considered **O(1)** space or **O(log N)** space in the worst case.
    