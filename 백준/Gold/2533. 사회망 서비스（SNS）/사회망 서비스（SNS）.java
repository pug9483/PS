import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[][] dp;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N][2];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(0);
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }

    private static void dfs(int here){
        visited[here] = true;
        dp[here][0] = 1;

        for (int next : graph.get(here)) {
            if(visited[next]) continue;
            dfs(next);
            dp[here][0] += Math.min(dp[next][0], dp[next][1]);
            dp[here][1] += dp[next][0];
        }
    }
}