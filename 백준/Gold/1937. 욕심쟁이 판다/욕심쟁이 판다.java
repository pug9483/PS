import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] A;
    public static int[][] memo;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());   
        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());   
            for(int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        memo = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        int ret = -INF;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                ret = Math.max(ret, solve(i, j));
        System.out.println(ret);
    }
    
    public static int solve(int y, int x){
        if(memo[y][x] != -1) return memo[y][x];
        int ret = 1;
        for(int dir = 0; dir < 4; dir++){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(check(ny, nx) || A[ny][nx] <= A[y][x]) continue;
            ret = Math.max(ret, 1 + solve(ny, nx));
        }
        memo[y][x] = ret;
        return ret;
    }
    
    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}
