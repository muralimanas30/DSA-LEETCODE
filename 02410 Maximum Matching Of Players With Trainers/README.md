# 02410 - Maximum Matching of Players With Trainers

**Language:** Java  
**Runtime:** 137 ms (Beats 5.41% of users)  
**Memory:** 58.3 MB (Beats 35.71% of users)  

---

## 📎 External Link

- [LeetCode Problem 2410: Maximum Matching of Players With Trainers](https://leetcode.com/problems/maximum-matching-of-players-with-trainers/)

---

## 📝 Problem Statement

Given two integer arrays, `players` and `trainers`, representing the skill levels of players and the training capacities of trainers, respectively, match each player to at most one trainer such that the trainer's capacity is greater than or equal to the player's skill. Each trainer can be matched with at most one player. Return the maximum number of players that can be matched.

---

## 📚 Example Inputs & Outputs

| Input | Output | Explanation |
|-------|--------|-------------|
| `players = [4,7,9]`<br>`trainers = [8,2,5,8]` | `2` | Player 1 (4) → Trainer 3 (5), Player 2 (7) → Trainer 1 (8). Player 3 (9) cannot be matched. |
| `players = [1,1,1]`<br>`trainers = [10]` | `1` | Only one trainer, so only one player can be matched. |
| `players = [5,6]`<br>`trainers = [1,2,3]` | `0` | No trainer can train any player. |

---

## 🏆 Solution Overview

This problem is a variant of the bipartite matching problem.  
We use a **greedy approach** with a `TreeMap` to efficiently find the smallest available trainer who can train each player.

- **Why this approach?**
  - Sorting and greedy matching ensures that each player is matched with the least capable trainer who can handle them, maximizing the number of matches.
  - `TreeMap` allows for efficient lookup and removal of trainers by capacity.

---

## 🧩 Variables & Data Structures

| Name      | Type                  | Purpose                                                                 |
|-----------|-----------------------|-------------------------------------------------------------------------|
| `freqT`   | `TreeMap<Integer,Integer>` | Stores the count of trainers for each capacity (acts as a multiset).    |
| `players` | `int[]`               | Array of player skill levels.                                           |
| `trainers`| `int[]`               | Array of trainer capacities.                                            |
| `count`   | `int`                 | Number of successful player-trainer matches.                            |
| `key`     | `Integer`             | The least trainer capacity ≥ player's skill (from `TreeMap`).           |

---

## 🛠️ Step-by-Step Algorithm

1. **Build Trainer Capacity Map:**
   - For each trainer, increment their capacity count in `freqT`.

2. **Iterate Over Players:**
   - For each player, use `ceilingKey` to find the smallest trainer capacity that can train them.
   - If found, decrement or remove that trainer from `freqT` and increment the match count.

3. **Return the Match Count:**
   - After all players are processed, return the total number of matches.

---

## 🎨 Visual Diagrams

### Example Matching

For `players = [4,7,9]`, `trainers = [8,2,5,8]`:

```
Players:   4   7   9
           |   |
Trainers:  2   5   8   8

Matching:
- Player 4 → Trainer 5
- Player 7 → Trainer 8
- Player 9 → (no trainer available)
```

### Table Representation

| Player Skill | Trainer Chosen | Trainer Capacity | Remaining Trainers |
|--------------|---------------|------------------|--------------------|
| 4            | Yes           | 5                | 2,8,8              |
| 7            | Yes           | 8                | 2,8                |
| 9            | No            | -                | 2,8                |

---

## 🧮 Step-by-Step Walkthrough

**Input:** `players = [4,7,9]`, `trainers = [8,2,5,8]`

1. **Build freqT:** `{2:1, 5:1, 8:2}`
2. **Player 4:** `ceilingKey(4)` → 5. Match. Remove 5. `freqT = {2:1, 8:2}`. `count = 1`
3. **Player 7:** `ceilingKey(7)` → 8. Match. Remove one 8. `freqT = {2:1, 8:1}`. `count = 2`
4. **Player 9:** `ceilingKey(9)` → null. No match.
5. **Return:** `2`

---

## 💻 Java Code Implementation

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

---

## 🛠️ Programming Workflow

### Logical Flow (Numbered List)

1. Initialize a `TreeMap` to count trainer capacities.
2. For each trainer, increment their count in the map.
3. For each player:
    - Find the smallest trainer capacity ≥ player's skill.
    - If found, decrement/remove trainer from map and increment match count.
4. Return the total match count.

### Flowchart (ASCII Art)

```
+-------------------------------+
| 1. Build trainer capacity map |
+---------------+---------------+
                |
                v
+-------------------------------+
| 2. For each player:           |
|   a. Find trainer ≥ skill     |
|   b. If found:                |
|      - Remove/decrement trainer|
|      - Increment match count  |
+---------------+---------------+
                |
                v
+-------------------------------+
| 3. Return match count         |
+-------------------------------+
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** `O(n log m)`  
  - For each player (`n`), `ceilingKey`/`put`/`remove` in `TreeMap` take `O(log m)` where `m` is the number of trainers.
  - If sorting is needed for players/trainers, add `O(n log n + m log m)` (not present in this code).
- **Space Complexity:** `O(m)`  
  - For the `TreeMap` storing trainer capacities.

---

## 📚 References

- [LeetCode Problem 2410](https://leetcode.com/problems/maximum-matching-of-players-with-trainers/)
- [TreeMap (Java SE Documentation)](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)
- [Greedy Algorithms (GeeksforGeeks)](https://www.geeksforgeeks.org/greedy-algorithms/)
