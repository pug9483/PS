import java.util.*;

class Solution {
    private List<List<Integer>> graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < roads.length; i++){
            int u = roads[i][0] - 1;
            int v = roads[i][1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(destination - 1);
        dist[destination - 1] = 0;
        
        while(!q.isEmpty()){
            int here = q.poll();
            for(int there: graph.get(here)){
                if(dist[there] == -1){
                    dist[there] = dist[here] + 1;
                    q.add(there);
                }
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i] - 1];
        }
        return answer;
    }
}