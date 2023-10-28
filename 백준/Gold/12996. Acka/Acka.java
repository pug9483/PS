import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.Math;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;    
    private static int[][][][] dp;
    private static final int MOD = 1_000_000_007;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        dp = new int[N][A+1][B+1][C+1];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < A+1; j++)
                for(int k = 0; k < B+1; k++)
                    Arrays.fill(dp[i][j][k], -1);
        System.out.println(solve(0, A, B, C));
    }   
    
    private static int solve(int here, int a, int b, int c){
        if(here == N){
            if(a == 0 && b == 0 && c == 0) return 1;    
            return 0;
        }
        if(a < 0 || b < 0 || c < 0) return 0;
        if(dp[here][a][b][c] != -1) return dp[here][a][b][c];
        int ret = 0;
        
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    if(i+j+k == 0) continue;
                    ret = (ret + solve(here+1, a-i, b-j, c-k)) % MOD;
                }
            }
        }
        return dp[here][a][b][c] = ret;
    }
}