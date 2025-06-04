# 00645 - Set Mismatch
    
**Language:** Java  
**Runtime:** 3 ms (Beats 77.20% of users)  
**Memory:** 45.6 MB (Beats 72.98% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 645 | Set Mismatch | [LeetCode Problem](https://leetcode.com/problems/set-mismatch/) |

---

## 💡 **Problem Explanation**

The problem states that you are given an integer array `nums` representing a set that originally contained numbers from `1` to `n`. However, due to some error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.  Your task is to find the number that occurs twice and the number that is missing.

**Example:**

```
Input: nums = [1,2,2,4]
Output: [2,3]

Explanation:
Here, 2 is repeated and 3 is missing from the original set {1, 2, 3, 4}.
```

---

## 📊 **Algorithm**

Here's the algorithm we'll use to solve this problem:

*   Iterate through the `nums` array.
*   For each number `i` in `nums`, take its absolute value and use it as an index to access `nums`.  If `nums[abs(i) - 1]` is negative, it means we've encountered this number before, so it's the duplicate.
*   Otherwise, mark the number at `nums[abs(i) - 1]` as negative.
*   After the first loop, iterate through `nums` again.  If a number is positive, its index plus one is the missing number.

---

## 🔥 **Code Implementation**

```java
class Solution {

    public static int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) {
                res[0] = Math.abs(i);
            } else
                nums[Math.abs(i) - 1] *= -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }
}
```

---

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees, so an ASCII representation isn't necessary.

---

## 📊 **TABLE Representation**

This problem does not necessarily need a table but it helps understanding the solution's logic.

Consider the input `nums = [1,2,2,4]`:

| Index | nums (Initial) | Loop 1 Actions                           | nums (After Loop 1) |
|-------|----------------|-------------------------------------------|---------------------|
| 0     | 1              | nums[0] = -1                              | -1                   |
| 1     | 2              | nums[1] = -2                              | -2                   |
| 2     | 2              | nums[1] < 0, res[0] = 2                   | -2                   |
| 3     | 4              | nums[3] = -4                              | -4                   |

| Index | nums (After Loop 1) | Loop 2 Actions                |
|-------|-----------------------|--------------------------------|
| 0     | -1                    | nums\[0] < 0, skip             |
| 1     | -2                    | nums\[1] < 0, skip             |
| 2     | -2                    | nums\[2] < 0, skip             |
| 3     | -4                    | nums\[3] < 0, skip             |

The value 3 is missing and found by checking the sign during array traversal.

---

## 📊 **WORKING**

Let's trace the execution with the sample input `nums = [1, 2, 2, 4]`:

1.  **First Loop:**
    *   `i = 1`: `nums[0]` becomes `-1`. `nums` is now `[-1, 2, 2, 4]`
    *   `i = 2`: `nums[1]` becomes `-2`. `nums` is now `[-1, -2, 2, 4]`
    *   `i = 2`: `nums[1]` is already negative.  `res[0] = 2`.
    *   `i = 4`: `nums[3]` becomes `-4`. `nums` is now `[-1, -2, 2, -4]`
2.  **Second Loop:**
    *   `i = 0`: `nums[0] < 0`, skip
    *   `i = 1`: `nums[1] < 0`, skip
    *   `i = 2`: `nums[2] > 0`. `res[1] = 3`.
    *   `i = 3`: `nums[3] < 0`, skip
3.  **Result:**
    *   `res = [2, 3]`

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n), where n is the length of the input array `nums`. We iterate through the array twice.
*   **Space Complexity:** O(1). We use a constant amount of extra space (the `res` array).  The modification is done in-place.
    