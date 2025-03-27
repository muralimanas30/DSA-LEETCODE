# 02888 - Minimum Index Of A Valid Split
    
**Language:** Java  
**Runtime:** 27 ms (Beats 63.46% of users)  
**Memory:** 57.7 MB (Beats 46.15% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2888 | MINIMUM INDEX OF A VALID SPLIT | [LeetCode Problem](https://leetcode.com/problems/minimum-index-of-a-valid-split/) |

---

## 💡 **Problem Explanation**

The problem asks us to find the smallest index `i` in a list of integers `nums` such that splitting the list at that index results in two sublists, each having a dominant element. A dominant element in a list is one that appears strictly more than half the time. If no such index exists, return -1.

For example:

*   `nums = [1,2,2,2]`
    *   Splitting at index 1 gives `[1]` and `[2,2,2]`. The dominant element in `[2,2,2]` is 2 and dominant element in `[1]` doesnt exist hence not valid split.
    *   Splitting at index 2 gives `[1,2]` and `[2,2]`. The dominant element in `[2,2]` is 2 and dominant element in `[1,2]` doesnt exist hence not valid split.

*   `nums = [1,2,2,2,3]`
    *   Splitting at index 2 gives `[1,2,2]` and `[2,3]`.  The dominant element in `[1,2,2]` is 2 and the dominant element in `[2,3]` does not exist.

## 📊 **Algorithm**

*   Find the dominant element in the entire `nums` list.
*   Iterate through `nums` from index 0 to `nums.size() - 2`.
*   Maintain a count of the dominant element in the left sublist.
*   Calculate the count of the dominant element in the right sublist.
*   Check if both sublists have the dominant element occurring more than half the time in their respective sublists.
*   If both conditions are met, return the current index.
*   If no such index is found, return -1.

## 🔥 **Code Implementation**

```java
class Solution {
    public int minimumIndex(List<Integer> nums) {
        int[] ans = getFrequency(0, nums.size() - 1, nums);
        int highF = ans[1];
        int ele = ans[0];

        int lcount = 0, rcount = highF;

        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) == ele) {
                lcount++;
                rcount = highF - lcount;
            }

            int leftSize = i + 1;
            int rightSize = nums.size() - leftSize;

            if (lcount * 2 > leftSize && rcount * 2 > rightSize) {
                return i;
            }
        }
        return -1;
    }


    public int[] getFrequency(int i, int j, List<Integer> nums) {
        int minReqLength = (j-i+1)/2;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (; i <= j; i++)
            hm.put(nums.get(i), hm.getOrDefault(nums.get(i), 0) + 1);

        int highF = 0;
        int ele = 0;
        for (Map.Entry<Integer, Integer> e : hm.entrySet()) {
            if (e.getValue() > minReqLength) {
                highF = e.getValue();
                ele = e.getKey();
                return new int[]{ele, highF};        
            }
        }
        return new int[]{ele, highF};
    }
}



// BRUTE FORCE APPROACH 
    // public int minimumIndex(List<Integer> nums) {
    //     int[] ans = getFrequency(0, nums.size() - 1, nums);
    //     int highF = ans[1];
    //     int ele = ans[0];

    //     for (int i = 0; i < nums.size() - 1; i++) {
    //         int[] left = getFrequency(0, i, nums);
    //         int[] right = getFrequency(i + 1, nums.size() - 1, nums);
    //         int e1 = left[0];
    //         int e2 = right[0];

    //         if (e1 != e2 || e1 != ele || e2 != ele)
    //             continue;
    //         return i;
    //     }
    //     return -1;
    // }
```

## 📊 **ASCII Representation**

(Not applicable, since the problem doesn't involve grids, trees or movements.)

## 📊 **WORKING**

Let's walk through the given example: `nums = [2,2,2,3,3]`

1.  `getFrequency(0, 4, nums)` finds that the dominant element in the whole array is `2` with a frequency of 3. `ele = 2`, `highF = 3`.

2.  `lcount = 0`, `rcount = 3`

3.  Loop from `i = 0` to `i = 3`:

    *   `i = 0`: `nums.get(0) == 2`, so `lcount = 1`, `rcount = 2`. `leftSize = 1`, `rightSize = 4`.  `lcount * 2 > leftSize` (2 > 1) is true. `rcount * 2 > rightSize` (4 > 4) is false.
    *   `i = 1`: `nums.get(1) == 2`, so `lcount = 2`, `rcount = 1`. `leftSize = 2`, `rightSize = 3`.  `lcount * 2 > leftSize` (4 > 2) is true. `rcount * 2 > rightSize` (2 > 3) is false.
    *   `i = 2`: `nums.get(2) == 2`, so `lcount = 3`, `rcount = 0`. `leftSize = 3`, `rightSize = 2`.  `lcount * 2 > leftSize` (6 > 3) is true. `rcount * 2 > rightSize` (0 > 2) is false.
    *   `i = 3`: `nums.get(3) == 3`, so `lcount = 3`, `rcount = 0`. `leftSize = 4`, `rightSize = 1`.  `lcount * 2 > leftSize` (6 > 4) is true. `rcount * 2 > rightSize` (0 > 1) is false.

4.  The loop finishes, and since no valid split was found, the function returns `-1`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: **O(n)**, where n is the length of the input list `nums`. The `getFrequency` method iterates through the list once, and the main loop also iterates through the list once.
*   **Space Complexity**: **O(n)** in the worst case for the `getFrequency` method, due to the `HashMap` storing the frequencies of each element in `nums`. In cases where there are many unique numbers in the array this complexity will hold, however it could reduce to O(k) where k represents the unique numbers and k<n.
    