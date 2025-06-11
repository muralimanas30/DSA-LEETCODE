# 03442 - Maximum Difference Between Even And Odd Frequency I
    
**Language:** Java  
**Runtime:** 1 ms (Beats 100.00% of users)  
**Memory:** 42.2 MB (Beats 96.08% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 3442 | MAXIMUM DIFFERENCE BETWEEN EVEN AND ODD FREQUENCY I | [LeetCode Problem](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/) |

---

## 💡 **Problem Explanation**

Given a string `s` consisting of lowercase English letters, the task is to find the maximum difference between the highest odd frequency and the lowest even frequency of characters in `s`. If there are no odd or even frequencies, return `-1`.

**Example 1:**

Input: `s = "aabbccdde"`
Output: `1`
Explanation:
- The odd frequencies are: `a` (2), `b` (2), `c` (2), `d` (2), `e` (1)
- Max odd frequency = `1`
- Even frequencies are: `2`
- Min even frequency = `2`
- Result is `1 - 2 = -1`. Since result can't be negative, the answer will be -1

**Example 2:**

Input: `s = "aabbccddd"`
Output: `1`
Explanation:
- The odd frequencies are: `d` (3)
- Max odd frequency = `3`
- Even frequencies are: `a` (2), `b` (2), `c` (2)
- Min even frequency = `2`
- Result is `3 - 2 = 1`

---

## 📊 **Algorithm**

*   Initialize a frequency array to store the count of each character in the string.
*   Iterate through the string and update the frequency of each character.
*   Initialize `maxOdd` to the smallest possible integer and `minEven` to the largest possible integer.
*   Iterate through the frequency array.
    *   If the frequency is greater than 0, check if it is even or odd.
    *   If even, update `minEven` if the frequency is smaller.
    *   If odd, update `maxOdd` if the frequency is larger.
*   If either `maxOdd` is still the smallest possible integer or `minEven` is still the largest possible integer, return `-1` (meaning no odd or even frequencies were found).
*   Otherwise, return the difference `maxOdd - minEven`.

## 🔥 **Code Implementation**

```java
class Solution {
    public static int maxDifference(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int maxOdd = Integer.MIN_VALUE, minEven = Integer.MAX_VALUE;
        for (int f : freq) {
            if (f > 0) {
                if (f % 2 == 0) minEven = Math.min(minEven, f);
                else maxOdd = Math.max(maxOdd, f);
            }
        }
        
        if(maxOdd == Integer.MIN_VALUE || minEven == Integer.MAX_VALUE)
            return -1;
            
        return maxOdd - minEven;
    }
}
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where n is the length of the string, as we iterate through the string once to count frequencies and then iterate through the frequency array (which is constant size 26).
*   **Space Complexity:** **O(1)**, as we use a fixed-size frequency array of size 26, which is independent of the input string size.
    