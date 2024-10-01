import java.io.*;
import java.util.*;

public class Main {
    public static int N = 200002;
    public static int M;
    public static int[] parent;
    public static int[] count;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            Map<String, Integer> map = new HashMap<>();
            M = Integer.parseInt(br.readLine());
            parent = new int[N];
            count = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                count[i] = 1;
            }
            int idx = 0;
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!map.containsKey(a)) map.put(a, idx++);
                if (!map.containsKey(b)) map.put(b, idx++);
                int u = map.get(a);
                int v = map.get(b);
                union(u, v);
                sb.append(count[find(u)]).append("\n");
            }
        }
        System.out.println(sb);
    }

    // kruskal을 통해 집합의 개수 구하기 -> u 기준으로 합치기
    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return;

        parent[v] = u;
        count[u] += count[v];
    }

    private static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}