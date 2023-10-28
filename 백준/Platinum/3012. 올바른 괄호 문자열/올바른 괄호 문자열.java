import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String S;
    public static String A = "({[";
    public static String B = ")}]";
    public static final long MOD = 100000;
    public static long[][] dp;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = br.readLine();
        dp = new long[N+1][N+1];
        for(int i = 0; i < N+1; i++) Arrays.fill(dp[i], -1);
        String ret = String.valueOf(solve(0, N-1));
        if(ret.length() >= 5) System.out.println(ret.substring(ret.length()-5, ret.length()));
        else System.out.println(ret);
    }
    
    public static long solve(int left, int right){
        if(left > right) return 1;
        if(dp[left][right] != -1) return dp[left][right];
        long ret = 0;
        char lc = S.charAt(left);
        for(int i = left+1; i <= right; i+=2){
            char c = S.charAt(i);
            for(int k = 0; k < 3; k ++){
                if((lc == A.charAt(k) || lc == '?') && (c == B.charAt(k) || c == '?')){
                    ret = 1L*ret + solve(left+1, i-1)*solve(i+1, right);
                    if(ret >= MOD) ret = MOD + ret % MOD;
                }
            }
        }
        return dp[left][right] = ret;
    }
}