import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int H;
    public static int D;
    public static int K;
    public static int[] A;
    public static int[][][] dp = new int[20][200][3];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 20; i++) {
            for (int k = 0; k < 200; k++) {
                Arrays.fill(dp[i][k], -1);
            }
        }

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int ret = H + solve(0, D, 0);
        System.out.println(ret <= 0 ? -1 : ret);
    }

    public static int solve(int index, int dist, int r) {
        if (index == N) return 0;

        if (dp[index][dist][r] != -1) return dp[index][dist][r];
        if (r == 1) return solve(index + 1, dist + K, 2);

        int ret = -INF;
        int damage = A[index];

        // 1. 웅크리기
        ret = Math.max(ret, -action1(dist, damage) + solve(index+1, dist, r));

        // 2. 네발로 걷기
        ret = Math.max(ret, -action2(dist, damage) + solve(index + 1, dist + K, r));

        // 3. 깜짝 놀라게 하기
        if (r == 0)
            ret = Math.max(ret, -action3(dist, damage) + solve(index + 1, dist, 1));

        return dp[index][dist][r] = ret;
    }

    private static int action3(int dist, int damage) {
        int tmp3 = Math.max(0, (damage - dist));
        return tmp3;
    }

    private static int action2(int dist, int damage) {
        return Math.max(0, damage - (dist + K));
    }

    private static int action1(int dist, int damage) {
        return Math.max(0, (damage - dist) / 2);
    }
}
