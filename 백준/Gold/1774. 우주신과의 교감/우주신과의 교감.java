import java.io.*;
import java.util.*;

public class Main {  
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    static class Edge{
        int u, v;
        double w;
        public Edge(int u, int v, double w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static List<double[]> points = new ArrayList<>();
    public static int[] parent;
    public static List<Edge> edges = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for(int i = 0; i < N; i++) parent[i] = i;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            double y = Double.parseDouble(st.nextToken());
            double x = Double.parseDouble(st.nextToken());
            points.add(new double[]{y, x});
        }
        for(int u = 0; u < N; u++){
            for(int v = 0; v < N; v++){
                edges.add(new Edge(u, v, getDist(u, v)));
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            if(find(u) == find(v)) continue;
            union(u, v);
        }
        System.out.printf("%.2f", solve());
    }
    
    public static int find(int u){
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }
    
    public static void union(int a, int b){
        int u = find(a);
        int v = find(b);
        if(u == v) return;
        parent[u] = v;
    }
    
    public static double getDist(int u, int v){
        double[] uP = points.get(u);
        double[] vP = points.get(v);
        return Math.sqrt((uP[0] - vP[0]) * (uP[0] - vP[0]) + (uP[1] - vP[1]) * (uP[1] - vP[1]));
    }
    
    public static double solve(){
        double ret = 0;
        edges.sort((o1, o2) -> Double.compare(o1.w, o2.w));
        for(Edge e : edges){
            if(find(e.u) == find(e.v)) continue;
            union(e.u, e.v);
            ret += e.w;
        }
        return ret;
    }
}