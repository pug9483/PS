import java.util.*;

class Solution {
    private List<List<Integer>> graph;
    
    public int solution(int n, int[][] edge) {
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++){
            int u = edge[i][0] - 1;
            int v = edge[i][1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        int maxDist = 0;
        
        while(!q.isEmpty()){
            int here = q.poll();
            maxDist = Math.max(maxDist, dist[here]);
            for(int next : graph.get(here)){
                if(dist[next] == -1){
                    q.add(next);
                    dist[next] = dist[here] + 1;
                }
            }
        }
        
        int answer = 0;        
        for(int i = 0; i < n; i++){
            if(dist[i] == maxDist) answer++;
        }
        
        return answer;
    }

}