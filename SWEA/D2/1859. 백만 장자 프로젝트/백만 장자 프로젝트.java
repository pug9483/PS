import java.io.*;
import java.util.*;


public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Stack<Integer>> list = new ArrayList<>();
	static int N;
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			System.out.printf("#%d %d\n", test_case, solve());
		}
	}

	static long solve() {
		long ret = 0;
		
		int max = 0;
		for(int i = N-1; i >= 0; i--) {
			max = Math.max(max, A[i]);
			if(A[i] < max) {
				ret += 1L*max - A[i];
			}
		}
		return ret;
	}
}