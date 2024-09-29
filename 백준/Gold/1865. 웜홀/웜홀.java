import java.io.*;
import java.util.*;

public class Main {        
    static class Edge{
        int u;
        int v;
        int w;
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int W;
    public static List<Edge> edges;
    public static final int INF = 2000000000;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(u, v, w));
                edges.add(new Edge(v, u, w));
            }
            for(int i = 0; i < W; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = -Integer.parseInt(st.nextToken());
                edges.add(new Edge(u, v, w));
            }
            System.out.println(solve(0) ? "YES" : "NO");
        }
    }
    
    public static boolean solve(int src){
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        
        for(int k = 0; k < N; k++){
            for(Edge e : edges){
                if(dist[e.v] > dist[e.u] + e.w){
                    dist[e.v] = dist[e.u] + e.w;
                    if(k == N-1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}