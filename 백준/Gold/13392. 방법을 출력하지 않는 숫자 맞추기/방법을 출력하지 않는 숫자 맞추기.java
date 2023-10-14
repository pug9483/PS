import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String src;
    public static String dst;
    public static int[][] dp;
    public static Map<String, Integer> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        src = br.readLine();
        dst = br.readLine();
        dp = new int[N][10];
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        System.out.println(solve(0, 0));
    }
    
    public static int solve(int here, int turn){
        if(here == N) return 0;
        if(dp[here][turn] != -1) return dp[here][turn];
        int from = src.charAt(here) - '0';
        from = (from + turn) % 10;
        int to = dst.charAt(here) - '0';
        int left = (to - from + 10) % 10;
        int ret = left + solve(here+1, (turn + left) % 10);
        int right = (from - to + 10) % 10;
        ret = Math.min(ret, right + solve(here+1, turn));
        return dp[here][turn] = ret;
    }
}