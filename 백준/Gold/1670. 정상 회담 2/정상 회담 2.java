import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static final int MOD = 987654321;
    public static long[] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[N+1];
        Arrays.fill(dp, -1);
        System.out.println(solve(N));
    }

    private static long solve(int num) {
        if(num == 0 || num == 2) return 1;

        if(dp[num] != -1) return dp[num];
        long ret = 0;
        for (int i = 2; i <= num; i += 2) {
            ret = (ret + solve(i - 2) * solve(num - i)) % MOD;
        }
        return dp[num] = ret;
    }
}