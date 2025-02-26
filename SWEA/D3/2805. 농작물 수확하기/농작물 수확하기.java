import java.util.*;
import java.io.*;

public class Solution {	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] A;
	static int INF = 987654321;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[][] pSum;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N][N];
			pSum = new int[N][N+1];
			
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < N; j++) {
					A[i][j] = s.charAt(j) - '0';
					pSum[i][j+1] = pSum[i][j] + A[i][j];
				}
			}
			System.out.printf("#%d %d\n", t, solve());
		}
	}	
	
	public static int solve() {
		int ret = 0;
		
		int size = 0;
		for(int y = 0; y < N; y++) {
			ret += pSum[y][(N/2) + size + 1] - pSum[y][(N/2-size)];
			size = y < N/2 ? size + 1 : size - 1;
		}
		return ret;
	}
}