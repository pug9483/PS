/*
LCA(Lowest Common Ancestor): 가장 가까운 공통 조상 찾기

1. 트리를 이용해 LCA를 구하기
   1_1) 두 정점이 같은 레벨이 되게 만든다.
   1_2) 두 정점이 조상을 향해 계속 올라가다가 같은 부모 정점을 가리키게 되면 해당 정점이 LCA다.
   시간 복잡도: O(N)


2. 트리를 이용해 정점들의 거리 구하기
   Tree의 특징: 사이클이 없기 때문에 임의의 두 정점 사이에 경로가 1개밖에 없다.
   Q. M개의 노드 쌍의 거리를 구하여라.
      방법 1. DFS/BFS로 구할 수 있다: O(MN)
      방법 2. LCA 사용: O(MH) = O(MN)
          dist[i]: 루트에서 i까지의 거리는 BFS/DFS를 통해 dist를 구할 수 있다 -> O(N)
          u와 v사이의 거리: dist[u] + dist[v] - 2*dist[LCA(u,v)] -> O(h) = O(N)

3. 다이나믹 프로그래밍을 통한 LCA 빠르게 구하기
   p[i][j] = 노드 i의 $$(2)^j$번째 조상
   p[i][0] = parent[i] // 첫 번째 조상(=부모)
   p[i][j] = p[p[i][j-1]][j-1]
   -> 노드 i의 $$(2)^j$번째 조상은 노드 i의 2^(j-1)번째 조상의 $$(2)^(j-1)$번째 조상이다.
   
   Q. M개의 노드 쌍의 거리를 구하여라.
    1) 두 노드의 레벨이 다르면 레벨 같을 때까지 레벨이 큰 것을 2^k칸씩 위로 올린다. k는 1씩 감소한다.
    2) 두 노드의 레벨이 같아지면 같은 노드가 되지 않을 때까지 2^k칸씩 위로 올린다. k는 1씩 감소하다.
    3) 마지막으로 1칸 올린다.
    시간복잡도: O(MlogN)
*/

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
