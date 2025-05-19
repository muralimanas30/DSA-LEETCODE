# 03321 - Type Of Triangle
    
**Language:** Java  
**Runtime:** 1 ms (Beats 34.56% of users)  
**Memory:** 41.9 MB (Beats 74.91% of users)  

## 💡 **Problem Explanation**

The problem asks us to determine the type of a triangle given the lengths of its three sides. We need to return "equilateral" if all sides are equal, "isosceles" if exactly two sides are equal, "scalene" if all sides are different, and "none" if the given side lengths cannot form a valid triangle.

**Example 1:**

*   **Input:** `nums = [2, 2, 2]`
*   **Output:** `"equilateral"`

**Example 2:**

*   **Input:** `nums = [3, 4, 5]`
*   **Output:** `"scalene"`

**Example 3:**

*   **Input:** `nums = [1, 2, 4]`
*   **Output:** `"none"`

## 📊 **Algorithm**

*   Sort the input array `nums` in ascending order. This simplifies the triangle inequality check.
*   Check if the given side lengths can form a valid triangle by verifying if the sum of the two smaller sides is greater than the largest side. If not, return "none".
*   If the triangle is valid, check if all sides are equal. If so, return "equilateral".
*   If not all sides are equal, check if exactly two sides are equal. If so, return "isosceles".
*   If none of the above conditions are met, it means all sides are different, so return "scalene".

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {

    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] + nums[1] <= nums[2]) {
            return "none";
        } else if (nums[0] == nums[2]) {
            return "equilateral";
        } else if (nums[0] == nums[1] || nums[1] == nums[2]) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  The dominant operation is sorting the array `nums`, which takes **O(1)**, as the array will always have a fixed size of 3 elements to sort. Hence the time complexity is **O(1)**.
*   **Space Complexity:** The space complexity is **O(1)**, as we are using a constant amount of extra space.
    