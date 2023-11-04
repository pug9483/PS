import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[][] A;
    public static int[][][] dp;
    public static final int INF = 1_000_000_000;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N][M][3]; // 아래쪽(0), 왼쪽(1), 오른쪽(2)
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
        System.out.println(solve(0, 0, 0));
    }
    
    public static int solve(int i, int j, int dir){
        if(i < 0 || i >= N || j < 0 || j >= M) return -INF;
        if(i == N-1 && j == M-1) return A[i][j];
        if(dp[i][j][dir] != Integer.MIN_VALUE) return dp[i][j][dir];
        int ret = A[i][j];
        
        if(dir == 1) ret = Math.max(solve(i+1, j, 0), solve(i, j+1, 1)) + A[i][j];
        else if(dir == 2) ret = Math.max(solve(i+1, j, 0), solve(i, j-1, 2)) + A[i][j];
        else{
            ret = Math.max(ret,  + A[i][j]);
            ret = Math.max(solve(i, j+1, 1), Math.max(solve(i+1, j, 0), solve(i, j-1, 2))) + A[i][j];
        }
        return dp[i][j][dir] = ret;
    }
}