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