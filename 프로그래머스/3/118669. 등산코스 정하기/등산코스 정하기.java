/*
 * 쉼터 혹은 산봉우리를 방문할 때마다 휴식을 취할 수 있으며, 휴식 없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산코스의 intensity라고 부르기로 합니다.
 * intensity가 최소가 되도록 등산코스를 정하려고 합니다.
 * gates : 출발점(여러 개)
 * summits : 산봉우리(여러 개)
 * 
 * 결과 : [산봉우리 번호, intensity 최솟값]
 */

import java.util.*;

class Solution {
    static class Node{
        int v, w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    
    List<List<Node>> graph = new ArrayList<>();
    int N;
    int INF = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        
        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < paths.length; i++){
            int u = paths[i][0];
            int v = paths[i][1];
            int w = paths[i][2];
            
            if(isGate(u, gates) || isSummit(v, summits)){
                graph.get(u).add(new Node(v, w));   
            }
            else if(isGate(v, gates) || isSummit(u, summits)){
                graph.get(v).add(new Node(u, w));
            }
            else{
                graph.get(u).add(new Node(v, w)); 
                graph.get(v).add(new Node(u, w));
            }
        }
        
        return solve(gates, summits);
    }
    
    public int[] solve(int[] gates, int[] summits){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        
        for(int gate: gates){
            pq.add(new Node(gate, 0));
            dist[gate] = 0;
        }
        
        while(!pq.isEmpty()){
            Node here = pq.poll();
            if(dist[here.v] < here.w) continue;
            
            for(Node next: graph.get(here.v)){
                if(dist[next.v] > Math.max(dist[here.v], next.w)){
                    pq.add(next);
                    dist[next.v] = Math.max(dist[here.v], next.w);
                }
            }
        }
        
        int ret = INF;
        int pos = INF;
        
        Arrays.sort(summits);
        
        for(int summit : summits){
            if(dist[summit] < ret){
                ret = dist[summit];
                pos = summit;
            }
        }
        
        return new int[]{pos, ret};
    }
    
    public boolean isGate(int v, int[] gates){
        for (int gate : gates) {
            if (v == gate) return true;
        }
    
        return false;
    }
    
    public boolean isSummit(int v, int[] summits){
        for (int summit : summits) {
            if (v == summit) return true;
        }
        
        return false;
    }
    
}