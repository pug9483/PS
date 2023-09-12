import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, K;
    public static long MOD = 1_000_000_003L;
    public static long[][][] dp;
    
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        dp = new long[3][N+1][N+1];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < N+1; j++)
                Arrays.fill(dp[i][j], -1L);
        long ret = solve(1, 2, 1); // 1 : 0번 색깔 선택, 2: 0번에 색깔 x
        ret = (ret + solve(2, 1, 0)) % MOD;
        System.out.println(ret);
    }
    
    public static long solve(int first, int index, int cnt){
        if(index >= N)  return cnt == K? 1L : 0L;        
        
        if(dp[first][index][cnt] != -1L) return dp[first][index][cnt];
        long ret = 0L;
        
        if(index < N - 1){
            // index번을 선택하는 경우
            ret = (ret + solve(first, index+2, cnt+1)) % MOD;
            // index번을 선택하지 않는 경우
            ret = (ret + solve(first, index+1, cnt)) % MOD;
        }
        else if(index == N - 1){
            if(first == 1L) ret = (ret + solve(first, index+1, cnt)) % MOD;
            else{
                ret = (ret + solve(first, index+2, cnt+1)) % MOD;
                ret = (ret + solve(first, index+1, cnt)) % MOD;
            }
        }
        return dp[first][index][cnt] = ret;
    }
}