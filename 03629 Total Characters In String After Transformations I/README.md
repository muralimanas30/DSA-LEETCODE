# 03629 - Total Characters In String After Transformations I
    
**Language:** Java  
**Runtime:** 151 ms (Beats 48.10% of users)  
**Memory:** 45.7 MB (Beats 53.16% of users)  

## 💡 **Problem Explanation**

The problem asks us to perform a series of transformations on a given string `s` for `t` times. Initially, each character in the string is represented by its corresponding position in the English alphabet (a=0, b=1, ..., z=25). In each transformation, we replace each character with the next character in the alphabet. If a character is 'z', it transforms into 'a' and 'b'. The goal is to determine the final length of the string after `t` transformations, modulo 1000000007. Since the number of transformations affect each character we count how many characters are on each alphabet index.

**Example:**

-   **Input:** `s` = "abc", `t` = 1
-   **Output:** 3

**Explanation:**

1.  Initial string: "abc"
2.  After 1 transformation: "bcd"
3.  Length of the string after transformation: 3

-   **Input:** `s` = "z", `t` = 1
-   **Output:** 2

**Explanation:**

1.  Initial string: "z"
2.  After 1 transformation: "ab"
3.  Length of the string after transformation: 2

## 📊 **Algorithm**

*   Initialize `arr` and `forNext` arrays of size 26 to store counts of each character at each step.
*   Populate `forNext` with the initial counts of characters in string `s`.
*   Iterate `t` times, performing the transformation in each iteration.
*   In each iteration:
    *   Copy `forNext` to `arr` to store the counts from the previous transformation.
    *   Reset `forNext` to zero.
    *   Iterate through the `arr`:
        *   If the count at index `i` is greater than 0, perform the character transformation.
        *   If `i` is 25 (i.e., 'z'), transform into 'a' and 'b' by adding the count to indices 0 and 1 in `forNext`.
        *   Otherwise, transform into the next character by adding the count to index `i+1` in `forNext`.
*   Calculate the final length of the string by summing all elements in `forNext` modulo 1000000007.
*   Return the final length.

## 🔥 **Code Implementation**

```java
class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int[] arr = new int[26];
        int[] forNext = new int[26];
        for (char x : s.toCharArray()) forNext[x - 'a']++;
        int mod = 1000000007;
        while (t-- > 0) {
            arr = forNext;
            forNext = new int[26];
            for (int i = 0; i < 26; i++) {
                int count = arr[i];
                if (count > 0) {
                    if (i == 25) {
                        forNext[0] = (forNext[0] + count) % mod;
                        forNext[1] = (forNext[1] + count) % mod;
                    } else {
                        forNext[i + 1] = (forNext[i + 1] + count) % mod;
                    }
                }
            }
        }
        int len = 0;
        for (int x : forNext) len = (len + x) % mod;
        return len;
    }
}
```

## 📊 **ASCII Representation**

Not applicable for this problem.

## 📊 **WORKING**

Let's consider the example input `s` = "abc", `t` = 1.

1. **Initial state:**

`s = "abc"`

`forNext = [1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]` (counts of a, b, c are 1)

2. **Transformation (t = 1):**

*   `arr = forNext` (arr now also contains the counts of a, b, c)
*   `forNext` is reset to all zeros.
*   Loop through `arr`:
    *   `i = 0` ('a'): `forNext[1] = (forNext[1] + 1) % mod`  (`forNext[1]` becomes 1)
    *   `i = 1` ('b'): `forNext[2] = (forNext[2] + 1) % mod`  (`forNext[2]` becomes 1)
    *   `i = 2` ('c'): `forNext[3] = (forNext[3] + 1) % mod`  (`forNext[3]` becomes 1)

`forNext = [0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`

3.  **Final length calculation:**

`len = (0 + 1 + 1 + 1 + 0 + ... + 0) % mod = 3`

Therefore, the final answer is 3.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(t * 26)**, where `t` is the number of transformations. We iterate through all 26 characters in the alphabet for each transformation.
*   **Space Complexity:** **O(1)**, as we use two arrays `arr` and `forNext` of fixed size 26, regardless of the input string length.
    