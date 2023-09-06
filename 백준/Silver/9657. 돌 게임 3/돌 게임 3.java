import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] dp;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        Arrays.fill(dp, -1);
        System.out.println(solve(N) == 1 ? "SK" : "CY");
    }
    
    // 이기면 1반환, 지면 -1 반환
    public static int solve(int N){
        if(N == 2) return -1;
        if(N == 1 || N == 3 || N == 4) return 1;
        if(dp[N] != -1) return dp[N];
        int ret = Math.min(solve(N-1), solve(N-3));
        ret = Math.min(ret, solve(N-4));
        dp[N] = ret == -1 ? 1 : -1;
        return dp[N];
    }
}