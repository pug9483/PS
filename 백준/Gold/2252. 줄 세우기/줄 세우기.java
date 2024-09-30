import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

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
            indegree[v]++;
            graph.get(u).add(v);
        }
        solve();
    }

    public static void solve() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        List<Integer> orders = new ArrayList<>();

        for (int i = 0; i < N; i++)
            if (indegree[i] == 0) q.add(i);

        while (!q.isEmpty()) {
            int here = q.poll();
            visited[here] = true;
            orders.add(here + 1);

            for (int there : graph.get(here)) {
                indegree[there]--;
                if (indegree[there] == 0) q.add(there);
            }
        }

        StringBuilder sb = new StringBuilder();
        orders.forEach(o -> sb.append(o).append(" "));
        for (int i = 0; i < N; i++) {
            if (!visited[i]) sb.append(i + 1).append(" ");
        }
        System.out.println(sb);
    }
}