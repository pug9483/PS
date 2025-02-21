import java.util.*;
import java.io.*;

public class Solution {
	static int N = 8;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		for(int t = 1; t <= 10; t++) {
			
			int[] A = new int[N];
			sc.nextInt();
			for(int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
			}
			
			sb.append("#").append(t).append(" ");
			solve(A);
		}
		System.out.print(sb);
	}
	
	static void solve(int[] A) {
		int start = 0;
		while(true) {
			boolean finish = false;
			
			for(int num = 1; num <= 5; num++) {
				A[start] = Math.max(0, A[start] - num);
				if(A[start] == 0) {
					finish = true;
					break;
				}
				start = (start + 1) % N;
			}
			
			if(finish) break;
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(A[(start + 1 + i) % N]).append(" ");
		}
		sb.append("\n");
	}
}
