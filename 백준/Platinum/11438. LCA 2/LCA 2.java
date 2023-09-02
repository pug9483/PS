import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {        
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] depth;
    public static int[] parent;
    public static boolean[] visited;
    public static int[][] p;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        depth = new int[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        p = new int[N+1][(int)Math.sqrt(N) + 1];
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        bfs(1);
        dp();
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
 
    public static void bfs(int src){
        Queue<Integer> q = new LinkedList<>();
        depth[src] = 0;
        visited[src] = true;
        parent[src] = 0;
        q.add(src);
        
        while(!q.isEmpty()){
            int here = q.poll();
            for(int there: graph.get(here)){
                if(visited[there]) continue;
                visited[there] = true;
                parent[there] = here;
                depth[there] = depth[here] + 1;
                q.add(there);
            }
        }
    }
    
    public static void dp(){
        for(int i = 1; i <= N; i++) p[i][0] = parent[i];
        for(int j = 1; (1<<j) < N; j++){
            for(int i = 1; i <= N; i++){
                if(p[i][j-1] != 0)
                    p[i][j] = p[p[i][j-1]][j-1];
            }
        }
    }
    
    public static int LCA(int u, int v){
        if(depth[u] < depth[v]){
            int tmp = u;
            u = v;
            v = tmp;
        }
        int log = 1;
        while((1<<log) <= depth[u]) log++;
        log--;
        for(int i = log; i >= 0; i--){
            if(depth[u] - (1 << i) >= depth[v])
                u = p[u][i];
        }
        if(u == v) return u; // v가 u의 조상일 경우
        else{
            for(int i = log; i >= 0; i--){
                if(p[u][i] != 0 && p[u][i] != p[v][i]){
                    u = p[u][i];
                    v = p[v][i];
                }
            }
        }
        return parent[u];
    }
}