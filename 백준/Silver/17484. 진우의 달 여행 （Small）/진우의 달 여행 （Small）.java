import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[][] A;
    public static int[][][] dp;
    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        dp = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ret = INF;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        for (int j = 0; j < M; j++) 
            ret = Math.min(ret, go(0, j, 0));
        System.out.println(ret);
    }

    public static int go(int i, int j, int dir) {
        if(i == N) return 0;
        if(j < 0 || j >= M) return INF;
        if(dp[i][j][dir] != -1) return dp[i][j][dir];
        int ret = INF;
        for (int k = 1; k <= 3; k++) {
            if(dir == k) continue;
            ret = Math.min(ret, A[i][j] + go(i + 1, j + k - 2, k));
        }
        return dp[i][j][dir] = ret;
    }
}