# 02262 - Solving Questions With Brainpower
    
**Language:** Java  
**Runtime:** 12 ms (Beats 31.25% of users)  
**Memory:** 125 MB (Beats 29.82% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2262 | Solving Questions With Brainpower | [LeetCode Problem](https://leetcode.com/problems/solving-questions-with-brainpower/) |

---

## 💡 **Problem Explanation**

You are given a 2D integer array `questions` where `questions[i] = [pointsi, brainpoweri]`.

The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0). For each question `i`, you have two choices:

*   **Answer** the question to gain `pointsi` points and skip the next `brainpoweri` questions.
*   **Skip** the question without gaining any points.

Return the **maximum** points you can earn for the exam.

**Example:**

```
Input: questions = [[3,2],[4,3],[4,4],[2,5]]
Output: 5
Explanation: The maximum points can be earned by answering the 0th question and skipping the 1st question.
Answer 0th question: points = 3, skipping the next 2 questions
Since we skip the next 2 questions, we do not get any points by answering the 1st and 2nd questions.
Only answer the 3rd question: points = 2
Total points = 3 + 2 = 5.
```

---

## 📊 **Algorithm**

*   Create a DP array `dp` of the same length as the `questions` array to store the maximum points that can be obtained starting from each question.
*   Initialize all elements of `dp` to -1.
*   Use recursion to determine the maximum points for each question:
    *   If the current question index `i` is out of bounds (i.e., `i >= questions.length`), return 0.
    *   If `dp[i]` is not -1, it means the maximum points for question `i` have already been calculated, so return `dp[i]`.
    *   Calculate the points gained by answering the current question (`consider`):
        *   Add the points of the current question to `consider`.
        *   Recursively calculate the maximum points that can be obtained by skipping the next `brainpoweri` questions.
    *   Calculate the points gained by skipping the current question (`dontConsider`):
        *   Recursively calculate the maximum points that can be obtained by moving to the next question.
    *   Store the maximum of `consider` and `dontConsider` in `dp[i]`.
    *   Return `dp[i]`.

---

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public long mostPoints(int[][] questions) {
        long[] dp = new long[questions.length];
        Arrays.fill(dp,-1);
        return recursion(questions,0,dp);
    }
    public long recursion(int[][] questions,int i,long[] dp){
        if(i>=dp.length)    return 0l;
        if(dp[i]!=-1) return dp[i];
        long consider = questions[i][0];
        if(i+questions[i][1]+1<questions.length)
            consider += recursion(questions,i+questions[i][1]+1,dp);
        long dontConsider = recursion(questions,i+1,dp);
        dp[i] = Math.max(consider,dontConsider);
        return dp[i];
    }
}
```

## 📊 **ASCII Representation**

Not applicable for this problem, as it doesn't involve grids or trees.

## 📊 **WORKING**

Let's trace the execution with `questions = [[3,2],[4,3],[4,4],[2,5]]`:

1.  `mostPoints([[3,2],[4,3],[4,4],[2,5]])` calls `recursion(questions, 0, dp)` with `dp` initialized to `[-1, -1, -1, -1]`.

2.  `recursion([[3,2],[4,3],[4,4],[2,5]], 0, [-1,-1,-1,-1])`:
    *   `consider = 3 + recursion(questions, 0+2+1, dp) = 3 + recursion(questions, 3, dp)`
    *   `dontConsider = recursion(questions, 1, dp)`

3.  `recursion([[3,2],[4,3],[4,4],[2,5]], 3, [-1,-1,-1,-1])`:
    *   `consider = 2 + recursion(questions, 3+5+1, dp) = 2 + recursion(questions, 9, dp) = 2 + 0 = 2`
    *   `dontConsider = recursion(questions, 4, dp) = 0`
    *   `dp[3] = max(2, 0) = 2`
    *   returns 2

4.  `consider = 3 + 2 = 5` in the first `recursion` call.

5.  `recursion([[3,2],[4,3],[4,4],[2,5]], 1, [-1,-1,-1, 2])`:
    *   `consider = 4 + recursion(questions, 1+3+1, dp) = 4 + recursion(questions, 5, dp) = 4 + 0 = 4`
    *   `dontConsider = recursion(questions, 2, dp)`

6.  `recursion([[3,2],[4,3],[4,4],[2,5]], 2, [-1,-1,-1, 2])`:
    *   `consider = 4 + recursion(questions, 2+4+1, dp) = 4 + recursion(questions, 7, dp) = 4 + 0 = 4`
    *   `dontConsider = recursion(questions, 3, dp) = 2`  (from DP array)
    *   `dp[2] = max(4, 2) = 4`
    *   returns 4

7. `consider = 4`
   *`dontConsider = 4`
    `dp[1] = max(4,4) = 4`
    returns 4

8. Back to recursion(0): `dp[0] = max(5,4) = 5` and return 5.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(N), where N is the number of questions. This is because each question is visited at most once due to memoization.
*   **Space Complexity:** O(N), where N is the number of questions, due to the `dp` array and the recursion stack.
    