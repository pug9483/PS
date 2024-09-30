import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static int[] A;
    public static List<List<Integer>> graph;
    public static int[] indegree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = new int[N];
            indegree = new int[N];

            graph = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                graph.get(u).add(v);
                indegree[v]++;
            }

            st = new StringTokenizer(br.readLine());
            int dst = Integer.parseInt(st.nextToken()) - 1;

            System.out.println(solve(dst));
        }
    }

    public static int solve(int dst) {
        int[] dist = new int[N];
    
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if(indegree[i] == 0) {
                q.add(i);
                dist[i] = A[i];
            }
        }

        while (!q.isEmpty()) {
            int here = q.poll();
            for (int next : graph.get(here)) {
                dist[next] = Math.max(dist[next], dist[here] + A[next]);
                indegree[next]--;
                if(indegree[next] == 0)
                    q.add(next);
            }
        }
        return dist[dst];
    }    
}