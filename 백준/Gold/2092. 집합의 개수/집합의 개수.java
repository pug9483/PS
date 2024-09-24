import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int T;
    public static int N;
    public static int S;
    public static int B;
    public static int[] cntArr;
    public static int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        cntArr = new int[T + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cntArr[num]++;
        }
        System.out.println(solve());
    }

    private static int solve() {
        int[][] dp = new int[T + 1][N + 1];

        for (int i = 0; i <= T; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k <= cntArr[i]; k++) {
                    if(j >= k){
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
        }

        int ret = 0;
        for (int k = S; k <= B; k++) {
            ret = (ret + dp[T][k]) % MOD;
        }
        return ret;
    }
}