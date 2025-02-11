import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, L;
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		System.out.println(solve());
	}
	
	static int solve() {
		int ret = 0;
		
		int idx = 0;
		
		while(true) {
			A[idx]++;
			if(A[idx] == M) break;
			ret++;
			
			if(A[idx] % 2 == 1) { // A[idx]가 홀수이면 시계 방향 L 번째
				idx = (idx + L) % N;
			}
			else { 			// A[idx]가 짝수이면 반시계 방향 L번째
				idx = (idx + N - L) % N;
			}
		}
		
		
		return ret;
	}
}