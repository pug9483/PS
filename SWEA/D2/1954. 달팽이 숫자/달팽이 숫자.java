import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			sb.append("#").append(t).append("\n");
			solve();
		}
		System.out.println(sb);
	}
	
	public static void solve() {
		int[][] A = new int[N][N];
		helper(0, 0, A, N, 1);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(A[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
	
	public static void helper(int sy, int sx, int[][] A, int size, int num) {
		if(size == 0) return;
		if(size == 1) {
			A[sy][sx] = num;
			return;
		}
		
		for(int x = sx; x < sx + size - 1; x++) {
			A[sy][x] = num++;
		}
		
		for(int y = sy; y < sy + size - 1; y++) {
			A[y][sx + size - 1] = num++;
		}
		
		for(int x = sx + size - 1; x > sx; x--) {
			A[sy + size - 1][x] = num++;
		}
		
		for(int y = sy + size - 1; y > sy; y--) {
			A[y][sx] = num++;
		}
		
		helper(sy + 1, sx + 1, A, size - 2, num);
	}
}