import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N = 12;
    static int[] prices = new int[4];
    static int[] A; // 각 달의 이용계획 개수
    static int[] dp;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            A = new int[N];
            dp = new int[N];
            Arrays.fill(dp, 987654321);
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            System.out.printf("#%d %d\n", t, Math.min(prices[3], solve(0)));
        }
    }

    public static int solve(int here) {
        if(here >= N) return 0;
        if(dp[here] != INF) return dp[here];

        int ret = Math.min(A[here] * prices[0], prices[1]) + solve(here+1);
        ret = Math.min(ret, prices[2] + solve(here + 3));

        return dp[here] = ret;
    }
}
