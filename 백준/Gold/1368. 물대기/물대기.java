import java.io.*;
import java.util.*;

public class Main {
    static class Edge{
        int u;
        int v;
        int w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static int N;
    public static int[] parent;

    public static List<Edge> edges = new ArrayList<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int w = Integer.parseInt(br.readLine());
            edges.add(new Edge(0, i, w));
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int w = Integer.parseInt(st.nextToken());
                if(i != j) edges.add(new Edge(i, j, w));
            }
        }
        for (int i = 0; i <= N; i++) parent[i] = i;
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;
        int count = 0;
        edges.sort(Comparator.comparingInt(o -> o.w));
        for (Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                ret += edge.w;
                count++;
                if (count == N) break;
            }
        }
        return ret;
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        parent[u] = v;
        return true;
    }

    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
