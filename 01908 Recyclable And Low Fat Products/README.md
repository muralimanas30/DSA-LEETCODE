# 01908 - Recyclable And Low Fat Products
    
**Language:** Mysql  
**Runtime:** 1400 ms (Beats 15.93% of users)  
**Memory:** 0B (Beats 100.00% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 1908 | Recyclable and Low Fat Products | [LeetCode Problem](https://leetcode.com/problems/recyclable-and-low-fat-products/) |

---

## 💡 **Problem Explanation**

The problem requires you to retrieve the `product_id` from a `Products` table where the product is both low fat (`low_fats = 'Y'`) and recyclable (`recyclable = 'Y'`). Essentially, you need to filter the table based on these two criteria and return the ID of the products that satisfy both conditions.

**Sample Input:**

Consider a `Products` table with the following data:

| product_id | low_fats | recyclable |
|------------|----------|------------|
| 0          | Y        | N          |
| 1          | Y        | Y          |
| 2          | N        | Y          |
| 3          | Y        | Y          |
| 4          | N        | N          |

**Expected Output:**

The query should return the following `product_id` values:

| product_id |
|------------|
| 1          |
| 3          |

These products are both low fat and recyclable.

## 📊 **Algorithm**

*   Select the `product_id` column from the `Products` table.
*   Filter the rows based on two conditions:
    *   `low_fats` must be equal to 'Y'.
    *   `recyclable` must be equal to 'Y'.
*   Return the filtered `product_id` values.

## 🔥 **Code Implementation**

```sql
SELECT product_id
FROM Products
WHERE low_fats = 'Y' AND recyclable = 'Y';
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The time complexity is **O(n)**, where n is the number of rows in the `Products` table. This is because the SQL query needs to scan each row to check if it meets the specified conditions.
*   **Space Complexity:** The space complexity is **O(1)**, meaning constant space. The query doesn't use extra space that scales with the input size. It only stores the `product_id` values that meet the conditions, and the number of such values does not affect the fundamental space usage of the query execution.
    