import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static char[] A;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		A = br.readLine().toCharArray(); 
		
		System.out.println(solve());
	}

	private static int solve() {
		int ret = 0;
		Stack<Character> Lstack = new Stack<>();
		Stack<Character> Sstack = new Stack<>();
		
		for(char c : A) {
			if(c >= '1' && c <= '9') {
				ret++;
			}
			else if(c == 'R') {
				if(Lstack.isEmpty()) return ret;
				ret++;
				Lstack.pop();
			}
			else if(c == 'K') {
				if(Sstack.isEmpty()) return ret;
				ret++;
				Sstack.pop();
			}
			else if(c == 'L') Lstack.add(c);
			else if(c == 'S') Sstack.add(c); 
			
		}
		
		return ret;
	}
}
