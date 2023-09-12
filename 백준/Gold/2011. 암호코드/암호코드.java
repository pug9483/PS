import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String s;
    public static int N;
    public static int MOD = 1000000;
    public static int[] dp;
    
    public static void main(String[] args) throws IOException{
        s = br.readLine();
        N = s.length();
        dp = new int[N+1];
        Arrays.fill(dp, -1);
        System.out.println(solve(0));
    }
    
    public static int solve(int index){
        if(index >= N) return 1;
        if(dp[index] != -1) return dp[index];
        int ret = 0;
        
        int here = (int)(s.charAt(index) - '0');
        if(here == 0) return 0;
        if(index < N - 1){
            int next = (int)(s.charAt(index+1) - '0');
            if(check(here, next)){
                ret = (ret + solve(index + 2)) % MOD;
            }   
        }
        ret = (ret + solve(index + 1)) % MOD;
        return dp[index] = ret;
    }
    
    public static boolean check(int here, int next){
        if(here == 1 && next >= 0 && next <= 9) return true;
        if(here == 2 && next >= 0 && next <= 6) return true;
        return false;
    }
}