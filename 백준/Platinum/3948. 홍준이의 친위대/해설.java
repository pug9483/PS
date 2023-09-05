/*
Tall[N] = 1번 사람 > 2번 사람으로 줄을 서는 경우의 수
Short[N] = 1번 사람 < 2번 사람으로 줄을 서는 경우의 수
Tall[N] = Short[N]

Tall[0] = 1, Tall[1] = Tall[2] = 1
Tall[N]을 구하기 위해 가장 키가 큰 사람의 위치를 k라고 하자.
-> K를 기준으로 왼쪽과 오른쪽으로 나누어진다. (왼쪽: Tall[k-1], 오른쪽: Short[N-k])
=> Tall[N] = Tall[K-1] * Short[N-K] * (n-1)C(k-1)

(정답)
if n == 1: Tall[N]
else Tall[N] + Short[N] = 2 * Tall[N]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    
    public static long[][] C = new long[21][21];
    public static long[] dp = new long[21];
    
    public static void comb(){
        for(int i = 0; i < 21; i++)
            C[i][i] = C[i][0] = 1L;
        for(int i = 1; i < 21; i++){
            for(int j = 1; j < i; j++)
                C[i][j] = C[i-1][j-1] + C[i-1][j];
        }
    }
    
    public static long go(int n) {
        if(n >= 0 && n <= 2) return 1L;
        if (dp[n] != -1L) return dp[n];
        long ans = 0L;
        for (int i = 1; i <= n; i += 2) 
            ans += C[n-1][i-1] * go(i-1) * go(n-i);
        return dp[n] = ans;
    }
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());   
        Arrays.fill(dp, -1);
        comb();
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            long ret = N == 1? 1L : 2L * go(N);
            sb.append(ret).append("\n");
        }
        System.out.print(sb);
    }
}
