import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Stack<Integer>> list = new ArrayList<>();
	static int N;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // Command의 수
			
			int velocity = 0;
			int ret = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				if(command == 0) {
					ret += velocity;
				}
				else if(command == 1) {
					int v = Integer.parseInt(st.nextToken());
					velocity += v;
					ret += velocity;
				}
				else if(command == 2) {
					int v = Integer.parseInt(st.nextToken());
					velocity = Math.max(0, velocity - v);
					ret += velocity;
				}
			}	
			System.out.printf("#%d %d\n", test_case, ret);
		}
	}
}