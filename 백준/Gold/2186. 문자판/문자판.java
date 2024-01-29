import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int K;
    public static char[][] A;
    public static int[][][] dp;
    public static String s;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for(int i = 0; i < N; i++)
            A[i] = br.readLine().toCharArray();
        dp = new int[N][M][80];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                Arrays.fill(dp[i][j], -1);
        s = br.readLine();
        int ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == s.charAt(0))
                    ret += solve(i, j, 1);
            }
        }
        System.out.println(ret);
    }
    
    public static int solve(int y, int x, int len){
        if(len == s.length()) return 1;
        if(dp[y][x][len] != -1) return dp[y][x][len];
        int ret = 0;
        for(int dir = 0; dir < 4; dir++){
            for(int k = 1; k <= K; k++){
                int ny = y + k * dy[dir];
                int nx = x + k * dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(A[ny][nx] == s.charAt(len))
                    ret += solve(ny, nx, len+1);
            }
        }
        return dp[y][x][len] = ret;
    }
}