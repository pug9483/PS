import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int c1, c2, c3;
    public static long[] fact = new long[11];
    public static long[][][][] dp;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        c3 = Integer.parseInt(st.nextToken());
        dp = new long[N+1][c1+1][c2+1][c3+1];
        for(int i = 0; i < N+1; i++)
            for(int j = 0; j < c1+1; j++)
                for(int k = 0; k < c2+1; k++)
                    Arrays.fill(dp[i][j][k], -1);
        getFactorial();
        System.out.println(solve(1, c1, c2, c3));
    }
    
    public static long solve(int here, int c1, int c2, int c3){
        if(here == N+1) return 1;
        if(dp[here][c1][c2][c3] != -1) return dp[here][c1][c2][c3];
        long ret = 0;
        // c1만 사용
        if(c1 >= here) ret += solve(here+1, c1-here, c2, c3);
        // c2만 사용
        if(c2 >= here) ret += solve(here+1, c1, c2-here, c3);
        // c3만 사용
        if(c3 >= here) ret += solve(here+1, c1, c2, c3-here);
        
        if(here >= 2 && here % 2 == 0){ // 레벨이 짝수일 경우에만 혼합 가능
            // c1, c2만 사용
            if(c1 >= here / 2 && c2 >= here / 2 && check(c1, c2))
                ret += fact[here] / (fact[here/2] * fact[here/2]) * solve(here+1, c1 - here/2, c2 - here/2, c3);
            // c1, c3만 사용
            if(c1 >= here / 2 && c3 >= here / 2 && check(c1, c3))
                ret += fact[here] / (fact[here/2] * fact[here/2]) * solve(here+1, c1 - here/2, c2, c3 - here/2);
            // c2, c3만 사용
            if(c2 >= here / 2 && c3 >= here / 2 && check(c2, c3))
                ret += fact[here] / (fact[here/2] * fact[here/2]) * solve(here+1, c1, c2 - here/2, c3 - here/2);
        }
        // c1, c2, c3 사용
        if(here >= 3 && here % 3 == 0 && c1 >= here / 3 && c2 >= here / 3 && c3 >= here / 3 && check(c1, c2) && check(c2, c3))
            ret += fact[here] / (fact[here/3] * fact[here/3] * fact[here/3]) * solve(here+1, c1 - here/3, c2 - here/3, c3 - here/3);
        return dp[here][c1][c2][c3] = ret;
    }
    
    public static boolean check(int a, int b){
        return a > 0 && b > 0;
    }
    
    public static void getFactorial(){
        fact[1] = 1;
        for(int i = 2; i <= 10; i++)
            fact[i] = fact[i-1] * i;
    }
}
