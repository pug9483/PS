import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, K;
    public static int[] dist;
    public static boolean check(int num){
        return num >= 0 && num <= 100000;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        Arrays.fill(dist, -1);
        bfs();
        System.out.println(dist[K]);
        System.out.println(reconstruct(K));
    }

    private static void go(int here, int next, Queue<Integer> q) {
        if(check(next) && dist[next] == -1){
            q.add(next);
            dist[next] = dist[here] + 1;
        }
    }

    private static int reconstruct(int here) {
        if(here < 0 || here > 100000) return 0;
        if(here == N) return 1;
        int ret = 0;
        if(check(here-1) && dist[here-1] + 1 == dist[here]) ret += reconstruct(here-1);
        if(check(here+1) && dist[here+1] + 1 == dist[here]) ret += reconstruct(here+1);
        if(here % 2 == 0 && dist[here / 2] + 1 == dist[here]) ret += reconstruct(here / 2);
        return ret;
    }


    private static void bfs() {
        dist[N] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while (!q.isEmpty()) {
            int here = q.poll();
            go(here, here + 1, q);
            go(here, here - 1, q);
            go(here, here * 2, q);
        }
    }
}
