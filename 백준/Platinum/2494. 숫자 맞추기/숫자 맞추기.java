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
    public static int[][] how;
    public static int[][] to;
    public static int[][] cost;
    public static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        src = br.readLine();
        dst = br.readLine();
        dp = new int[N][10];
        how = new int[N][10];
        to = new int[N][10];
        cost = new int[N][10];
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        System.out.println(solve(0, 0)); 
        reconstruct(0, 0);
        System.out.println(sb);
    }
    
    public static int solve(int here, int turn){
        if(here == N) return 0;
        if(dp[here][turn] != -1) return dp[here][turn];
        int fromSrc = src.charAt(here) - '0';
        fromSrc = (fromSrc + turn) % 10;
        int toDst = dst.charAt(here) - '0';
        int left = (toDst - fromSrc + 10) % 10;
        int L = left + solve(here+1, (turn + left) % 10);
        int right = (fromSrc - toDst + 10) % 10;
        int R = right + solve(here+1, turn);
        if(L < R) {
            how[here][turn] = 1;
            to[here][turn] = (turn + left) % 10;
            cost[here][turn] = left;
        } else{
            how[here][turn] = 2;
            to[here][turn] = turn;
            cost[here][turn] = right;
        }
        int ret = Math.min(L, R);
        return dp[here][turn] = ret;
    }
    
    public static void reconstruct(int here, int turn){
        if(here == N) return;
        if(cost[here][turn] == 0) reconstruct(here+1, turn);
        else{
            sb.append(here+1).append(" ");
            if(how[here][turn] == 2) sb.append("-");
            sb.append(cost[here][turn]).append("\n");
            reconstruct(here+1, to[here][turn]);
        }
    }
}