# 02410 - Maximum Matching Of Players With Trainers
    
**Language:** Java  
**Runtime:** 137 ms (Beats 5.41% of users)  
**Memory:** 58.3 MB (Beats 35.71% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2410 | MAXIMUM MATCHING OF PLAYERS WITH TRAINERS | [LeetCode Problem](https://leetcode.com/problems/maximum-matching-of-players-with-trainers/) |

---

## 💡 **Problem Explanation**

Imagine you have a group of players, each with a certain skill level, and a group of trainers, each with a certain training capacity.  You want to find the maximum number of players you can match with trainers, such that the trainer's capacity is greater than or equal to the player's skill.

For example:

**Input:** `players = [4,7,9]`, `trainers = [8,2,5,8]`
**Output:** `2`

**Explanation:**

*   Player 1 (skill 4) can be matched with trainer 1 (capacity 8) or trainer 3 (capacity 5) or trainer 4 (capacity 8).
*   Player 2 (skill 7) can be matched with trainer 1 (capacity 8) or trainer 4 (capacity 8).
*   Player 3 (skill 9) can be matched with trainer 1 (capacity 8) or trainer 4 (capacity 8).

An optimal matching would be:

*   Player 1 (4) -> Trainer 3 (5)
*   Player 2 (7) -> Trainer 1 (8)

Another possible optimal matching:
*   Player 1 (4) -> Trainer 1 (8)
*   Player 2 (7) -> Trainer 4 (8)

Thus, the maximum number of players that can be matched is 2.

---

## 📊 **Algorithm**

*   Sort both the `players` and `trainers` arrays.  Sorting allows us to efficiently find matches by iterating through both arrays with two pointers.
*   Initialize two pointers, `i` for `players` and `j` for `trainers`, both starting at 0.
*   Iterate while both `i` is less than the length of `players` and `j` is less than the length of `trainers`.
*   If `players[i]` is less than or equal to `trainers[j]`, it means we can match the player with the trainer. Increment both `i` and `j`, and increment the `count` of matched players.
*   Otherwise, if `players[i]` is greater than `trainers[j]`, it means the current trainer is not capable of training the current player. Increment `j` to consider the next trainer.
*   Return the final `count` of matched players.

## 🔥 **Code Implementation**

```java
import java.util.TreeMap;

class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        TreeMap<Integer,Integer> freqT = new TreeMap<>();
        for(int capacity : trainers)
            freqT.put(capacity,freqT.getOrDefault(capacity,0)+1);
        int count = 0;
        for(int player : players){
            Integer key = freqT.ceilingKey(player);
            if(key==null)
                continue;
            if(freqT.get(key)==1)
                freqT.remove(key);
            else
                freqT.put(key,freqT.get(key)-1);
            count++;
        }
        return count;
    }
}
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n log n + m log m), where n is the number of players and m is the number of trainers. This is due to the sorting of both arrays.  The `TreeMap` operations such as `ceilingKey`, `put`, and `remove` take O(log m) time in the worst case and are called at most n times in total, thus contributing to O(n log m). Overall, sorting dominates resulting in O(n log n + m log m) for the overall algorithm.
*   **Space Complexity:** O(m) in the worst case, where m is the number of trainers.  This is because the `TreeMap` `freqT` can store at most `m` key-value pairs, where `m` is the number of trainers.
    