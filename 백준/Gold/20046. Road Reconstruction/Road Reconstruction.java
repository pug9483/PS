import java.util.*;
import java.io.*;

/**
 * 도시가 위와 같이 격자 형태로 주어졌을 때
 * 맨 왼쪽 위 단위 격자에서 맨 오른쪽 아래 단위 격자로 가는 경로를 만들기 위해 필요한 최소 도로 건설 비용을 구하는 프로그램을 작성하시오.
 */

public class Main { 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static int[][] A;
	static int INF = 987654321;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	
	public static void main(String args[]) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		 		
		System.out.println(solve());
	}
	
	public static int solve() {
		if(A[0][0] == -1) return -1;
		
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		int[][] dist = new int[N][M];
		for(int i = 0; i < N; i++)
			Arrays.fill(dist[i], INF);
		
		dist[0][0] = A[0][0];
		q.add(new int[] {0, 0, dist[0][0]});
		
		while(!q.isEmpty()) {
			int[] here = q.poll();
			int w = dist[here[0]][here[1]];
			if(here[2] < w) continue;
			
			if(here[0] == N-1 && here[1] == M-1) {
				return w;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = here[0] + dy[dir];
				int nx = here[1] + dx[dir];
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || A[ny][nx] == -1) continue;
				if(dist[ny][nx] <= w + A[ny][nx]) continue;
				dist[ny][nx] = w + A[ny][nx];
				q.add(new int[] {ny, nx, dist[ny][nx]});
			}
		}
		return -1; 
	}
}