import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        Arrays.fill(A, 1);

        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int p = a / getGcd(a, b);
            int q = b / getGcd(a, b);
            int k = A[v] * p;
            int t = A[u] * q;

            int ggcd = getGcd(k, t);
            A[u] *= k / ggcd;
            A[v] *= t / ggcd;

            dfs(u, k/ggcd, new boolean[N]);
            dfs(v, t/ggcd, new boolean[N]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static int getGcd(int a, int b) {
        if(b == 0) return a;
        return getGcd(b, a % b);
    }

    private static void dfs(int here, int num, boolean[] visited) {
        visited[here] = true;

        for (int next : graph.get(here)) {
            if(visited[next]) continue;
            A[next] *= num;
            dfs(next, num, visited);
        }
    }
}
