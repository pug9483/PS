import java.util.*;
import java.io.*;

public class Main { 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int K;
	static int INF = 987654321;
	
	public static void main(String args[]) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int backCnt = 0;
		String s = br.readLine();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'T') backCnt++;
		}
		
		System.out.println(solve(N - backCnt));
	}
	
	public static int solve(int front) {
		Queue<Integer> q = new LinkedList<>();
		q.add(front);
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[front] = 0;
		
		while(!q.isEmpty()) {
			int here = q.poll();
			if(here == 0) {
				return dist[here]; // front의 개수
			}
			
			for(int i = 0; i <= K; i++) { 
				int frontCnt = i; // i개 만큼 front -> back으로 바꿈
				int backCnt = K - i; // K - i개 만큼 back -> front으로 바꿈
				if(here < frontCnt) continue;
				if(N - here < backCnt) continue;
				int next = here - frontCnt + backCnt;  // ?
				if(next < 0 || next > N || dist[next] != INF) continue;
				q.add(next);
				dist[next] = dist[here] + 1;
			}
		}
		
		return -1;
	}
}