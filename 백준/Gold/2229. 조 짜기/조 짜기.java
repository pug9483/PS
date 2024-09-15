import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N];
        Arrays.fill(dp, -1);
        System.out.println(solve(0));
    }

    private static int solve(int here) {
        if(here >= N) return 0;
        if(dp[here] != -1) return dp[here];
        int ret = 0;
        for (int next = here; next < N; next++) {
            ret = Math.max(ret, getDiff(here, next) + solve(next + 1));
        }
        return dp[here] = ret;
    }

    private static int getDiff(int left, int right) {
        int max = A[left];
        int min = A[left];
        for (int k = left; k <= right; k++) {
            max = Math.max(A[k], max);
            min = Math.min(A[k], min);
        }
        return max - min;
    }
}