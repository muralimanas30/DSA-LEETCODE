# 00038 - Count And Say
    
**Language:** Java  
**Runtime:** 5 ms (Beats 51.21% of users)  
**Memory:** 41.3 MB (Beats 79.02% of users)  

## 💡 **Problem Explanation**

The "Count and Say" sequence is a sequence of digit strings defined by the recursive formula:

*   `countAndSay(1) = "1"`
*   `countAndSay(n)` is the way you would "say" the digit string from `countAndSay(n-1)`, which is then converted into a different digit string.

To determine how you "say" a digit string, split it into the minimal number of groups so that each group is contiguous and has the same character. Then, for each group, say the number of characters, then say the character.

For example:

*   `countAndSay(1) = "1"`
*   `countAndSay(2) =` say "1" = one 1 = `"11"`
*   `countAndSay(3) =` say "11" = two 1's = `"21"`
*   `countAndSay(4) =` say "21" = one 2 + one 1 = `"1211"`

**Example 1:**

*   Input: `n = 1`
*   Output: `"1"`

**Example 2:**

*   Input: `n = 4`
*   Output: `"1211"`

## 📊 **Algorithm**

*   Initialize an ArrayList called `results` to store the sequence strings. The first two strings are pre-populated: `results.add("")` and `results.add("1")`.
*   The `generateUpTo(int n)` method generates the sequence strings up to the `n`-th term if they haven't been generated yet.
*   Inside `generateUpTo`, a while loop runs until the size of the `results` list is greater than `n`.
*   In each iteration, the previous string `prev` from `results` is retrieved.
*   The algorithm iterates through the characters of `prev`, counting consecutive occurrences of the same character.
*   When a different character is encountered, the count and the previous character are appended to the current StringBuilder `curr`.
*   Finally, the last count and character are appended to `curr`, and the resulting string is added to `results`.
*   The `countAndSay(int n)` method calls `generateUpTo(n)` to ensure the sequence is generated up to `n`, and then it returns the `n`-th string from `results`.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    private final List<String> results = new ArrayList<>();

    public Solution() {
        results.add(""); 
        results.add("1");
    }

    private void generateUpTo(int n) {
        while (results.size() <= n) {
            String prev = results.get(results.size() - 1);
            char[] chars = prev.toCharArray();
            StringBuilder curr = new StringBuilder();

            int count = 1;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == chars[i - 1]) {
                    count++;
                } else {
                    curr.append(count).append(chars[i - 1]);
                    count = 1;
                }
            }
            curr.append(count).append(chars[chars.length - 1]);
            results.add(curr.toString());
        }
    }

    public String countAndSay(int n) {
        generateUpTo(n);
        return results.get(n);
    }
}
```

## 📊 **ASCII Representation**

N/A (This problem is not grid or tree based)

## 📊 **WORKING**

Let's trace `countAndSay(4)`:

1.  `results` is initialized with `["", "1"]`.
2.  `generateUpTo(4)` is called.
3.  **Iteration 1:** (Generating `results[2]`)
    *   `prev = "1"`
    *   `curr` becomes `"11"` (one 1)
    *   `results` becomes `["", "1", "11"]`
4.  **Iteration 2:** (Generating `results[3]`)
    *   `prev = "11"`
    *   `curr` becomes `"21"` (two 1s)
    *   `results` becomes `["", "1", "11", "21"]`
5.  **Iteration 3:** (Generating `results[4]`)
    *   `prev = "21"`
    *   `curr` becomes `"1211"` (one 2, one 1)
    *   `results` becomes `["", "1", "11", "21", "1211"]`
6.  `generateUpTo(4)` completes.
7.  `countAndSay(4)` returns `"1211"`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The time complexity is approximately **O(N \* L)**, where N is the input `n` and L is the maximum length of the generated string. The nested loop within `generateUpTo` can iterate up to the length of the previous string, and this is repeated for each `n`.

*   **Space Complexity:** The space complexity is **O(L)**, where L is the length of the longest string generated. This is due to storing the sequence of strings in the `results` list and the StringBuilder `curr`. In the worst case, the length of the string can grow exponentially with `n`.
    