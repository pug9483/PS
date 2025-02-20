import java.util.*;
import java.io.*;

public class Solution {
	static class Node{
		int sum, calory;
		
		public Node(int sum, int calory) {
			this.sum = sum;
			this.calory = calory;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N; // 재료의 수
	static int L; // 최대 칼로리의 수
	static List<Node> list;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int sum = Integer.parseInt(st.nextToken());
				int calory = Integer.parseInt(st.nextToken());
				list.add(new Node(sum, calory));
			}
			
			Collections.sort(list, (o1, o2) -> Integer.compare(o1.calory, o2.calory));
			System.out.printf("#%d %d\n", t, solve(-1, 0));
		}
	}
	
	private static int solve(int here, int calory) {
		int ret = 0;
		
		for(int next = here + 1; next < N; next++) {
			int nextCalory = calory + list.get(next).calory;
			if(nextCalory > L) break;
			ret = Math.max(ret, list.get(next).sum + solve(next, nextCalory));
		}
		
		return ret;
	}
}
