import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N; // 재료의 수
	static int L; // 최대 칼로리의 수
	static int[] A; // 점수
	static int[] B; // 칼로리
	static int ret;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			A = new int[N];
			B = new int[N];
			ret = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			solve(-1, 0, 0);
			System.out.printf("#%d %d\n", t, ret);
		}
	}
	
	private static void solve(int here, int sum, int calory) {
		if(calory <= L)
			ret = Math.max(ret, sum);
		
		for(int next = here + 1; next < N; next++) {
			solve(next, sum + A[next], calory + B[next]);
		}
	}
}
