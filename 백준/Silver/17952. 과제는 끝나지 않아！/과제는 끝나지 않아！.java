import java.io.*;
import java.util.*;

public class Main {
	static class Work{
		int score;
		int workingTime;
		
		public Work(int score, int workingTime) {
			this.score = score;
			this.workingTime = workingTime;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Stack<Work> list = new Stack<>();
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		list.add(null);
		
		for(int t = 1; t <= N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 0) {
				list.add(null);
			}
			else {
				int score = Integer.parseInt(st.nextToken());
				int workingTime = Integer.parseInt(st.nextToken());
				list.add(new Work(score, workingTime));
			}
		}
		System.out.println(solve());
		
	}
	private static int solve() {
		int ret = 0;
		Stack<Work> stack = new Stack<>();
		
		for(int time = 1; time <= N; time++) {
			if(list.get(time) == null) {
				if(!stack.isEmpty()) {
					Work prev = stack.pop();
					int nextWorkingtime = prev.workingTime - 1;
					if(nextWorkingtime == 0) ret += prev.score;
					else stack.add(new Work(prev.score, nextWorkingtime));
				}
			}
			else {
				Work here = list.get(time);
				int nextWorkingtime = here.workingTime - 1;
				if(nextWorkingtime == 0) ret += here.score;
				else stack.add(new Work(here.score, here.workingTime - 1));
			}
		}
		
		return ret;
	}
	
}
