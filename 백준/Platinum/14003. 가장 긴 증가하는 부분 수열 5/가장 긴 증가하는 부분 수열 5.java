import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int[] A;

	public static void main(String[] args) throws IOException {
		N =  Integer.parseInt(br.readLine());
		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		solve();
	}

	private static void solve() {
		// 경로를 역추적하기 위한 배열.
		// P[i]는 "원본 배열의 A[i]가 L 배열의 몇 번째 인덱스(pos)에 들어갔는지"를 기록
		int[] P = new int[N];

		// LIS를 만들어가는 배열
		// L[i]는 "길이가 i+1인 증가 부분 수열을 만들 수 있는 마지막 숫자 중 가장 작은 값"을 의미
		int[] L = new int[N]; // 요소 넣기
		int len  = 0;

		for (int i = 0; i < N; i++) {
			int pos = Arrays.binarySearch(L, 0, len, A[i]);
			if (pos < 0) pos =  -pos - 1;
			L[pos] = A[i];
			P[i] = pos;
			if(pos == len) len++;
		}

		int[] result = new int[len];
		int k = len - 1;
		for (int i = N - 1; i >= 0; i--) {
			if (P[i] == k) {
				result[k] = A[i];
				k--;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(len);
		System.out.println(sb);
	}
}
