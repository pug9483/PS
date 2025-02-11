
import java.util.Scanner;

//벽 3개 세워 안전구역 최댓값 구하기
public class Main {
	public static int N;
	public static int M;
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	public static int result = Integer.MIN_VALUE;
	
	public static int[][] copy(int [][] arr) {
		int[][] copyWall = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyWall[i][j] = arr[i][j];
			}
		}
		return copyWall;
	}
	
	public static void print(int[][] a) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	
	private static void check(int y, int x, int[][] copyWall) {
		copyWall[y][x] = 3;
		
		for(int i=0; i<4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if(nextY >= 0 && nextY < N && nextX >=0 && nextX <M && (copyWall[nextY][nextX] == 0 || copyWall[nextY][nextX] == 2))
				check(nextY,nextX,copyWall);
		}
	}

	public static void countMax(int[][] arr) {
		int[][] copyWall = copy(arr);
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++)
				if(arr[y][x] == 2)
					check(y,x,copyWall);
		}
		
		int tmp = 0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++)
				if(copyWall[y][x] == 0) tmp++;
		}
		result = Math.max(result, tmp);
	}
	
	public static void solve(int count, int[][] arr) {
		if(count == 0) {
			countMax(arr);
			return;
		}
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(arr[y][x] == 0) {
					arr[y][x] = 1;
					solve(count-1, arr);
					arr[y][x] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		solve(3, arr);
		System.out.println(result);
	}
}
