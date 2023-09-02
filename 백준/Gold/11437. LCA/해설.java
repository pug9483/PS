/*
LCA(Lowest Common Ancestor): 가장 가까운 공통 조상 찾기

1. 두 정점이 같은 레벨이 되게 만든다.
2. 두 정점이 조상을 향해 계속 올라가다가 같은 부모 정점을 가리키게 되면 해당 정점이 LCA다.
시간 복잡도: O(N)
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {        
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static StringBuilder sb = new StringBuilder();
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] parent;
    public static int[] depth;
    public static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        parent = new int[N+1];
        depth = new int[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        bfs();
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
    
    public static void bfs(){
        depth[1] = 0;
        visited[1] = true;
        parent[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty()){
            int here = q.poll();
            for(int there: graph.get(here)){
                if(visited[there]) continue;
                depth[there] = depth[here] + 1;
                visited[there] = true;
                parent[there] = here;
                q.add(there);
            }
        }
    }
    
    public static int LCA(int u, int v){
        if(depth[u] < depth[v]){
            int tmp = u;
            u = v;
            v = tmp;
        }
        while(depth[u] != depth[v]) u = parent[u];
        while(u != v){
            u = parent[u];
            v = parent[v];
        }
        return u;
    }
}
