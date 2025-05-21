# 00409 - Longest Palindrome
    
**Language:** Java  
**Runtime:** 1 ms (Beats 99.62% of users)  
**Memory:** 41.8 MB (Beats 70.05% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 409 | LONGEST PALINDROME | [LeetCode Problem](https://leetcode.com/problems/longest-palindrome/) |

---

## 💡 **Problem Explanation**

Given a string `s` consisting of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters. Letters are case sensitive, for example, `"Aa"` is not considered a palindrome here.

**Example 1:**

*   **Input:** `s = "abccccdd"`
*   **Output:** `7`
*   **Explanation:** One longest palindrome that can be built is `"dccaccd"`, whose length is `7`.

**Example 2:**

*   **Input:** `s = "a"`
*   **Output:** `1`
*   **Explanation:** The longest palindrome that can be built is `"a"`, whose length is `1`.

**Example 3:**

*   **Input:** `s = "bb"`
*   **Output:** `2`
<br>
<br>
## 💡 **Algorithm**

*   Create an array `arr` to store the frequency of each character in the string `s`. Since we are dealing with uppercase and lowercase English letters, the size of the array is 58 (from 'A' to 'z').
*   Iterate through the string `s` and update the frequency of each character in the `arr`.
*   Initialize `no` to 0, which will store the length of the longest palindrome.
*   Initialize `singleCounted` to `false`. This boolean will track if we've included a single character in the palindrome.
*   Iterate through the frequency array `arr`.
    *   If the frequency `f` is odd, add `f / 2 * 2` to `no`. If `singleCounted` is false, add 1 to `no` and set `singleCounted` to `true`.
    *   If the frequency `f` is even, add `f` to `no`.
*   Return `no`, which will be the length of the longest palindrome.

## 🔥 **Code Implementation**

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] arr = new int[58];
        for (char x : s.toCharArray())
            arr[x - 'A']++;
        int no = 0;
        boolean singleCounted = false;
        for (int f : arr) {
            if ((f & 1) == 1) {
                no += f / 2 * 2;
                if (!singleCounted) {
                    no += 1;
                    singleCounted = true;
                }
            } else {
                no += f;
            }
        }
        return no;
    }
}
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n), where n is the length of the string `s`. This is because we iterate through the string once to count character frequencies and then iterate through the frequency array.
*   **Space Complexity:** O(1), as the size of the frequency array `arr` is fixed at 58, regardless of the input string's length. Therefore it is constant space.
    