# 01908 - Recyclable And Low Fat Products
    
**Language:** Mysql  
**Runtime:** 1400 ms (Beats 15.93% of users)  
**Memory:** 0B (Beats 100.00% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title                            | 🔗 Link                                                                      |
| ------------------ | ---------------------------------- | ----------------------------------------------------------------------------- |
| 1965               | Employees With Missing Information | [LeetCode Problem](https://leetcode.com/problems/employees-with-missing-information/) |

---

## 💡 **Problem Explanation**

The problem asks us to find the `employee_id` of employees whose information is incomplete.  Incomplete information means that either the employee's name is missing from the `Employees` table or their salary is missing from the `Salaries` table. We need to return a list of these `employee_id`s sorted in ascending order.

## 📊 **Algorithm**

*   Perform a full outer join between the `Employees` and `Salaries` tables using the `employee_id` as the join key.
*   Filter the results to include only rows where either the `name` column from `Employees` or the `salary` column from `Salaries` is `NULL`.
*   Select the `employee_id` from the filtered rows.
*   Order the results in ascending order by `employee_id`.

## 🔥 **Code Implementation**

```sql
SELECT employee_id
FROM Employees
LEFT JOIN Salaries
USING (employee_id)
WHERE salary IS NULL

UNION

SELECT employee_id
FROM Salaries
LEFT JOIN Employees
USING (employee_id)
WHERE name IS NULL

ORDER BY employee_id;
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  The time complexity is primarily determined by the join operation and the sorting.  In the worst case, the join operation could take **O(m*n)** time, where m and n are the sizes of the Employees and Salaries tables, respectively. However, with proper indexing, this can be improved.  The sorting operation takes **O(k log k)** where k is the number of employee IDs with missing information.

*   **Space Complexity:** The space complexity depends on the size of the intermediate result set after the join and before the filtering.  In the worst case, this could be **O(m+n)**. The space required for sorting is typically **O(k)**, where `k` is the number of employee IDs in the result.
    