# 01293 - Three Consecutive Odds
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 42.7 MB (Beats 23.19% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 1550 | Three Consecutive Odds | [LeetCode Problem](https://leetcode.com/problems/three-consecutive-odds/) |

---

## 💡 **Problem Explanation**

Given an integer array `arr`, return `true` if there are three consecutive odd numbers in the array. Otherwise, return `false`.

For example:
*   **Input:** `arr = [2,6,4,1]`
*   **Output:** `false`
    _Explanation: There are no three consecutive odds._

*   **Input:** `arr = [1,2,3,4,5,7,21]`
*   **Output:** `true`
    _Explanation: There are three consecutive odds: 5, 7, and 21._

---

## 📊 **Algorithm**

*   Iterate through the array from the second element to the second-to-last element.
*   For each element, check if it and its adjacent elements are odd.
*   If three consecutive odd numbers are found, return `true`.
*   If the loop completes without finding three consecutive odd numbers, return `false`.

## 🔥 **Code Implementation**

```java
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
```

## 📊 **ASCII Representation**

This problem doesn't directly lend itself to ASCII diagrams in a particularly useful way. However, conceptually, we're scanning the array like this:

```
[ a, b, c, d, e ]
   ^     Check if a, b, and c are all odd
      ^  Check if b, c, and d are all odd
         ...and so on
```

## 📊 **WORKING**

Let's trace the execution with the input `arr = [1, 2, 3, 4, 5, 7, 21]`:

1.  `i = 1`, `arr[1] = 2`. `(arr[i] & 1) == 0` is true, so `i` becomes `2`.
2.  `i = 2`, `arr[2] = 3`. `(arr[i] & 1) == 0` is false.
3.  `arr[i+1] = arr[3] = 4`. `(arr[i+1] & 1) == 0` is true, so `i` becomes `4`.
4.  `i = 4`, `arr[4] = 5`. `(arr[i] & 1) == 0` is false.
5.  `arr[i+1] = arr[5] = 7`. `(arr[i+1] & 1) == 0` is false.
6.  `arr[i-1] = arr[3] = 4`. `(arr[i-1] & 1) == 0` is true.
7.  Now, we need to check if all three elements from arr[i-1] to arr[i+1] are odd to check if we have our answer. Since we know that `arr[i]` and `arr[i+1]` are odd and `arr[i-1]` is even, the conditions for all of them being odd are not met.
8.  Thus, loop finishes with `i = 4` returning `true`.

Let's trace the execution with the input `arr = [1, 3, 5]`:

1.  `i = 1`, `arr[1] = 3`. `(arr[i] & 1) == 0` is false.
2.  `arr[i+1] = arr[2] = 5`. `(arr[i+1] & 1) == 0` is false.
3.  `arr[i-1] = arr[0] = 1`. `(arr[i-1] & 1) == 0` is false.
4.  We return `true` since `arr[i-1], arr[i], arr[i+1]` are all odd numbers.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where n is the length of the array, as we iterate through the array once.
*   **Space Complexity:** **O(1)**, as we use only constant extra space.
    