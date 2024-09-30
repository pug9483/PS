import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] indegree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N];
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            indegree[v]++;
        }
        solve();
    }

    public static void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer::compare));
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if(indegree[i] == 0) pq.add(i);
        }

        while (!pq.isEmpty()) {
            int here = pq.poll();
            ret.add(here + 1);
            for (int next : graph.get(here)) {
                indegree[next]--;
                if(indegree[next] == 0)
                    pq.add(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        ret.forEach(o -> sb.append(o).append(" "));
        System.out.println(sb);
    }
}