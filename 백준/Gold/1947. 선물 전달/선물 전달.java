import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
    public static long[] dp;
    public static int MOD =  1_000_000_000;
    
    // dp[i]: 1. i와 j(<i)가 서로 선물을 맞교환한다. -> dp[i-2]
    //        2. i와 j가 서로 선물을 맞교환하지 않는다. -> dp[i-1]
    // 위와 같은 방법이 총 i-1개가 존재하므로 dp[i] = (dp[i-2] + dp[i-1]) * (i-1)이다.
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[4];
        dp[2] = 1;
        for(int i = 3; i <= N; i++)
            dp[i%4] = ((dp[(i-2)%4] + dp[(i-1)%4]) * (i-1)) % MOD;
        System.out.println(dp[N%4]);
    }
}