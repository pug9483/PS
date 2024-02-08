import java.io.*;
import java.util.*;

public class Main {
    static class Edge{
        int u, v;
        public Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }
    
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
	public static int M; 
	public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] discovered;
    public static int counter = 0;
    public static List<Edge> edges = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        discovered = new int[N+1];
        Arrays.fill(discovered, -1);
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        solve();
    }   
    
    public static void solve(){
        // dfs를 이용하여 단절선을 찾는다.
        for(int i = 1; i <= N; i++){
            if(discovered[i] == -1)
                dfs(i, 0);
        }
        // 출력 부분
        Collections.sort(edges, (o1, o2) -> {
            int cmp = Integer.compare(o1.u, o2.u);
            return cmp == 0 ? Integer.compare(o1.v, o2.v) : cmp;
        });
        
        System.out.println(edges.size());
        for(Edge e: edges){
            sb.append(e.u).append(" ").append(e.v).append("\n");
        }
        System.out.print(sb);
    }
    
    public static int dfs(int here, int parent){
        discovered[here] = counter++;
        int ret = discovered[here];
        
        for(int there: graph.get(here)){
            if(there == parent) continue;
            if(discovered[there] == -1){
                int subtree = dfs(there, here);
                if(subtree > discovered[here])
                    addEdge(here, there);
                ret = Math.min(ret, subtree);
            }
            else ret = Math.min(ret, discovered[there]);
        }
        return ret;
    }
    
    public static void addEdge(int here, int there){
        if(here < there) edges.add(new Edge(here, there));
        else edges.add(new Edge(there, here));
    }
}