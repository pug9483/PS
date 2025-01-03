import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    int N;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        N = n;
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < roads.length; i++){
            int u = roads[i][0];
            int v = roads[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        int[] dist = bfs(destination);
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
    
    public int[] bfs(int src){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        
        while(!q.isEmpty()){
            int here = q.poll();
            for(int next: graph.get(here)){
                if(dist[next] >= 0) continue;
                q.add(next);
                dist[next] = dist[here] + 1;
            }
        }
        return dist;
    }
}