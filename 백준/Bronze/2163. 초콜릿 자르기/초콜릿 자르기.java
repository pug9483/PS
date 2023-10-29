import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.Math;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[][] dp; // 크기가 A*B인 초콜릿을 나누는 방법 저장
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N+1][M+1];
        for(int i = 0; i < N+1; i++) Arrays.fill(dp[i], -1);
        System.out.println(solve(N, M));
    }   
    
    private static int solve(int w, int h){
        if(w == 1 && h == 1) return 0;
        if(dp[w][h] != -1) return dp[w][h];
        
        int ret = INF;
        if(w > 1) ret = Math.min(ret, solve(w/2, h) + solve((w+1)/2, h) + 1);
        if(h > 1) ret = Math.min(ret, solve(w, h/2) + solve(w, (h+1)/2) + 1);
        return dp[w][h] = ret;
    }
}