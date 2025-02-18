import java.util.*;

class Main
{
	static int N;
	static int M;
	static List<List<Integer>> graph = new ArrayList<>();
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		boolean[][] visited = new boolean[N][N];
		for(int i = 0; i < N; i++) graph.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			int u = sc.nextInt()- 1;
			int v = sc.nextInt()- 1;
			graph.get(u).add(v);
			
		}
		for(int i = 0; i < N; i++) {
			solve(i, visited);
		}
		
		int ret = 0;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if(visited[i][j] || visited[j][i]) sum++;
			}
			if(sum == N-1) ret++;
		}
		System.out.println(ret);
	}
	
	static void solve(int here, boolean[][] visited) {
		for(int next: graph.get(here)) {
			if(!visited[here][next]) {
				visited[here][next] = true;
				solve(next, visited);
			}
			for(int j = 0; j < N; j++) {
				if(visited[next][j]) {
					visited[here][j] = true;
				}
			}
		}
	}
	
}

