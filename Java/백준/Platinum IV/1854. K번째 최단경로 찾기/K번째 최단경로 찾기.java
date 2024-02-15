import java.io.*;
import java.util.*;


public class Main {
    static class Node{
        int v;
        int w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int M;
    public static int K;
	public static List<List<Node>> graph = new ArrayList<>();
    public static final int INF = 987654321;
    
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
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }
        solve(1);    
    }
    
    public static void solve(int src){
        PriorityQueue<Integer>[] dist = new PriorityQueue[N+1];
        for(int i = 0; i <= N; i++){
            dist[i] = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        }
        dist[1].add(0);
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        pq.add(new Node(1, 0));
        
        while(!pq.isEmpty()) {
            Node here = pq.poll();
            for(Node next: graph.get(here.v)){
                if(dist[next.v].size() < K){
                    dist[next.v].add(here.w + next.w);
                    pq.add(new Node(next.v, here.w + next.w));
                }
                else if(dist[next.v].peek() > here.w + next.w){
                    dist[next.v].poll();
                    dist[next.v].add(here.w + next.w);
                    pq.add(new Node(next.v, here.w + next.w));
                }
            }
        }
        
        for(int i = 1; i <= N; i++){
            if(dist[i].size() < K) sb.append(-1).append("\n");
            else sb.append(dist[i].peek()).append("\n");
        }
        System.out.print(sb);
    }
}
