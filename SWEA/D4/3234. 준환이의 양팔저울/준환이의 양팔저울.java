import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			A = new int[N];
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			} 
			System.out.printf("#%d %d\n", t, solve(new ArrayList<>(), new boolean[N]));
		}
	}

	
	public static int solve(List<Integer> list, boolean[] visited) {
		if(list.size() == N) {
			return helper(0, 0, 0, list);
		}
		
		int ret = 0;
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			list.add(A[i]);
			visited[i] = true;
			ret += solve(list, visited);
			list.remove(list.size()-1);
			visited[i] = false;
		}
		
		return ret;
	}
	
	public static int helper(int idx, int lSum, int rSum, List<Integer> list) {
		if(idx == list.size()) return 1;
		int ret = 0;
		
		int num = list.get(idx);
	
		ret += helper(idx+1, lSum + num, rSum, list);
		if(lSum >= rSum + num) {
			ret += helper(idx+1, lSum, rSum + num, list);	
		}
		return ret;
	}
}