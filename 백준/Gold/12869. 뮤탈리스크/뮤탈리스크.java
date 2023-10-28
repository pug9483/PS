import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.Math;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;    
    private static int[] A = new int[3];
    private static int[][][] dp = new int[61][61][61];
    private static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 61; i++)
            for(int j = 0; j < 61; j++)
                Arrays.fill(dp[i][j], -1);
        int ret = solve(A[0], A[1], A[2]);
        System.out.println(ret == -INF ? - 1 : ret);
    }
    
    private static int solve(int a, int b, int c){
        if(a < 0) return solve(0, b, c);
        if(b < 0) return solve(a, 0, c);
        if(c < 0) return solve(a, b, 0);
        if(a == 0 && b == 0 && c == 0) return 0;
        int ret = INF;
        
        if(dp[a][b][c] != -1) return dp[a][b][c];
        
        ret = Math.min(ret, solve(a-9, b-3, c-1));
        ret = Math.min(ret, solve(a-9, c-3, b-1));
        ret = Math.min(ret, solve(b-9, c-3, a-1));
        ret = Math.min(ret, solve(b-9, a-3, c-1));
        ret = Math.min(ret, solve(c-9, a-3, b-1));
        ret = Math.min(ret, solve(c-9, b-3, a-1));
        ret++;
        
        return dp[a][b][c] = ret;
    }
}