import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    public int solution(int n, int[][] computers) {
        for(int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(computers[i][j] == 1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            answer++;
            bfs(i, visited);
        }
        return answer;
    }
    
    public void bfs(int src, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = true;
        
        while(!q.isEmpty()){
            int here = q.poll();
            for(int next : graph.get(here)){
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
    }
}