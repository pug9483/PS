import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.Math;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        dp = new int[N+1][N+1];
        for(int i = 0; i < N+1; i++) Arrays.fill(dp[i], -1);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve(0, N-1));
    }   
    
    private static int solve(int left, int right){
        if(left >= right) return 0;
        if(dp[left][right] != -1) return dp[left][right];
        int ret = solve(left+1, right);
        for(int k = left+1; k <= right; k++){
            if(A[left] == A[k])
                ret = Math.max(ret, solve(left+1, k-1) + solve(k+1, right) + 1);
        }        
        return dp[left][right] = ret;
    }
}