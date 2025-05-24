# 03194 - Find Words Containing Character
    
**Language:** Java  
**Runtime:** 1 ms (Beats 100.00% of users)  
**Memory:** 45.4 MB (Beats 7.51% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2942 | Find Words Containing Character | [LeetCode Problem](https://leetcode.com/problems/find-words-containing-character/) |

---

## 💡 **Problem Explanation**

The problem asks us to find the indices of the words in a given array of strings that contain a specific character. We are given an array of strings `words` and a character `letter`. The goal is to return a list of integers, where each integer represents the index of a word in the `words` array that contains the given `letter`.

**Example 1:**

Input: `words = ["leetcode","is","cool"], letter = "e"`
Output: `[0,2]`
Explanation: "leetcode" contains the character "e" at index 0, and "cool" contains the character "e" at index 2.

**Example 2:**

Input: `words = ["abc","bcd","aaaa","cbc"], letter = "b"`
Output: `[0,1,3]`
Explanation: "abc" contains the character "b" at index 0, "bcd" contains the character "b" at index 1, and "cbc" contains the character "b" at index 3.

**Example 3:**

Input: `words = ["a","b","c"], letter = "z"`
Output: `[]`
Explanation: None of the words contain the character "z".

## 📊 **Algorithm**

*   Initialize an empty list `al` to store the indices of the words containing the target letter.
*   Iterate through the `words` array using a `for` loop.
*   For each word, check if it contains the target `letter` using the `indexOf` method.
*   If the `indexOf` method returns a value other than -1, it means the letter is present in the word.
*   In this case, add the index `i` of the word to the `al` list.
*   After iterating through all the words, return the `al` list containing the indices of the words containing the letter.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findWordsContaining(String[] words, char letter) {
        List<Integer> al  = new ArrayList<>();
        for(int i=0;i<words.length;i++)
            if(words[i].indexOf(letter)!=-1)
                al.add(i);
        return al;
    }
}
```

## 🚀 **Time & Space Complexity**

The **time complexity** of the `findWordsContaining` function is **O(N*M)**, where N is the number of words in the `words` array and M is the average length of a word. This is because we iterate through each word in the array (O(N)), and for each word, the `indexOf` method can take up to O(M) time in the worst case.

The **space complexity** of the function is **O(K)**, where K is the number of words that contain the target letter. This is because we store the indices of the matching words in an `ArrayList`, which can grow up to the size of K in the worst case where every word contains the letter.
    