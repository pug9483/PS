import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int R;
    public static int Q;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] size;
    public static boolean[] visited;
    
    public static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = new int[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(R);
        for(int i = 0; i < Q; i++){
            int node = Integer.parseInt(br.readLine());
            sb.append(size[node]).append("\n");
        }
        System.out.print(sb);
    }
    
    public static int dfs(int node){
        visited[node] = true;
        int ret = 1;
        for(int next: graph.get(node)){
            if(visited[next]) continue;
            ret += dfs(next);
        }
        size[node] = ret;
        return ret;
    }
}