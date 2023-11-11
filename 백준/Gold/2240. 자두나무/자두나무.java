import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int T; // [1, 1000]
    public static int W; // [1, 30]
    public static int[][] dp;
    public static int[] A;
    
    public static void main(String[] args) throws IOException {
       StringTokenizer st = new StringTokenizer(br.readLine());
       T = Integer.parseInt(st.nextToken());
       W = Integer.parseInt(st.nextToken());
       dp = new int[T+1][W+1];
       for(int i = 0; i < T + 1; i++)
           Arrays.fill(dp[i], -1);        

       A = new int[T+1];
       for(int i = 1; i < T+1; i++)
           A[i] = Integer.parseInt(br.readLine());
       
       System.out.println(Math.max(solve(1, 0), solve(1, 1)));
    }
    
    public static int solve(int sec, int turn){
        if(sec == T+1 && turn <= W) return 0;
        if(turn > W) return 0;
        if(dp[sec][turn] != -1) return dp[sec][turn];

        int pos = turn % 2 + 1;
        int num = pos == A[sec] ? 1 : 0;
        dp[sec][turn] = Math.max(solve(sec+1, turn), solve(sec+1, turn+1)) + num;
        return dp[sec][turn];
    }
}
