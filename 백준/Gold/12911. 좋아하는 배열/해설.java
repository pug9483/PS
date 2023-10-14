/*
조건1: 길이가 N
조건2: 배열은 1부터 K까지의 수로 채움
조건3: 연속한 수가 A,B일 때, A <= B 또는 A % B != 0

방법1.
D[N][A] = 길이가 N, 첫 수가 A인 배열의 개수
D[N][A] = sum(D[N-1][B]) (B가 A보다 크거나 B가 A의 약수가 아닐 때)
시간복잡도: O(n*k*k) (시간초과)

방법2.
D[N][A] = sum(D[N-1][C]) - sum(D[N-1][B]) (1<=C<=A, B는 A의 약수)
시간복잡도: O(N*K*약수의개수)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, K;
    public static final int MOD = 1_000_000_007;
    public static ArrayList<Integer>[] list;
    public static long[] sums;
    public static long[][] dp;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new long[N+1][K+1];
        sums = new long[N+1];
        list = new ArrayList[K+1];
        for(int i = 0; i < K+1; i++) list[i] = new ArrayList<>();
        // 약수 구하기
        getDivisions();
        System.out.println(solve());
    }
    
    public static void getDivisions(){
        for(int i = 1; i <= K; i++){
            for(int j = i*2; j <= K; j+=i){
                list[j].add(i);
            }
        }
    }
    
    public static long solve(){
        for(int i = 1; i <= K; i++) dp[1][i] = 1;
        sums[1] = K;
        for(int i = 2; i <= N; i++){
            for(int j = 1; j <= K; j++){
                dp[i][j] = sums[i-1]; // D[i-1][c]
                for(int div: list[j])
                    dp[i][j] = (dp[i][j] - dp[i-1][div] + MOD) % MOD;
                sums[i] = (sums[i] + dp[i][j]) % MOD;
            }
        }
        return sums[N];
    }
}
