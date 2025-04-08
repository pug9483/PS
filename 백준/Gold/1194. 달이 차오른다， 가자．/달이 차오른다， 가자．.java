import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int y;
		int x;
		int set;

		public Node(int y, int x, int set) {
			this.y = y;
			this.x = x;
			this.set = set;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static char[][] board;
	static final int INF = 987654321;
	static final int[] dy = {1, -1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		System.out.println(solve());
	}

	public static int solve() {
		Queue<Node> q = new LinkedList<>();
		int[][][] dist = new int[N][M][2<<6];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dist[i][j], INF);
			}
		}

		out:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == '0'){
					q.add(new Node(i, j, 0));
					dist[i][j][0] = 0;
					break out;
				}
			}
		}

		while (!q.isEmpty()) {
			Node here = q.poll();
			int d = dist[here.y][here.x][here.set];
			if(board[here.y][here.x] == '1') return d;
			for (int dir = 0; dir < 4; dir++) {
				int ny = here.y + dy[dir];
				int nx = here.x + dx[dir];
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || board[ny][nx] == '#') continue;
				if (isDoor(ny, nx) && (here.set & 1 << (board[ny][nx] - 'A')) == 0) continue;

				int newSet = here.set;
				if (isKey(ny, nx)) newSet |= (1 << (board[ny][nx] - 'a'));

				if(dist[ny][nx][newSet] == INF){
					dist[ny][nx][newSet] = d + 1;
					q.add(new Node(ny, nx, newSet));
				}
			}
		}

		return -1;
	}

	private static boolean isKey(int ny, int nx) {
		return board[ny][nx] == 'a' || board[ny][nx] == 'b' || board[ny][nx] == 'c' || board[ny][nx] == 'd'
			|| board[ny][nx] == 'e' || board[ny][nx] == 'f';
	}

	private static boolean isDoor(int ny, int nx) {
		return board[ny][nx] == 'A' || board[ny][nx] == 'B' || board[ny][nx] == 'C' || board[ny][nx] == 'D'
			|| board[ny][nx] == 'E' || board[ny][nx] == 'F';
	}
}
