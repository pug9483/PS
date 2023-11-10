import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][][] dp = new int[15][30][30];
    public static final int[] dy = {1, -1, 0, 0, 1, -1};
    public static final int[] dx = {0, 0, 1, -1, 1, -1};
    
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < 15; i++)
            for(int j = 0; j < 30; j++)
                Arrays.fill(dp[i][j], -1);
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            System.out.println(solve(N, 15, 15));
        }
    }
    
    public static int solve(int here, int y, int x){
        if(here == 0) {
            if(y == 15 && x == 15) return 1;
            return 0;
        }
        if(dp[here][y][x] != -1) return dp[here][y][x];
        
        int ret = 0;
        
        for(int dir = 0; dir < 6; dir++){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            ret += solve(here-1, ny, nx);
        }
        
        return dp[here][y][x] = ret;
    }
}