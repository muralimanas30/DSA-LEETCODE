# 01908 - Recyclable And Low Fat Products
    
**Language:** Mysql  
**Runtime:** 1400 ms (Beats 15.93% of users)  
**Memory:** 0B (Beats 100.00% of users)  

```markdown
## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title                      | 🔗 Link                                                              |
|------------------|-------------------------------|---------------------------------------------------------------------|
| 1757             | Recyclable and Low Fat Products | [LeetCode Problem](https://leetcode.com/problems/recyclable-and-low-fat-products/) |

---

## 💡 **Problem Explanation**

The problem requires us to select the `product_id` from the `Products` table that satisfies two conditions: the `low_fats` flag is 'Y' (yes, it's low fat) and the `recyclable` flag is also 'Y' (yes, it's recyclable). Essentially, we want to find products that are both low fat and recyclable.

## 📊 **Algorithm**

*   Filter the `Products` table based on the `low_fats` column being equal to 'Y'.
*   Further filter the result based on the `recyclable` column also being equal to 'Y'.
*   Select the `product_id` from the remaining rows.

## 🔥 **Code Implementation**

```sql
SELECT product_id
FROM Products
WHERE low_fats = 'Y' AND recyclable = 'Y';
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The time complexity is **O(n)**, where n is the number of rows in the `Products` table, as we potentially need to scan through all rows to filter the data. However, database systems are optimized for such queries, and the actual execution time can be significantly faster due to indexing and other optimizations.
*   **Space Complexity:** The space complexity is **O(1)** because the query itself does not require significant extra space.  The memory used is primarily for storing the result set, which depends on the number of products satisfying the conditions.
```
    