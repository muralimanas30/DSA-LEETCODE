# 00049 - Group Anagrams
    
**Language:** Java  
**Runtime:** 7 ms (Beats 66.57% of users)  
**Memory:** 47.6 MB (Beats 95.40% of users)  

## 💡 **Problem Explanation**

Given an array of strings `strs`, group the anagrams together. You can return the answer in **any order**.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**

```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Example 2:**

```
Input: strs = [""]
Output: [[""]]
```

**Example 3:**

```
Input: strs = ["a"]
Output: [["a"]]
```

## 📊 **Algorithm**

*   Initialize a HashMap to store the sorted string as a key and a list of anagrams as the value.
*   Iterate through the input string array.
*   For each string, sort it alphabetically.
*   Use the sorted string as the key in the HashMap.
*   If the sorted string (key) doesn't exist in the map, create a new list for it.
*   Add the original string to the list associated with its sorted key.
*   Finally, return a list of all values (which are lists of anagrams) from the HashMap.

## 🔥 **Code Implementation**

```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // HashMap to store sorted string as key and list of anagrams as value
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        // Iterate through the input string array
        for (String str : strs) {
            // Sort the string alphabetically
            String sortedStr = sortString(str);

            // If the sorted string is not in the map, create a new list
            map.putIfAbsent(sortedStr, new ArrayList<String>());

            // Add the original string to the list of anagrams for the sorted string
            map.get(sortedStr).add(str);
        }

        // Return a list of all the lists of anagrams
        return new ArrayList<>(map.values());
    }

    // Helper function to sort a string alphabetically
    public String sortString(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't particularly benefit from an ASCII representation since it deals with strings and their properties rather than spatial arrangements like grids or trees.

## 📊 **WORKING**

Let's trace the code with the input `strs = ["eat","tea","tan","ate","nat","bat"]`:

1.  **Initialization:** `map = {}`
2.  **Loop through `strs`:**
    *   `str = "eat"`: `sortedStr = "aet"`. `map = {"aet": ["eat"]}`
    *   `str = "tea"`: `sortedStr = "aet"`. `map = {"aet": ["eat", "tea"]}`
    *   `str = "tan"`: `sortedStr = "ant"`. `map = {"aet": ["eat", "tea"], "ant": ["tan"]}`
    *   `str = "ate"`: `sortedStr = "aet"`. `map = {"aet": ["eat", "tea", "ate"], "ant": ["tan"]}`
    *   `str = "nat"`: `sortedStr = "ant"`. `map = {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"]}`
    *   `str = "bat"`: `sortedStr = "abt"`. `map = {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"], "abt": ["bat"]}`
3.  **Return `map.values()` as a List:** `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]` (order may vary)

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(N * KlogK), where N is the number of strings in the input array, and K is the maximum length of a string.  We iterate through each string (O(N)) and sort each string (O(KlogK)).
*   **Space Complexity:** O(N * K), where N is the number of strings and K is the maximum length of any string in the input array. This is because, in the worst case, all strings are unique, and we store each character of each string in the hashmap.
    