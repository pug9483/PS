import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int C; // 늘려야 되는 고객의 수
	private static int N; // 도시의 개수
	private static int[] A; // 각 도시 홍보 비용 배열
	private static int[] B;// 해당 비용으로 얻을 수 있는 고객의 수
	private static final int INF = 987654321;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		A = new int[N];
		B = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1][C+1];
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(dp[i], INF);
		}
		System.out.println(solve(0, 0));
	}

	// here번 인덱스까지의 고객의 수 저장
	private static int solve(int here, int num) {
		if(here == N){
			if(num >= C) return 0;
			return INF;
		}

		if(num >= C) return 0;
		if(dp[here][num] != INF) return dp[here][num];

		int ret = A[here] + solve(here, B[here] + num);
		ret = Math.min(ret, A[here] + solve(here + 1, B[here] + num));
		ret = Math.min(ret, solve(here + 1, num));

		return dp[here][num] = ret;
	}
}
