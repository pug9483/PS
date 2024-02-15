import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int v;
        long w;
        int k;
        public Node(int v, long w, int k){
            this.v = v;
            this.w = w;
            this.k = k;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int K;
	public static List<List<Node>> graph = new ArrayList<>();
    public static final Long INF = 20000000000L;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            graph.get(u).add(new Node(v, w, 0));
            graph.get(v).add(new Node(u, w, 0));
        }
        System.out.println(solve(1, N));    
    }
    
    public static long solve(int s, int e){
        long[][] dist = new long[N+1][K+1];
        for(int i = 0; i < N+1; i++) Arrays.fill(dist[i], INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        dist[s][0] = 0;
        pq.add(new Node(s, 0, 0));
        
        while(!pq.isEmpty()){
            Node here = pq.poll();
            int step = here.k;
            if(dist[here.v][step] < here.w) continue;
            if(here.v == N) return here.w;
            for(Node next: graph.get(here.v)){
                // 포장하는 경우
                if(step+1 <= K && dist[next.v][step+1] > dist[here.v][step]){
                    dist[next.v][step+1] = dist[here.v][step];
                    pq.add(new Node(next.v, dist[next.v][step+1], step+1));
                }
                // 포장하지 않는 경우
                if(dist[next.v][step] > dist[here.v][step] + next.w){
                    dist[next.v][step] = dist[here.v][step] + next.w;
                    pq.add(new Node(next.v, dist[next.v][step], step));
                }
            }
        }
        return -1;
    }
}
