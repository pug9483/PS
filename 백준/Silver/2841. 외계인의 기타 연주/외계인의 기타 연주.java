import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Stack<Integer>> list = new ArrayList<>();
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 6; i++) {
			list.add(new Stack<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int ret = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken()) - 1;
			int plat = Integer.parseInt(st.nextToken());
			ret += solve(list.get(index), plat);
		}
		
		System.out.println(ret);
	}
	
	public static int solve(Stack<Integer> stack, int plat) {
		int ret = 0;
		
		while(!stack.isEmpty()) {
			int top = stack.peek();
			if(top > plat) {
				stack.pop();
				ret++;
			}
			else break;
		}
		
		if(stack.isEmpty()) {
			ret++;
			stack.add(plat);
		}
		else {
			if(stack.peek() == plat) {
				return ret;
			}
			else {
				ret++;
				stack.add(plat);
			}
		}
		return ret;
	}
}