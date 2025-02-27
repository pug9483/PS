import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static char[][] A;
	public static int[] dy = {-1, 0, 1};
	public static int[] dx = {1, 1, 1};
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			A[i] = br.readLine().toCharArray();
		}
		
		int ret = 0;
		for(int sy = 0; sy < N; sy++) {
			if(solve(sy, 0)) ret++;
		}
		System.out.println(ret);
	}
	
	public static boolean solve(int sy, int sx) {
		if(sx == M-1) {
			return true;
		}
		
		int ret = 0;
		for(int dir = 0; dir < 3; dir++) {
			int ny = sy + dy[dir];
			int nx = sx + dx[dir];
			if(ny < 0 || ny >= N || nx < 0 || nx >= M || A[ny][nx] == 'x') continue;
			A[ny][nx] = 'x';
			if(solve(ny, nx)) return true;
		}
		return false;
	}
}
