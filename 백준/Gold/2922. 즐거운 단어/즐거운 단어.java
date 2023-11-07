import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static char[] A;
    public static int N;
    public static long[][][][] dp;
    
    public static void main(String[] args) throws IOException{
        A = br.readLine().toCharArray();
        N = A.length;
        dp = new long[N+1][3][3][2];
        for(int i = 0; i < N+1; i++)
            for(int j = 0; j < 3; j++)
                for(int k = 0; k < 3; k++)
                    Arrays.fill(dp[i][j][k], -1);
        System.out.println(solve(0, 0, 0, 0));
    }
    
    // dp[here][a][b][c]
    // here: 위치
    // a : 이전에 연속된 자음의 개수
    // b : 이전에 연속된 모음의 개수
    // c : L의 유무
    public static long solve(int here, int a, int b, int c){        
        if(here == N){
            return c == 1? 1 : 0;
        }
        
        if(dp[here][a][b][c] != -1) return dp[here][a][b][c];
        long ret = 0;
        char ch = A[here];
        if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'){
            if(b < 2) ret += solve(here+1, 0, b+1, c);
        }
        else if(ch == 'L'){
            if(a < 2) ret += solve(here+1, a+1, 0, 1);
        }
        else if(ch == '_'){
            if(a < 2){
                ret += solve(here+1, a+1, 0, 1); // L일 경우
                ret += 20 * solve(here+1, a+1, 0, c); // 그 외 자음
            }
            if(b < 2) ret += 5 * solve(here+1, 0, b+1, c);
        }
        else if(a < 2) ret += solve(here+1, a+1, 0, c);
        
        return dp[here][a][b][c] = ret;
    }
}