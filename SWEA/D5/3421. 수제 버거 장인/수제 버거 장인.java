import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N; // 재료의 개수
	static int M; // 서로 맞지 않는 재료 쌍의 개수
	static int[] A; // A[i] : i번째 재료와 같이 저장할 수 없는 재료들을 bit로 저장
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				A[u] = (A[u] & (1 << v)) == 0 ? A[u] + (1 << v) : A[u];
				A[v] = (A[v] & (1 << u)) == 0 ? A[v] + (1 << u) : A[v];
			}
			
//			for(int i = 0; i < N; i++) {
//				System.out.println(Integer.toBinaryString(A[i]));	
//			}
			sb.append(solve(0, 0)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	
	public static int solve(int idx, int set) {
		if(idx == N) {
			return 1;
		}
		
		int ret = solve(idx+1, set);
		if((A[idx] & set) == 0) ret += solve(idx + 1, (1 << idx) | set);
		
		return ret;
	}
}
