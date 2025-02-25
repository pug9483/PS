import java.util.*;
import java.io.*;

public class Solution {	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] A;
	static int[] ops; // +, -, *, /
	static int INF = 987654321;
	static int minRet;
	static int maxRet;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			ops = new int[4];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			
			minRet = INF;
			maxRet = -INF;
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			solve(0, 0);
			System.out.printf("#%d %d\n", t, maxRet - minRet);
		}
	}
	
	public static void solve(int idx, long set) {
		if(idx == N-1) {
			int ret = compute(set);
			minRet = Math.min(minRet, ret);
			maxRet = Math.max(maxRet, ret);
			return;
		}

		for(int op = 0; op < 4; op++) {
			if(ops[op] <= 0) continue;
			ops[op]--;
			solve(idx+1, set * 10 + (op+1));
			ops[op]++;
		}
	}
	
	public static int compute(long set) {
		int k = 0;
		int ret = A[k++];
		for(int i = 0, j = N-2; i < N-1; i++, j--) {
			
			long digit = (long) Math.pow(10, j);
			int op = (int) (set / digit);
			set -= op * digit;
			
			if(op == 1) ret += A[k++];
			else if(op == 2) ret -= A[k++];
			else if(op == 3) ret *= A[k++];
			else ret /= A[k++];
		}
		
		return ret;
	}
}
