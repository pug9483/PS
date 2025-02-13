import java.util.Scanner;
import java.util.*;

public class Main {
	static int MOD = 1_000_000_007;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		System.out.println(solve(D));
	}

	
	public static int solve(int D) {
		int[][] dp = new int[8][D+1];
		dp[0][0] = 1;
		
		for(int d = 1; d <= D; d++) {
			dp[0][d] = (dp[1][d-1] + dp[2][d-1]) % MOD;
			dp[1][d] = ((dp[0][d-1] + dp[2][d-1]) % MOD + dp[3][d-1]) % MOD;
			dp[2][d] = ((dp[0][d-1] + dp[1][d-1]) % MOD + (dp[3][d-1] + dp[4][d-1]) % MOD) % MOD;
			dp[3][d] = ((dp[1][d-1] + dp[2][d-1]) % MOD + (dp[4][d-1] + dp[5][d-1]) % MOD) % MOD;
			dp[4][d] = ((dp[2][d-1] + dp[3][d-1]) % MOD + (dp[5][d-1] + dp[6][d-1]) % MOD) % MOD;
			dp[5][d] = ((dp[3][d-1] + dp[4][d-1]) % MOD + dp[7][d-1]) % MOD;
			dp[6][d] = (dp[4][d-1] + dp[7][d-1]) % MOD;
			dp[7][d] = (dp[5][d-1] + dp[6][d-1]) % MOD;
		}
		
		return dp[0][D];
	}
}