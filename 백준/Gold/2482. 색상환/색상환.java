import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        System.out.println(solve());
    }

    public static int solve() {
        int ret = 0;
        int[][] dp = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 1; j <= (i + 1) / 2; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        ret += (dp[N - 3][K - 1] + dp[N - 1][K]) % MOD;


        return ret;
    }
}