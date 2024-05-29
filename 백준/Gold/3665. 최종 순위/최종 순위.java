import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int T;
    public static int N;
    public static int M;
    public static int[] A;
    public static int[][] edges;
    public static int[] indegree;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }
            computeEdge();
            computeInDegree();
            solve();
        }
        System.out.print(sb);
    }

    private static void computeInDegree() {
        indegree = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if(edges[j][i] == 1) sum++;
            }
            indegree[i] = sum;
        }
    }

    private static void computeEdge() throws IOException {
        StringTokenizer st;
        edges = new int[N+1][N+1];
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                edges[A[i]][A[j]] = 1;
            }
        }
        M = Integer.parseInt(br.readLine());
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (edges[u][v] == 1) {
                edges[u][v] = 0;
                edges[v][u] = 1;
            }
            else {
                edges[v][u] = 0;
                edges[u][v] = 1;
            }
        }
    }

    private static void solve() {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();

        for (int i = 1; i <= N; i++)
            if(indegree[i] == 0) q.add(i);

        for (int loop = 0; loop < N; loop++) {
            if (q.isEmpty()){
                sb.append("IMPOSSIBLE\n");
                return;
            }
            if (q.size() > 1) {
                sb.append("?\n");
                return;
            }
            int here = q.poll();
            ret.add(here);
            indegree[here]--;
            for (int i = 1; i <= N; i++) {
                if (edges[here][i] == 1) indegree[i]--;
                if (indegree[i] == 0) q.add(i);
            }
        }
        ret.forEach(i -> sb.append(i).append(" "));
        sb.append("\n");
    }
}
