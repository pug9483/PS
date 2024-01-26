import java.io.*;
import java.util.*;

public class Main {  
    static class Node{
        int type, sum;
        public Node(int type, int sum){
            this.type = type;
            this.sum = sum;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] size;
    public static int[][] dp;
    
    public static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        size = new int[N+1];
        st = new StringTokenizer(br.readLine());
        dp = new int[N+1][2];
        
        for(int i = 1; i <= N; i++){
            size[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
    
    public static void dfs(int here, int parent){
        for(int next: graph.get(here)){
            if(next == parent) continue;
            dfs(next, here);
            dp[here][0] += Math.max(dp[next][0], dp[next][1]);
            dp[here][1] += dp[next][0];
        }
        dp[here][1] += size[here];
    }
}