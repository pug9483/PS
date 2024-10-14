import java.io.*;
import java.util.*;

// 문제 H : 단어 접두사 분석 시스템
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int[] A;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 팔굽혀펴기 횟수
            M = Integer.parseInt(st.nextToken()); // 경기에서 나올 수 있는 득점의 종류
            dp = new boolean[N+1][N+1];

            A = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) A[i] = Integer.parseInt(st.nextToken());

            solve(0, 0);
            int ret = -1;
            for(int i = 1; i <= N; i++) if(dp[N][i]) ret = i;
            sb.append(ret).append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(int sum, int num) {
        if(sum > N) return;
        if(dp[sum][num]) return;
        dp[sum][num] = true;

        for (int i = 0; i < M; i++) {
            solve(sum + num + A[i], num + A[i]);
        }
    }
}