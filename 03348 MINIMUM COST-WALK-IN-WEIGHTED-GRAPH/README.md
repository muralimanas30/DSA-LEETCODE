# 03348 - minimum-cost-walk-in-weighted-graph
    
**Language:** Java  
**Runtime:** 30 ms (Beats 22.94% of users)  
**Memory:** 124.3 MB (Beats 36.47% of users)  

## Solution
```java
import java.util.*;
class Solution {
    int[] comp;
    int[] compCost;
    int compId = 0;
    int INITIAL = (1 << 18) - 1;
    
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        comp = new int[n];
        Arrays.fill(comp, -1);
        compCost = new int[n];
        Arrays.fill(compCost, -1);
        for (int i = 0; i < n; i++) {
            if (comp[i] == -1) {
                int[] cost = new int[]{INITIAL};
                dfs(i, compId, graph, cost);
                compCost[compId] = graph[i].isEmpty() ? -1 : cost[0];
                compId++;
            }
        }
        int[] res = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0], t = query[i][1];
            res[i] = (comp[s] == comp[t]) ? compCost[comp[s]] : -1;
        }
        return res;
    }
    
    private void dfs(int node, int cid, ArrayList<int[]>[] graph, int[] cost) {
        comp[node] = cid;
        for (int[] nbr : graph[node]) {
            int next = nbr[0], w = nbr[1];
            cost[0] &= w;
            if (comp[next] == -1) {
                dfs(next, cid, graph, cost);
            }
        }
    }
}

```
    