import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] A;
    public static int[][] dp;
    public static boolean[] visited;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        visited = new boolean[N];
        dp = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < N; i++)
            dp[i][1] = A[i];

        dfs(0);
        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    public static void dfs(int here) {
        visited[here] = true;

        for (int next : graph.get(here)) {
            if(visited[next]) continue;
            dfs(next);
            dp[here][0] += Math.max(dp[next][0], dp[next][1]);
            dp[here][1] += dp[next][0];
        }
    }
}
