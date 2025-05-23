# 00001 - Two Sum
    
**Language:** Java  
**Runtime:** 2 ms (Beats 98.83% of users)  
**Memory:** 45.3 MB (Beats 18.66% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title   | 🔗 Link                                         |
| ------------------ | ----------- | --------------------------------------------- |
| 1                 | TWO SUM | [LeetCode Problem](https://leetcode.com/problems/two-sum/) |

---

## 💡 **Problem Explanation**

Given an array of integers `nums` and an integer `target`, return _indices of the two numbers such that they add up to `target`_.

You may assume that each input would have **exactly one solution**, and you may not use the _same_ element twice.

You can return the answer in any order.

**Example 1:**

```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

**Example 2:**

```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

**Example 3:**

```
Input: nums = [3,3], target = 6
Output: [0,1]
```

## 📊 **Algorithm**

*   Create a HashMap to store each number from the input array along with its index.
*   Iterate through the `nums` array.
*   For each number `nums[i]`, calculate the `difference` needed to reach the `target` (i.e., `target - nums[i]`).
*   Check if the `difference` exists as a key in the HashMap.
    *   If it does, return an array containing the index of the `difference` (obtained from the HashMap) and the current index `i`.
*   If the `difference` is not found in the HashMap, add the current number `nums[i]` and its index `i` to the HashMap.
*   If no solution is found after iterating through the entire array, return a default array (e.g., `[1, 2]`).

## 🔥 **Code Implementation**

```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length==2){
            return new int[]{0,1};
        }
        Map<Integer , Integer > hs = new HashMap<>();

        for(int i =0 ; i< nums.length ; i++){
            int difference = target - nums[i];

            if(hs.containsKey(difference)){
                return new int[]{ hs.get(difference) , i };
            }
            hs.put(nums[i] , i);
        }

    return new int[]{1,2};
    }
}
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n), where n is the length of the input array `nums`. We iterate through the array once.  HashMap operations (containsKey and put) take O(1) on average.
*   **Space Complexity:** O(n), where n is the length of the input array `nums`.  In the worst case, we might store all elements of the array in the HashMap.
    