# 00001 - Two Sum

**Language:** Java  
**Runtime:** 2 ms (Beats 98.83% of users)  
**Memory:** 45.3 MB (Beats 18.66% of users)  

---

## 🔗 External Link

- [LeetCode Problem #1: Two Sum](https://leetcode.com/problems/two-sum/)

---

## 📝 Problem Statement

Given an array of integers `nums` and an integer `target`, return **indices of the two numbers such that they add up to `target`**.

- Each input will have **exactly one solution**.
- You **may not use the same element twice**.
- Return the answer in any order.

---

## 📋 Example Inputs & Outputs

| Input                  | Output   | Explanation                                  |
|------------------------|----------|----------------------------------------------|
| nums = [2,7,11,15], target = 9 | [0,1]   | nums[0] + nums[1] == 9                      |
| nums = [3,2,4], target = 6     | [1,2]   | nums[1] + nums[2] == 6                      |
| nums = [3,3], target = 6       | [0,1]   | nums[0] + nums[1] == 6                      |

---

## 📊 Solution Overview

This problem is a classic example of using a **HashMap** for efficient lookups.  
The brute-force approach checks all pairs (O(n²)), but using a HashMap allows us to solve it in **O(n)** time by storing previously seen numbers and their indices.

**Why this approach?**
- HashMap provides O(1) average time complexity for insert and lookup.
- We only need a single pass through the array.

---

## 🧩 Variables & Data Structures

| Variable         | Type                  | Description                                                      |
|------------------|-----------------------|------------------------------------------------------------------|
| `nums`           | `int[]`               | Input array of integers                                          |
| `target`         | `int`                 | Target sum to find                                               |
| `hs`             | `Map<Integer,Integer>`| HashMap storing value → index for fast lookup                    |
| `i`              | `int`                 | Current index in the array                                       |
| `difference`     | `int`                 | Value needed to reach target with current number (`target - nums[i]`) |

---

## 🪜 Step-by-Step Algorithm

1. **Initialize** an empty HashMap (`hs`).
2. **Iterate** through the array `nums`:
    - For each index `i`, compute `difference = target - nums[i]`.
    - **Check** if `difference` exists in `hs`:
        - If yes, return `[hs.get(difference), i]`.
    - **Otherwise**, add `nums[i]` and its index `i` to `hs`.
3. If no solution is found, return a default value (should not happen as per constraints).

---

## 🎨 Visual Diagram

Suppose `nums = [2, 7, 11, 15]`, `target = 9`:

```
Index:   0   1   2   3
Nums:    2   7  11  15

Step-by-step HashMap (hs) state:

i=0: difference = 9-2=7, hs={}, add 2:0 → hs={2:0}
i=1: difference = 9-7=2, hs={2:0}, found! return [0,1]
```

---

## 🧮 Walkthrough Example

**Input:** `nums = [3, 2, 4]`, `target = 6`

| i | nums[i] | difference | hs before | hs after   | Output      |
|---|---------|------------|-----------|------------|-------------|
| 0 |   3     |   3        | {}        | {3:0}      |             |
| 1 |   2     |   4        | {3:0}     | {3:0,2:1}  |             |
| 2 |   4     |   2        | {3:0,2:1} |            | [1,2] found |

---

## 🔥 Code Implementation

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

---

## 🧭 Programming Workflow

### Numbered Steps

1. Initialize an empty HashMap.
2. For each element in `nums`:
    - Compute the required complement (`target - nums[i]`).
    - If the complement exists in the HashMap, return the indices.
    - Otherwise, store the current number and its index in the HashMap.
3. Return a default value if no solution is found.

### Flowchart

```
+-----------------------------+
|  Initialize empty HashMap   |
+-------------+---------------+
              |
              v
+-----------------------------+
|  For each index i in nums:  |
+-------------+---------------+
              |
              v
+-----------------------------+
|  Compute difference         |
|  = target - nums[i]         |
+-------------+---------------+
              |
              v
+-----------------------------+
|  If difference in HashMap?  |
+----+------------------------+
     | Yes                    | No
     v                        v
+---------+           +----------------------+
| Return  |           | Add nums[i]:i to     |
| indices |           | HashMap, continue    |
+---------+           +----------------------+
```

---

## 🚀 Time & Space Complexity

- **Time Complexity:** O(n), where n is the length of `nums`. Each element is processed once, and HashMap operations are O(1) on average.
- **Space Complexity:** O(n), for storing up to n elements in the HashMap.

---

## 📚 References

- [LeetCode Editorial: Two Sum](https://leetcode.com/problems/two-sum/editorial/)
- [Hash Table - Wikipedia](https://en.wikipedia.org/wiki/Hash_table)
- [Java HashMap Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)