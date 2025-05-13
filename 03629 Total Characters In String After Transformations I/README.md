# 03629 - Total Characters In String After Transformations I
    
**Language:** Java  
**Runtime:** 151 ms (Beats 48.10% of users)  
**Memory:** 45.7 MB (Beats 53.16% of users)  

## 💡 **Problem Explanation**

The problem asks us to simulate a series of transformations on a given string `s` for `t` iterations. Each character in the string represents a number from 0 to 25 ('a' to 'z'). In each transformation, every character is replaced based on the following rule:

- If a character is 'z' (25), it transforms into 'a' (0) and 'b' (1).
- Otherwise, a character transforms into the next character in the alphabet (e.g., 'a' becomes 'b', 'b' becomes 'c', and so on).

The goal is to find the total length of the string after `t` transformations.  Since the length may be very large, we need to return the length modulo 1000000007.

**Example:**

Let's say `s = "abc"` and `t = 1`.

1.  'a' becomes 'b'
2.  'b' becomes 'c'
3.  'c' becomes 'd'

So, the resulting string is `"bcd"` and the length is 3.

Let's say `s = "z"` and `t = 1`.

1. 'z' becomes 'a' and 'b'
so, the resulting string is "ab" and the length is 2.

## 📊 **Algorithm**

1.  **Initialize Frequency Arrays:** Create two arrays, `arr` and `forNext`, of size 26 to store the frequency of each character ('a' to 'z').  Initially, `forNext` will store the frequencies of characters in the original string `s`.
2.  **Iterate through Transformations:** Loop `t` times to simulate the transformations.
3.  **Update Frequencies:** In each iteration:
    *   Copy `forNext` to `arr` to keep track of the previous frequencies.
    *   Reset `forNext` to a new array of zeros.
    *   Iterate through the `arr` frequency array. For each character:
        *   If the frequency of a character is greater than 0:
            *   If the character is 'z' (index 25), update the frequencies of 'a' (index 0) and 'b' (index 1) in `forNext`.
            *   Otherwise, increment the frequency of the next character (index + 1) in `forNext`.
4.  **Calculate Total Length:** After all transformations, iterate through the `forNext` array and sum up the frequencies of all characters.  Take the modulo 1000000007 to prevent overflow.
5.  **Return Length:** Return the total length after the transformations.

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

This problem doesn't directly involve grids or trees, so an ASCII representation isn't applicable. However, you can imagine the characters 'a' to 'z' as nodes in a graph where 'z' points to 'a' and 'b', and every other character points to its successor.

## 📊 **WORKING**

Let's trace the execution with `s = "az"` and `t = 1`.

Initial state: `forNext = [1, 0, 0, ..., 0, 1]` (one 'a' and one 'z')

**Iteration 1:**

1. `arr` becomes `[1, 0, 0, ..., 0, 1]`
2. `forNext` is reset to `[0, 0, ..., 0]`
3. Iterate through `arr`:
    *   `i = 0` ('a'): `count = 1`. `forNext[1]` becomes `(0 + 1) % 1000000007 = 1`. `forNext` is now `[0, 1, 0, ..., 0]`
    *   `i = 25` ('z'): `count = 1`. `forNext[0]` becomes `(0 + 1) % 1000000007 = 1`. `forNext[1]` becomes `(1 + 1) % 1000000007 = 2`. `forNext` is now `[1, 2, 0, ..., 0]`

Final state: `forNext = [1, 2, 0, ..., 0]` (one 'a' and two 'b's)

Total length = `(1 + 2) % 1000000007 = 3`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(t \* 26), where `t` is the number of transformations.  The outer loop runs `t` times, and the inner loop iterates through the 26 characters of the alphabet.
*   **Space Complexity:** O(1). We use two arrays, `arr` and `forNext`, both of size 26, which is constant space regardless of the input size. Therefore, the space complexity is **O(1)**.
    