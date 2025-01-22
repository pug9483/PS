import java.util.*;
import java.io.*;

public class Main { 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static List<List<Integer>> graph = new ArrayList<>();
	static int INF = 987654321;
	static int[] rumors;
	static int[] indegree;
	
	public static void main(String args[]) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		 
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		for(int u = 1; u <= N; u++) {
			st = new StringTokenizer(br.readLine());
			while(true) {
				int v = Integer.parseInt(st.nextToken());
				if(v == 0) break;
				graph.get(u).add(v);
				indegree[v]++;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		rumors = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			rumors[i] = Integer.parseInt(st.nextToken());
		}
		
		solve();
	}
	
	public static void solve() {
		Queue<Integer> q = new LinkedList<>();
		int[] dist = new int[N+1];
		int[] sums = new int[N+1];
		
		Arrays.fill(dist, INF);
		for(int rumor: rumors) {
			dist[rumor] = 0;
			q.add(rumor);
		}
		
		while(!q.isEmpty()) {
			int here = q.poll();
			for(int next: graph.get(here)) {
				if(dist[next] != INF) continue;
				indegree[next]--;
				sums[next]++;
				if(indegree[next] <= sums[next]) {
					dist[next] = dist[here] + 1;
					q.add(next);	
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(dist[i] == INF ? -1 : dist[i]).append(" ");
		}
		System.out.println(sb);
	}
}