/* dp[N][p1][p2][l]: N번째 글자까지로 만들 수 있는 즐거운 단어의 개수
 * p1: 전전 글자, p2: 전글자, l: L이 나왔는지의 여부
 * 모음은 1, 자음은 2로 바꾸어 수행한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static char[] A;
    public static int N;
    public static long[][][][] dp = new long[111][3][3][2];
    public static final int[] ways = {5, 20};
    
    public static void main(String[] args) throws IOException{
        A = br.readLine().toCharArray();
        N = A.length;
        for(int i = 0; i < 111; i++)
            for(int j = 0; j < 3; j++)
                for(int k = 0; k < 3; k++)
                    Arrays.fill(dp[i][j][k], -1);
        System.out.println(solve(0, 0, 0, 0));
    }
    
    public static long solve(int here, int p1, int p2, int l){        
        if(here == N) return l == 1? 1 : 0;
        if(dp[here][p1][p2][l] != -1) return dp[here][p1][p2][l];
        
        long ret = 0;
        char ch = A[here];
        if(ch != '_'){
            if(p1 == p2 && p2 == what(ch)) return 0; // 3개 연속 자음 또는 3개 연속 모음
            return solve(here+1, p2, what(ch), (l | (ch == 'L' ? 1 : 0)));
        }
        for(int j = 1; j <= 2; j++){ // 모음/자음 추가
            if(p1 == p2 && p2 == j) continue;
            ret += ways[j-1] * solve(here+1, p2, j, l);
        }
        if(!(p1 == p2 && p2 == 2)) // L 추가
            ret += solve(here+1, p2, 2, 1);
        
        return dp[here][p1][p2][l] = ret;
    }
    
    public static int what(char x){
        if(x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U') return 1;
        return 2;
    }
}
