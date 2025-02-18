import java.util.*;

class Main
{
	static int N;
	static int M;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[][] dist = new int[N][N];
		for(int i = 0; i < M; i++) {
			int u = sc.nextInt()- 1;
			int v = sc.nextInt()- 1;
			dist[u][v] = 1;
		}
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dist[i][j] |= (dist[i][k] & dist[k][j]);
				}
			}
		}
		
		int ret = 0;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if(dist[i][j] == 1 || dist[j][i] == 1) sum++;
			}
			if(sum == N-1) ret++;
		}
		System.out.println(ret);
	}
}