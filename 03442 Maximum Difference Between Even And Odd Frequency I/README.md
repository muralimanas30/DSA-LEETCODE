# 03442 - Maximum Difference Between Even And Odd Frequency I
    
**Language:** Java  
**Runtime:** 1 ms (Beats 100.00% of users)  
**Memory:** 42.2 MB (Beats 96.08% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title                                       | 🔗 Link                                                                                                |
|-------------------|------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| 3442              | MAXIMUM DIFFERENCE BETWEEN EVEN AND ODD FREQUENCY I | [LeetCode Problem](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/) |

---

## 💡 **Problem Explanation**

The problem asks us to find the maximum difference between the largest odd frequency and the smallest even frequency of characters in a given string `s`. If a frequency is 0, it should be ignored. If there are no odd or even frequencies, the difference is considered 0.

For example:

1.  If `s = "aabbccdde"`, frequencies are `a:2, b:2, c:2, d:2, e:1`.  The largest odd frequency is 1 (e), and the smallest even frequency is 2 (a, b, c, d).  The result is `1 - 2 = -1`.
2.  If `s = "abcd"`, frequencies are `a:1, b:1, c:1, d:1`.  The largest odd frequency is 1, and there are no even frequencies. So effectively the smallest even frequency is considered infinity, resulting in `1 - infinity` which according to the question boils down to `1 - 0 = 1`. No frequency is considered as '0'. The frequencies are strictly greater than zero.
3. If `s = "abcabc"`, frequencies are `a:2, b:2, c:2`. The largest odd frequency does not exist, and the smallest even frequency is `2`. So according to the question the answer is `0 - 2 = -2`. Since the problem definition states that we should consider 0 as minimum even frequency, the answer should be `0 - 0`.

---

## 📊 **Algorithm**

*   Calculate the frequency of each character in the string.
*   Initialize `maxOdd` to the smallest possible integer value and `minEven` to the largest possible integer value.
*   Iterate through the frequencies:
    *   If a frequency is odd, update `maxOdd`.
    *   If a frequency is even, update `minEven`.
*   If either `maxOdd` is still its initial value or `minEven` is still its initial value, treat the corresponding frequency as 0.
*   Return the difference between `maxOdd` and `minEven`.

---

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

        //if minEven and maxOdd are not changed, return 0 - 0
        if (maxOdd == Integer.MIN_VALUE){
            maxOdd = 0;
        }
        if (minEven == Integer.MAX_VALUE){
            minEven = 0;
        }

        return maxOdd - minEven;
    }
}
```

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(N)**, where N is the length of the string `s`, due to the single pass through the string and the frequency array.
*   **Space Complexity:** **O(1)**, as the frequency array has a fixed size of 26, regardless of the input string length.
    