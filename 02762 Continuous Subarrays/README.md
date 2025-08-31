# 02762 - Continuous Subarrays
    
**Language:** Java  
**Runtime:** 46 ms (Beats 21.38% of users)  
**Memory:** 59.7 MB (Beats 31.53% of users)  

## 📝 LeetCode Problem
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2762 | CONTINUOUS SUBARRAYS | [LeetCode Problem](https://leetcode.com/problems/continuous-subarrays/) |

---

## 🧩 Problem Statement
Given an integer array `nums`, we define a subarray `nums[i...j]` as "continuous" if the absolute difference between its maximum and minimum elements is at most 2. The goal is to return the total number of continuous subarrays in `nums`.

### Constraints
*   `1 <= nums.length <= 10^5`
*   `1 <= nums[i] <= 10^9`

## 💡 Problem Explanation
The problem asks us to count all possible contiguous subarrays of a given array `nums` that satisfy a specific condition: the difference between the maximum and minimum values within that subarray must be less than or equal to 2. This type of problem often lends itself to a sliding window approach, where we maintain a window `[left, right]` and efficiently check its validity while expanding and shrinking. The main challenge is to quickly find the minimum and maximum elements within the current window and update them as elements are added or removed.

## 🧪 Examples

### Example 1
```
Input: nums = [5,4,3,4,5]
Output: 15
```
**Explanation:**
The continuous subarrays where `max - min <= 2` are:
*   **Length 1:** `[5]`, `[4]`, `[3]`, `[4]`, `[5]` (5 subarrays)
*   **Length 2:** `[5,4]` (max=5, min=4, diff=1), `[4,3]` (max=4, min=3, diff=1), `[3,4]` (max=4, min=3, diff=1), `[4,5]` (max=5, min=4, diff=1) (4 subarrays)
*   **Length 3:** `[5,4,3]` (max=5, min=3, diff=2), `[4,3,4]` (max=4, min=3, diff=1), `[3,4,5]` (max=5, min=3, diff=2) (3 subarrays)
*   **Length 4:** `[5,4,3,4]` (max=5, min=3, diff=2), `[4,3,4,5]` (max=5, min=3, diff=2) (2 subarrays)
*   **Length 5:** `[5,4,3,4,5]` (max=5, min=3, diff=2) (1 subarray)
Total = 5 + 4 + 3 + 2 + 1 = 15.

### Example 2
```
Input: nums = [1,2,3]
Output: 6
```
**Explanation:**
The continuous subarrays are: `[1]`, `[2]`, `[3]`, `[1,2]`, `[2,3]`, `[1,2,3]`. All satisfy the `max - min <= 2` condition.
Total = 6.

---

## 🧭 Algorithm Overview

This problem is efficiently solved using a **Sliding Window** approach combined with a **`TreeMap`** to maintain the elements within the current window `[left, right]` and quickly query their minimum and maximum values.

*   **Sliding Window:** We use two pointers, `left` and `right`, to define the current subarray. The `right` pointer expands the window, and the `left` pointer contracts it when the condition is violated.
*   **`TreeMap` for Min/Max Tracking:** A `TreeMap<Integer, Integer>` is used to store the frequency of each number within the current window. Because `TreeMap` maintains its keys in sorted order, `map.firstEntry().getKey()` provides the minimum element and `map.lastEntry().getKey()` provides the maximum element in `O(log K)` time (where `K` is the number of distinct elements in the window).
*   **Window Expansion:** As the `right` pointer moves, `nums[right]` is added to the `TreeMap`.
*   **Window Contraction:** After adding `nums[right]`, we check if the `max - min > 2` condition holds for the current window. If it does, we repeatedly remove `nums[left]` from the `TreeMap` and increment `left` until the condition `max - min <= 2` is restored.
*   **Counting Subarrays:**
    *   When the window `[left, right]` becomes invalid (i.e., `max - min > 2`), the `while` loop for shrinking the window is triggered. Inside this loop, `res += (right - left)` is executed. This line counts all valid subarrays that *end at `right-1`* and *start at or after `left`* before `nums[left]` is removed.
    *   After the main `for` loop finishes, there will be a final valid window `[left...nums.length-1]`. All subarrays within this final window are also continuous. The count for these is calculated using the formula `N * (N + 1) / 2`, where `N` is the length of this last valid window.

This approach is chosen because it efficiently handles dynamic min/max queries in a sliding window, which is crucial for the problem's constraints. Alternatives like two Deques (for min/max) are also possible but might be slightly more complex to manage element counts if duplicates exist and need to be removed precisely.

---

## 🧱 Variables & Data Structures

| Variable/Structure | Type                          | Description                                                                                             |
| :----------------- | :---------------------------- | :------------------------------------------------------------------------------------------------------ |
| `nums`             | `int[]`                       | The input array of integers.                                                                            |
| `map`              | `TreeMap<Integer, Integer>`   | Stores the frequency of elements currently within the sliding window `[left, right]`. Keys are elements, values are their counts. Provides `O(log K)` access to min/max. |
| `res`              | `long`                        | Accumulates the total count of continuous subarrays. Uses `long` to prevent overflow.                   |
| `left`             | `int`                         | The left pointer of the sliding window, indicating the start of the current valid window.                 |
| `right`            | `int`                         | The right pointer of the sliding window, expanding the window to include `nums[right]`.                 |
| `lastValid`        | `int`                         | Stores the sum of frequencies in `map` after the main loop, representing the length of the final valid window. |

---

## 🧰 Programming Workflow

1.  **Initialize**: Set `res` (total count) to 0, and `left` (left window pointer) to 0. Create an empty `TreeMap<Integer, Integer>` named `map` to store element frequencies within the window.
2.  **Initial Window**: Add the first element `nums[0]` to the `map` with a count of 1.
3.  **Iterate `right` pointer**: Loop `right` from `1` to `nums.length - 1`.
4.  **Check `nums[right]` for quick validity**: Before adding `nums[right]`, check if it fits within the existing `map`'s `[min, max]` range such that `abs(min - nums[right]) <= 2` AND `abs(max - nums[right]) <= 2`. If true, add `nums[right]` to `map` and `continue` to the next `right` element (no shrinking needed yet).
5.  **Add `nums[right]`**: If the quick check fails, add `nums[right]` to `map` (increment its frequency, or add it with count 1 if new).
6.  **Shrink Window**: Enter a `while` loop that continues as long as `(map.lastEntry().getKey() - map.firstEntry().getKey()) > 2`. This means the window `[left...right]` is currently invalid.
    *   Inside the `while` loop, add `(right - left)` to `res`. This counts all valid subarrays that ended at `right-1` and started at or after `left` *before* `nums[left]` is removed.
    *   Decrement the frequency of `nums[left]` in `map`. If its count becomes 0, remove the entry from `map`.
    *   Increment `left` to shrink the window.
7.  **Final Count**: After the `for` loop completes, the window `[left...nums.length-1]` is guaranteed to be valid. Calculate `lastValid` as the sum of all frequencies in `map` (which represents the length of this final valid window).
8.  **Add Remaining Subarrays**: Add `(1L * lastValid * (lastValid + 1L)) / 2` to `res`. This formula counts all subarrays within a valid window of length `lastValid`.
9.  **Return `res`**.

```
+-------------------+
|       Start       |
+-------------------+
         |
         v
+------------------------+
| Initialize map, res=0, |
|      left=0            |
+------------------------+
         |
         v
+-------------------------+
| Add nums[0] to map      |
+-------------------------+
         |
         v
+--------------------------------+
| For right from 1 to nums.length-1 |
+--------------------------------+
         |
         v
+--------------------------------+
|  Is |map.first - nums[right]| <= 2 |
| AND |map.last - nums[right]| <= 2? |
+--------------------------------+
         | Yes
         v
+------------------------+
| Add nums[right] to map |
+------------------------+
         |
         v
+------------------------+
|      Continue loop     |
|    for next right      |
+------------------------+
         | No (from condition above)
         v
+------------------------+
| Add nums[right] to map |
+------------------------+
         |
         v
+--------------------------------+
| While map.last - map.first > 2 |
+--------------------------------+
         | Yes
         v
+------------------------+
|  res += (right - left) |
+------------------------+
         |
         v
+--------------------------------+
| Decrement count of nums[left]  |
| in map; remove if count is 0   |
+--------------------------------+
         |
         v
+------------------------+
|         left++         |
+------------------------+
         |
         v
+--------------------------------+
| While map.last - map.first > 2 |
+--------------------------------+
         | No
         v
+--------------------+
| Continue loop for  |
|     next right     |
+--------------------+
         |
         v (Loop ends)
+----------------------------+
| Calculate lastValid =      |
| sum of values in map       |
+----------------------------+
         |
         v
+------------------------------------+
| res += lastValid * (lastValid + 1) / 2 |
+------------------------------------+
         |
         v
+--------------------+
|     Return res     |
+--------------------+
         |
         v
+--------------------+
|        End         |
+--------------------+
```

---

## 🔥 Code Implementation

```java
import java.util.TreeMap; // Required for TreeMap data structure
import java.util.stream.Collectors; // Required for Collectors in sum operation, though simple reduce works

class Solution {
    public long continuousSubarrays(int[] nums) {
        // TreeMap to store frequencies of elements within the current sliding window.
        // It keeps keys sorted, allowing O(log K) access to min (firstEntry) and max (lastEntry).
        TreeMap<Integer,Integer> map = new TreeMap<>();
        long res = 0; // Initialize total count of continuous subarrays, use long to prevent overflow
        int left = 0; // Left pointer of the sliding window

        // Initialize the map with the first element. The main loop starts 'right' from 1.
        // This ensures 'map' is never empty when 'right' loop starts.
        map.put(nums[left],1);

        // Iterate with the 'right' pointer to expand the window
        for(int right = 1; right < nums.length; right++){
            // Optimization: Check if adding nums[right] to the current window [left...right-1]
            // would keep the overall max-min difference within 2.
            // This is true if nums[right] is within 2 units of both the current min and max.
            if(Math.abs(map.firstEntry().getKey() - nums[right]) <= 2
                && Math.abs(map.lastEntry().getKey() - nums[right]) <= 2){
                // If the condition holds, simply add nums[right] to the map and move on.
                // The window [left...right] is valid.
                map.put(nums[right],map.getOrDefault(nums[right],0)+1);
                continue; // Proceed to the next 'right' element
            }

            // If the above `if` condition fails, it means nums[right] will likely cause
            // the overall max-min difference to exceed 2 (or it already did).
            // Add nums[right] to the map first.
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);

            // Shrink the window from the left until the |max - min| <= 2 condition is met
            while(Math.abs(map.firstEntry().getKey() - map.lastEntry().getKey()) > 2){
                // When we are about to remove nums[left], the window [left...right-1] was valid.
                // All subarrays ending at (right-1) that start from 'left' up to (right-1) itself
                // are valid. There are (right - left) such subarrays.
                // This correctly adds contributions from windows that were valid before 'left' moved.
                res += (right - left);
                
                // Remove nums[left] from the window (decrement its count)
                map.put(nums[left],map.getOrDefault(nums[left],0)-1);
                // If count becomes zero, remove the entry completely from the map
                if(map.get(nums[left]) == 0)
                    map.remove(nums[left]);
                left++; // Move left pointer to shrink the window
            }
        }
        
        // After the main 'for' loop finishes, the remaining window [left...nums.length-1]
        // is guaranteed to be valid and contains no violations.
        // We need to count all continuous subarrays within this final valid window.
        // The length of this final window is 'lastValid'.
        int lastValid = map.values().stream().reduce(0, Integer::sum);
        
        // For any valid window of length N, there are N*(N+1)/2 continuous subarrays within it.
        // For example, if N=3 ([a,b,c]), subarrays are [a],[b],[c],[a,b],[b,c],[a,b,c] = 6 = 3*(3+1)/2.
        res += (1l * lastValid * (lastValid + 1l)) / 2;
        
        return res;
    }
}
```

---

## 🧪 Working Demo

Let's trace the algorithm with `nums = [1,2,5]`. The expected output is `4`.

*   **Initial State**:
    *   `left = 0`, `res = 0`
    *   `map = {}` (empty)

1.  **Initialize `map` with `nums[0]`**:
    *   `nums[left=0] = 1`.
    *   `map.put(1, 1)`.
    *   `map = {1:1}`
    *   Window: `[1]`

2.  **`for` loop: `right = 1` (`nums[1] = 2`)**
    *   Current `map = {1:1}`. `map.first=1, map.last=1`.
    *   Check `if`: `abs(1-2)=1 <= 2` AND `abs(1-2)=1 <= 2`. Condition is TRUE.
    *   Add `nums[1]=2`: `map.put(2,1)`.
    *   `map = {1:1, 2:1}`.
    *   `continue` to next `right`.
    *   Window: `[1, 2]` (valid: `max=2, min=1, diff=1 <= 2`)

3.  **`for` loop: `right = 2` (`nums[2] = 5`)**
    *   Current `map = {1:1, 2:1}`. `map.first=1, map.last=2`.
    *   Check `if`: `abs(map.first - nums[right]) <= 2` -> `abs(1-5)=4 <= 2` is FALSE. Skip `if` block.
    *   Add `nums[2]=5`: `map.put(5,1)`.
    *   `map = {1:1, 2:1, 5:1}`.
    *   Window: `[1, 2, 5]`
    *   **`while` loop (check `map.last - map.first > 2`)**:
        *   `map.first=1, map.last=5`. `abs(1-5)=4 > 2`. Condition is TRUE.
        *   `res += (right - left)`: `res += (2 - 0) = 2`.
            *   `res = 2`. (These 2 subarrays ending at `nums[1]` are `[2]` and `[1,2]`, which are valid.)
        *   Remove `nums[left=0]=1`: `map.put(1, 0)`, then `map.remove(1)`.
        *   `map = {2:1, 5:1}`.
        *   `left++`: `left = 1`.
        *   Window: `[2, 5]`
        *   Re-check `while` condition: `map.first=2, map.last=5`. `abs(2-5)=3 > 2`. Condition is TRUE.
        *   `res += (right - left)`: `res += (2 - 1) = 1`.
            *   `res = 2 + 1 = 3`. (This 1 subarray ending at `nums[1]` is `[2]`, which is valid.)
        *   Remove `nums[left=1]=2`: `map.put(2, 0)`, then `map.remove(2)`.
        *   `map = {5:1}`.
        *   `left++`: `left = 2`.
        *   Window: `[5]`
        *   Re-check `while` condition: `map.first=5, map.last=5`. `abs(5-5)=0 <= 2`. Condition is FALSE. Exit `while` loop.

4.  **End of `for` loop.**
    *   Current `map = {5:1}`. `left = 2`.
    *   `lastValid = map.values().stream().reduce(0, Integer::sum) = 1`.
    *   `res += (1L * lastValid * (lastValid + 1L)) / 2`: `res += (1L * 1 * (1 + 1L)) / 2 = 1`.
    *   `res = 3 + 1 = 4`.

*   **Final Result**: `res = 4`. This matches the expected output for `[1,2,5]`.

### 🧮 ASCII Representation of Window & Map State

Let's illustrate for `nums = [1,2,5]`:

```
Nums: [1, 2, 5]

Step 1: Initial (right=0, handled before loop)
Window: [1]
Pointers:  L
           R
Map: {1:1}
res = 0

Step 2: right = 1 (nums[1]=2)
Window: [1, 2]
Pointers:  L  R
Map: {1:1, 2:1}
res = 0

Step 3: right = 2 (nums[2]=5)
   Adding nums[2]=5, current map min=1, max=2.
   abs(1-5) = 4 > 2. So, we enter the shrinking phase.
Window: [1, 2, 5]
Pointers:  L     R
Map: {1:1, 2:1, 5:1}
res = 0

   Shrink 1: (map.first=1, map.last=5, diff=4 > 2)
   res += (right-left) = (2-0) = 2.
   res = 2 (counts subarrays [2], [1,2] ending at index 1)
   Remove nums[left=0]=1 from map. left becomes 1.
Window:    [2, 5]
Pointers:     L  R
Map: {2:1, 5:1}

   Shrink 2: (map.first=2, map.last=5, diff=3 > 2)
   res += (right-left) = (2-1) = 1.
   res = 2 + 1 = 3 (counts subarray [2] ending at index 1)
   Remove nums[left=1]=2 from map. left becomes 2.
Window:       [5]
Pointers:        L R
Map: {5:1}

   Shrink 3: (map.first=5, map.last=5, diff=0 <= 2)
   Condition false. Exit while loop.

Step 4: End of for loop.
Final window is [5] (nums[left=2...nums.length-1=2])
Map: {5:1}
lastValid = sum(map.values()) = 1
res += (1 * (1+1)) / 2 = 1.
res = 3 + 1 = 4.

Final Result: 4
```

---

## 🚀 Time & Space Complexity

*   **Time Complexity:** `O(N log K)`
    *   `N` is the length of the input array `nums`.
    *   `K` is the maximum number of distinct elements within the sliding window at any given time. In the worst case, `K` can be `N`.
    *   Each element `nums[right]` is added to the `TreeMap` once, taking `O(log K)` time.
    *   Each element `nums[left]` is removed from the `TreeMap` at most once, also taking `O(log K)` time.
    *   The `firstEntry()` and `lastEntry()` operations on `TreeMap` take `O(log K)` time.
    *   Therefore, the total time complexity is dominated by these `N` operations on the `TreeMap`, leading to `O(N log K)`. Since `K <= N`, the worst-case time complexity is `O(N log N)`.

*   **Space Complexity:** `O(K)`
    *   `K` is the maximum number of distinct elements stored in the `TreeMap` at any point.
    *   In the worst case, if all elements in a valid window are distinct, `K` can be up to `N`.
    *   Thus, the space complexity is `O(K)`, or `O(N)` in the worst case.

---

## 🔗 References

*   **Sliding Window Technique:** A common algorithmic paradigm for efficiently processing data in a window of a sequence or array.
    *   [LeetCode Tag: Sliding Window](https://leetcode.com/tag/sliding-window/)
    *   [GeeksforGeeks: Sliding Window Technique](https://www.geeksforgeeks.org/sliding-window-protocol/)
*   **Java `TreeMap`:** A Red-Black tree based NavigableMap implementation, providing sorted key-value pairs and logarithmic time complexity for insertions, deletions, and retrieval of min/max elements.
    *   [Oracle Java 8 Documentation: `TreeMap`](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)
*   **Two Pointers Algorithm:** The foundation of the sliding window, using two pointers to manage a subsegment of data.
    *   [GeeksforGeeks: Two Pointers Technique](https://www.geeksforgeeks.org/two-pointers-technique/)
    