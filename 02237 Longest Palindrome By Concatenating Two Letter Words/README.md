# 02237 - Longest Palindrome By Concatenating Two Letter Words
    
**Language:** Java  
**Runtime:** 57 ms (Beats 70.12% of users)  
**Memory:** 59.2 MB (Beats 33.33% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2131 | Longest Palindrome by Concatenating Two Letter Words | [LeetCode Problem](https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/) |

---

## 💡 **Problem Explanation**

You are given an array of strings `words` where each element of `words` is a two-letter string. Your task is to find the length of the longest palindrome that can be built by concatenating elements from `words`. Each `words[i]` can be used at most once in the palindrome.

Let's consider the following examples:

**Example 1:**

```
Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lcggcl", of length 6.
"clgglc" is another longest palindrome that works.
```

**Example 2:**

```
Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "abclltybca", of length 8.
```

**Example 3:**

```
Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
"ll" is another longest palindrome that works, and so is "xx".
```

## 📊 **Algorithm**

Here’s the algorithm to solve this problem:

*   Initialize a HashMap to store the frequency of each word.
*   Iterate through the input array `words` and update the frequency of each word in the HashMap.
*   Initialize a counter variable `count` to 0, which will store the length of the longest palindrome.
*   Initialize a boolean variable `hasCentralWord` to false, which will be true if there is a word that is a palindrome itself and can be placed in the center of the final palindrome.
*   Iterate through the keys of the HashMap. For each word:
    *   If the frequency of the word is 0, skip this word.
    *   Reverse the word and check if the reversed word exists in the HashMap.
        *   If the word is equal to its reversed form (i.e., it's a palindrome):
            *   Add `(frequency / 2) * 4` to the `count`. This is because we can use pairs of this word in the palindrome.
            *   If the frequency is odd, set `hasCentralWord` to true. This indicates that one instance of this word can be used as the central word of the palindrome.
        *   If the reversed word exists and is different from the original word:
            *   Find the minimum frequency between the original word and its reversed form.
            *   Add `pairs * 4` to the `count`.
            *   Set the frequency of the reversed word to 0 to avoid double-counting.
    *   Set the frequency of the current word to 0 to avoid reprocessing.
*   If `hasCentralWord` is true, add 2 to the `count`. This is because we can add one palindrome word in the center, which adds 2 to the length.
*   Return the final `count`.

## 🔥 **Code Implementation**

```java
import java.util.*;

class Solution {
    public int longestPalindrome(String[] words) {
        // Map to store the frequency of each word
        Map<String, Integer> map = new HashMap<>();
        // Counter to store the length of the longest palindrome
        int count = 0;
        // Flag to check if there's a central word (like "gg" in "lcggcl")
        boolean hasCentralWord = false;

        // Populate the map with word frequencies
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        // Iterate through the map
        for (String word : map.keySet()) {
            int freq = map.get(word);
            if (freq == 0) continue; // Skip if already processed

            // Reverse the word
            String reversed = new StringBuilder(word).reverse().toString();
            // If the word is a palindrome itself (like "gg")
            if (word.equals(reversed)) {
                // Add pairs of the word to the count
                count += (freq / 2) * 4;
                // If the frequency is odd, mark that we have a potential central word
                if (freq % 2 == 1) hasCentralWord = true;
            } else if (map.containsKey(reversed)) {
                // If the reversed word exists
                int pairs = Math.min(freq, map.get(reversed));
                count += pairs * 4; // Add pairs of word and its reverse
                map.put(reversed, 0); // Mark the reversed word as used
            }
            map.put(word, 0); // Mark the current word as used
        }

        // If we have a central word, add it to the count
        if (hasCentralWord) count += 2;
        return count; // Return the length of the longest palindrome
    }
}
```

## 📊 **ASCII Representation**

Since this problem does not directly involve grids, trees, or movements, an ASCII representation is not applicable.

## 📊 **WORKING**

Let's trace the execution with the input `words = ["lc","cl","gg"]`.

1.  The `map` is populated: `{"lc": 1, "cl": 1, "gg": 1}`.
2.  `count = 0`, `hasCentralWord = false`.
3.  First word: `"lc"`.
    *   `reversed = "cl"`.
    *   `"cl"` exists in the map.
    *   `pairs = min(1, 1) = 1`.
    *   `count = 0 + (1 * 4) = 4`.
    *   `map` becomes: `{"lc": 0, "cl": 0, "gg": 1}`.
4.  Next word: `"cl"` (skipped as freq is 0).
5.  Next word: `"gg"`.
    *   `reversed = "gg"`.
    *   `"gg".equals("gg")` is true.
    *   `count = 4 + (1 / 2) * 4 = 4 + 0 = 4`.
    *   `freq % 2 == 1` is true, so `hasCentralWord = true`.
6.  `map` becomes `{"lc": 0, "cl": 0, "gg": 0}`.
7.  Finally, `hasCentralWord` is true, so `count = 4 + 2 = 6`.
8.  The function returns 6.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  The time complexity is **O(N)**, where N is the number of words in the input array. We iterate through the array once to build the HashMap and then iterate through the keys of the HashMap, which in the worst case could be N (if all words are unique).  The string reversal operation takes O(1) time as the strings are of fixed length 2.

*   **Space Complexity:** The space complexity is **O(N)**, where N is the number of words in the input array. In the worst case, the HashMap can store all the unique words in the input array.
    