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
    public static int N;
    public static int M;
    public static List<List<Node>> graph = new ArrayList<>();
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        System.out.println(solve(1, N));
    }
    
    public static int solve(int src, int dst){
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        pq.add(new Node(src, 0));
        
        while(!pq.isEmpty()){
            Node here = pq.poll();
            int distance = dist[here.v];
            for(Node there: graph.get(here.v)){
                if(dist[there.v] > distance + there.w){
                    dist[there.v] = distance + there.w;
                    pq.add(there);
                }
            }
        }
        return dist[dst];
    }
}