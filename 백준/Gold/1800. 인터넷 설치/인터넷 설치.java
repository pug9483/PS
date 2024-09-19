import java.io.*;
import java.util.*;

import static java.util.Comparator.*;

public class Main {
    static class Node {
        int v;
        int w;
        int kCnt;

        public Node(int v, int w, int kCnt) {
            this.v = v;
            this.w = w;
            this.kCnt = kCnt;
        }

        public static Node create(int v, int w, int kCnt) {
            return new Node(v, w, kCnt);
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int P;
    public static int K;
    public static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w, 0));
            graph.get(v).add(new Node(u, w, 0));
        }
        System.out.println(solve());
    }

    private static int solve() {
        int ret = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>(comparingInt(o -> o.w));

        int[][] dist = new int[N][K + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[N - 1][0] = 0;
        pq.add(new Node(N - 1, 0, 0));

        while (!pq.isEmpty()) {
            Node here = pq.poll();
            if (here.v == 0) {
                ret = Math.min(ret, dist[here.v][here.kCnt]);
            }

            if (dist[here.v][here.kCnt] < here.w) continue;

            for (Node next : graph.get(here.v)) {
                int newDist = dist[here.v][here.kCnt];
                if (here.kCnt < K && dist[next.v][here.kCnt + 1] > newDist) {
                    dist[next.v][here.kCnt + 1] = newDist;
                    pq.add(Node.create(next.v, newDist, here.kCnt + 1));
                }
                newDist = Math.max(dist[here.v][here.kCnt], next.w);
                if (dist[next.v][here.kCnt] > newDist) {
                    dist[next.v][here.kCnt] = newDist;
                    pq.add(Node.create(next.v, newDist, here.kCnt));
                }
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}