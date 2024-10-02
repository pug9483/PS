import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] residents;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        residents = new int[N + 1];
        tree = new ArrayList[N + 1];
        dp = new int[N + 1][2]; // [][0]: 우수 마을 아님, [][1]: 우수 마을
        visited = new boolean[N + 1];

        // 각 마을의 주민 수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            residents[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        // 트리 구성
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        // DP 시작 (루트 노드를 1로 설정)
        dfs(1);

        // 결과 출력
        int result = Math.max(dp[1][0], dp[1][1]);
        System.out.println(result);
    }

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;             // 현재 노드가 우수 마을이 아닌 경우
        dp[node][1] = residents[node]; // 현재 노드가 우수 마을인 경우

        for (int child : tree[node]) {
            if (!visited[child]) {
                dfs(child);

                // 현재 노드가 우수 마을이 아닌 경우
                dp[node][0] += Math.max(dp[child][0], dp[child][1]);

                // 현재 노드가 우수 마을인 경우
                dp[node][1] += dp[child][0];
            }
        }
    }
}
