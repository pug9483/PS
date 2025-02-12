import java.util.Scanner;
import java.util.*;

/**
 * 두께 D, 가로크기 W인 보호 필름 단면의 정보와 합격기준 K가 주어졌을 때, 약품 투입 횟수를 최소로 하여 성능검사를 통과할 수 있는 방법을 찾고,
 * 이때의 약품 투입 횟수를 출력하라.
 */
public class Solution {
	static int N, M, K; // 세로, 가로, 기준
	static int[][] A;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			A = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					A[i][j] = sc.nextInt();
				}
			}
		
			System.out.printf("#%d %d\n", test_case, solve(0, 0));
		}
	}

	
	public static int solve(int idx, int size) {
		if(idx == N) {
			if(check()) return size;
			return Integer.MAX_VALUE;
		}
		
		int[] tmp = Arrays.copyOf(A[idx], A[idx].length);
		
		int ret = solve(idx + 1, size);
		for(int x = 0; x < M; x++) {
			A[idx][x] = 0;
		}
		ret = Math.min(ret, solve(idx+1, size+1));
		for(int x = 0; x < M; x++) {
			A[idx][x] = 1;
		}
		ret = Math.min(ret, solve(idx+1, size+1));
		
		A[idx] = tmp;
		return ret;
	}
	
	public static boolean check() {
		for(int x = 0; x < M; x++) {
			int zeroCnt = 0;
			int oneCnt = 0;
			boolean ok = false;
			for(int y = 0; y < N; y++) {
				if(A[y][x] == 0) {
					zeroCnt++;
					oneCnt = 0;
				}
				else {
					oneCnt++;
					zeroCnt = 0;
				}
				if(oneCnt >= K || zeroCnt >= K) {
					ok = true;
					break;
				}
			}
			if(!ok) return false;
		}
		return true;
	}
}