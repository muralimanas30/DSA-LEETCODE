# 00959 - 3sum With Multiplicity
    
**Language:** Java  
**Runtime:** 35 ms (Beats 47.34% of users)  
**Memory:** 43.9 MB (Beats 92.60% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 923 | 3Sum With Multiplicity | [LeetCode Problem](https://leetcode.com/problems/3sum-with-multiplicity/) |

---

## 💡 **Problem Explanation**

Given an integer array `arr` and an integer `target`, you need to find the number of triplets `(i, j, k)` such that `i < j < k` and `arr[i] + arr[j] + arr[k] == target`. Since the answer may be very large, return it modulo 10<sup>9</sup> + 7.

**Example:**

```
Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
The triplets are below:
(1, 2, 5) occurs 8 times
(1, 3, 4) occurs 8 times
(2, 2, 4) occurs 2 times
(2, 3, 3) occurs 2 times
```

---

## 📊 **Algorithm**
* Sort the input array `arr`.
* Iterate through the array using a single loop with index `i` from `0` to `n-2`.
* For each `i`, use two pointers `l` and `r` to find the remaining two elements such that `arr[i] + arr[l] + arr[r] == target`.
* If `arr[l] + arr[r] < sum`, increment `l`.
* If `arr[l] + arr[r] > sum`, decrement `r`.
* If `arr[l] + arr[r] == sum`, there are two cases:
    * If `arr[l] == arr[r]`, then the number of combinations is `(r - l + 1) * (r - l) / 2`.
    * If `arr[l] != arr[r]`, count the number of occurrences of `arr[l]` and `arr[r]` and multiply them.
* Add the number of combinations to the result and take modulo `10^9 + 7`.

## 🔥 **Code Implementation**

```java
class Solution {
    public int threeSumMulti(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        long ans = 0;

        for (int i = 0; i < n - 2; i++) {
            int sum = target - arr[i];
            int l = i + 1, r = n - 1;

            while (l < r) {
                if (arr[l] + arr[r] < sum) l++;
                else if (arr[l] + arr[r] > sum) r--;
                else {
                    int count1 = 1, count2 = 1;

                    if (arr[l] == arr[r]) {
                        int len = r - l + 1;
                        ans += (len * (len - 1) / 2);
                        break;
                    }

                    while (l < r && arr[l] == arr[l + 1]) {
                        count1++;
                        l++;
                    }

                    while (r > l && arr[r] == arr[r - 1]) {
                        count2++;
                        r--;
                    }

                    ans += (count1 * count2);
                    l++; r--;
                }
            }
        }

        return (int) (ans % 1000000007);
    }
}
```

## 📊 **WORKING**
Let's take the example `arr = [1,1,2,2,3,3,4,4,5,5], target = 8`.

1.  **Sort the array:** `arr = [1,1,2,2,3,3,4,4,5,5]`
2.  **Iterate through the array:**
    *   `i = 0`, `arr[i] = 1`, `sum = target - arr[i] = 8 - 1 = 7`
    *   `l = 1`, `r = 9`
    *   `arr[l] = 1`, `arr[r] = 5`, `arr[l] + arr[r] = 6 < 7`, so `l++`
    *   `arr[l] = 2`, `arr[r] = 5`, `arr[l] + arr[r] = 7 == 7`
        *   `arr[l] != arr[r]`
        *   Count occurrences of `arr[l] = 2`: 2 times
        *   Count occurrences of `arr[r] = 5`: 2 times
        *   `ans += 2 * 2 = 4`
        *   `l++`, `r--`
    *   `i = 1`, `arr[i] = 1`, `sum = target - arr[i] = 8 - 1 = 7`
        *   `l = 2`, `r = 8`
         *   `arr[l] = 2`, `arr[r] = 4`, `arr[l] + arr[r] = 6 < 7`, so `l++`
        *   `arr[l] = 2`, `arr[r] = 4`, `arr[l] + arr[r] = 6 < 7`, so `l++`
            ...
3. After traversing fully add the result to get `20`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The time complexity is **O(N<sup>2</sup>)** because we sort the array (O(N log N)) and then use two pointers in a nested loop (O(N<sup>2</sup>)).
*   **Space Complexity:** The space complexity is **O(1)** since we are not using any extra space that scales with the input size (excluding the space used for sorting, which can be O(log N) to O(N) depending on the sorting algorithm).
    