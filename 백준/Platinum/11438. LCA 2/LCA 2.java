import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {        
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int N, M;
    public static int[][] p;
    public static int[] tin;
    public static int[] tout;
    public static int timer = 0;
    public static int log = 0;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tin = new int[N+1];
        tout = new int[N+1];
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        while((1 << log) <= N) log++;
        p = new int[N+1][log];
        log--;
        dfs(1, 1);
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
 
    public static void dfs(int here, int parent){
        tin[here] = ++timer;
        p[here][0] = parent;
        for(int i = 1; i <= log; i++)
            p[here][i] = p[p[here][i-1]][i-1];
        for(int there: graph.get(here))
            if(there != parent)
                dfs(there, here);
        tout[here] = ++timer;
    }

    public static int LCA(int u, int v){
        if(upper(u, v)) return u;
        if(upper(v, u)) return v;
        for(int i = log; i >= 0; i--)
            if(!upper(p[u][i], v))
                u = p[u][i];
        return p[u][0];
    }
    
    public static boolean upper(int u, int v){
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }
}