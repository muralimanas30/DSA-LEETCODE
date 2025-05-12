# 01293 - Three Consecutive Odds
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 42.7 MB (Beats 23.19% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 1550 | THREE CONSECUTIVE ODDS | [LeetCode Problem](https://leetcode.com/problems/three-consecutive-odds/) |

---

## 💡 **Problem Explanation**

Given an integer array `arr`, return `true` if there are three consecutive odd numbers in the array. Otherwise, return `false`.

For example:

*   **Input:** `arr = [2,6,4,1]`
    **Output:** `false`
    **Explanation:** There are no three consecutive odds.

*   **Input:** `arr = [1,2,3,4,5,7,21]`
    **Output:** `true`
    **Explanation:** There are three consecutive odds, for example, `5, 7, 21`.

---

## 📊 **Algorithm**

*   Iterate through the array `arr` from the second element to the second to last element.
*   For each element `arr[i]`, check if it is odd.
*   If `arr[i]` is odd, check if the previous element `arr[i-1]` and the next element `arr[i+1]` are also odd.
*   If `arr[i-1]`, `arr[i]`, and `arr[i+1]` are all odd, return `true`.
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

This problem doesn't directly involve grids, trees, or movements that benefit from ASCII representation. It's more about a simple sequence check.

## 📊 **WORKING**

Let's trace the execution with the input `arr = [1, 2, 3, 4, 5, 7, 21]`:

1.  **i = 1:** `arr[1] = 2` (even). `i` becomes 2.
2.  **i = 2:** `arr[2] = 3` (odd).
3.  `arr[3] = 4` (even). `i` becomes 4.
4.  **i = 4:** `arr[4] = 5` (odd).
5.  `arr[5] = 7` (odd).
6.  `arr[3] = 4` which is odd, skipping.
7.  **i = 4:** `arr[4] = 5`, `arr[3] = 4`, `arr[5] = 7`, thus skip to next `i`.
8.  **i = 5:** `arr[5] = 7` (odd).
9.  `arr[6] = 21` (odd).
10. `arr[4] = 5` (odd).
11. The condition `arr[i-1]`, `arr[i]`, `arr[i+1]` are odd is met with `i = 5`, meaning 5, 7, 21 are all odd consecutively. The function returns `true`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The algorithm iterates through the array once. Thus, the time complexity is **O(n)**, where n is the length of the array.
*   **Space Complexity:** The algorithm uses a constant amount of extra space.  Thus, the space complexity is **O(1)**.
    