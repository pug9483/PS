import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.Math;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;    
    private static long[] dp;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        N = Integer.parseInt(st.nextToken());
        dp = new long[N+1];
        Arrays.fill(dp, -1);
        System.out.println(solve(N));   
    }   
    
    public static long solve(int here){
        if(here == 0) return 0;
        if(dp[here] != -1) return dp[here];
        long ret = solve(here-1) + 1;
        
        for(int i = here-3; i >= 0; i--)
            ret = Math.max(ret, solve(i) * (here-i-1));
        return dp[here] = ret;   
    }
}