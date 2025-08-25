import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int M;
	private static int R;
	private static int[] A;
	private static int[][] dist;
	private static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) dist[i][j] = 0;
				else dist[i][j] = INF;
			}
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken()) - 1;
			int dst = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			dist[src][dst] = w;
			dist[dst][src] = w;
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int ret = 0;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if(dist[i][j] <= M){
					sum += A[j];
				}
			}
			ret = Math.max(ret, sum);
		}
		System.out.println(ret);
	}
}
