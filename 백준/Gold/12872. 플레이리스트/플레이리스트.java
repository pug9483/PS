import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int P;
    public static long[][] dp;
    public static final int MOD = 1_000_000_007;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dp = new long[P+1][P+1];
        for(int i = 0; i < P+1; i++)
            Arrays.fill(dp[i], -1);
        System.out.println(solve(0, 0));
    }
    
    public static long solve(int len, int picked){
        int left = N - picked;
        if(len == P) return  left == 0? 1 : 0;
        long ret = 0;
        if(dp[len][picked] != -1) return dp[len][picked];
        if(left > 0) ret = (ret + solve(len+1, picked+1) * left) % MOD;
        if(left > M) ret = (ret + solve(len+1, picked) * (left - M)) % MOD;
        
        return dp[len][picked] = ret;
    }
}