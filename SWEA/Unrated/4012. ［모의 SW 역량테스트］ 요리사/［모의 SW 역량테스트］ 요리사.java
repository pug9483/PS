import java.util.*;
import java.io.*;

public class Solution {	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] A;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.printf("#%d %d\n", t, solve(-1, 0, 0));
		}
	}
	
	public static int solve(int idx, int cnt, int set) {
		if(cnt == N / 2) return compute(set);
		
		int ret = INF;
		for(int next = idx+1; next < N; next++) {
			ret = Math.min(ret, solve(next, cnt+1, set | (1 << N - 1 - next)));
		}
		
		return ret;
	}
	
	public static int compute(int set) {
		int a = 0;
		int b = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if((set & (1 << i)) > 0 && (set & (1 << j)) > 0) {
					a += A[i][j];
				}
				else if((set & (1 << i)) == 0 && (set & (1 << j)) == 0) {
					b += A[i][j];
				}
			}
		}
			
		return Math.abs(a - b);
	}
}
