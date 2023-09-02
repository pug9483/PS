import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {        
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();
    public static int[] parent;
    public static int[] depth;
    public static int INF = 987564321;
    public static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        parent = new int[N+1];
        depth = new int[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i <= N; i++) parent[i] = i;
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(1, 0);
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(LCA(u, v)).append("\n");
        }
        System.out.print(sb);
    }   
    
    public static void dfs(int u, int d){
        visited[u] = true;
        for(int v: graph.get(u)){
            if(visited[v]) continue;
            depth[v] = d+1;
            parent[v] = u;
            dfs(v, d+1);
        }
    }
    
    public static int LCA(int u, int v){
        while(depth[u] > depth[v]) u = parent[u];
        while(depth[u] < depth[v]) v = parent[v];
        while(u != v){
            u = parent[u];
            v = parent[v];
        }
        return u;
    }
}