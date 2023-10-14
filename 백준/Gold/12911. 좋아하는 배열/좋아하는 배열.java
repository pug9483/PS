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
