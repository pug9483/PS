import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N = 9;
	private static int[][] A = new int[N][N];

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				A[i][j] = s.charAt(j) - '0';
			}
		}
		solve(0, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(A[i][j]);
			}
			System.out.println();
		}
	}

	private static boolean solve(int y, int x) {
		if(y == N) return true;
		if(x == N) return solve(y+1, 0);

		if(A[y][x] != 0) return solve(y, x+1);

		for (int num = 1; num <= 9; num++) {
			if (!checkRow(y, num)) continue;
			if (!checkCol(x, num)) continue;
			if (!checkBox(y, x, num)) continue;
			A[y][x] = num;
			if (solve(y, x + 1)) return true;
			A[y][x] = 0;
		}
		return false;
	}

	private static boolean checkRow(int y, int num) {
		for(int i = 0; i < N; i++) {
			if(A[y][i] == num) return false;
		}
		return true;
	}

	private static boolean checkCol(int x, int num) {
		for(int i = 0; i < N; i++) {
			if(A[i][x] == num) return false;
		}
		return true;
	}

	private static boolean checkBox(int y, int x, int num) {
		int fy = (y / 3) * 3;
		int fx = (x / 3) * 3;
		for(int i = fy; i < fy + 3; i++) {
			for (int j = fx; j < fx + 3; j++) {
				if(A[i][j] == num) return false;
			}
		}
		return true;
	}
}
