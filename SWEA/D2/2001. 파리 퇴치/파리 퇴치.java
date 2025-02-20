import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N; // 세로, 가로
	static int M; // 파리채 크기
	static int[][] A;
	static int[][] psum;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			A = new int[N+1][N+1];
			psum = new int[N+1][N+1];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					A[i+1][j+1] = Integer.parseInt(st.nextToken());
				}
			}
			makePsum();
			System.out.printf("#%d %d\n", t, solve());
		}		
	}
	
	static void makePsum() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				psum[i][j] = A[i][j] + psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1];
			}
		}
	}
	
	private static int solve() {
		int ret = 0;
		
		for(int i = M; i <= N; i++) {
			for(int j = M; j <= N; j++) {
				ret = Math.max(ret, psum[i][j] - psum[i-M][j] - psum[i][j-M] + psum[i-M][j-M]);
			}
		}
		
		return ret;
	}
}