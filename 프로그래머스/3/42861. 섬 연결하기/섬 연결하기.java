import java.util.*;

class Solution {
    private int[] parent;
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        
        int answer = 0;
        
        for (int[] cost : costs) {
            if (find(cost[0]) == find(cost[1])) continue;
            union(cost[0], cost[1]);
            answer += cost[2];
        }
        
         return answer;
    }
    
    private int find(int u) {
        if (parent[u] == u) {
            return u;
        }
        return parent[u] = find(parent[u]);
    }

    private void union(int u, int v) {
        int a = find(u);
        int b = find(v);

        if (a != b) {
            parent[b] = a;
        }
    }
}