import java.io.*;
import java.util.*;

public class Main {           
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] board;
    public static int M;
    public static int N;
    public static int[][] memo;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N];
        memo = new int[N+1][M+2];
        for(int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        for(int i = 0; i < N; i++)
            board[i] = Integer.parseInt(br.readLine());
        System.out.println(solve(0, 0));
    }
    
    public static int solve(int here, int w){
        if(here == N) return 0;
        if(memo[here][w] != -1) return memo[here][w];
        
        int left = M - w + 1;
        int ret = solve(here+1, board[here] + 1) + (left * left);
        
        if(w + board[here] <= M)
            ret = Math.min(ret, solve(here+1, w + board[here] + 1));
        
        memo[here][w] = ret;
        return ret;
    }
    
}