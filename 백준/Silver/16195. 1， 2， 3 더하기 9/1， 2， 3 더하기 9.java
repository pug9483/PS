import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int M;
    public static int[][] memo = new int[1001][1001];
    public static int[] nums = {1, 2, 3};
    public static final int MOD = 1_000_000_009;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());   
        for(int i = 0; i < 1001; i++) Arrays.fill(memo[i], -1);
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());   
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            sb.append(solve(N, M)).append("\n");
        }
        System.out.print(sb);
    }
    
    public static int solve(int N, int M){
        if(M < 0 || N < 0) return 0;
        if(N == 0) return 1;
        
        if(memo[N][M] != -1) return memo[N][M];
        int ret = 0;
        for(int num: nums){
            ret = (ret + solve(N - num, M - 1)) % MOD;
        }
        memo[N][M] = ret;
        return ret;
    }
}