import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<int[]> rooms = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static int[][] dist;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int N;
    static int M;
    static int R;
    static int C;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            rooms.add(new int[]{y, x, w});
        }

        dist = new int[N][M];
        for(int i = 0; i < N; i++)
            Arrays.fill(dist[i], INF);

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            q.add(new int[]{y, x});
            dist[y][x] = 0;
        }
        System.out.println(solve());
    }

    private static int solve() {
        while (!q.isEmpty()) {
            int[] here = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int ny = here[0] + dy[dir];
                int nx = here[1] + dx[dir];
                if (ny < 0 || ny >= N || nx <0 || nx >= M || dist[ny][nx] != INF) continue;
                dist[ny][nx] = dist[here[0]][here[1]] + 1;
                q.add(new int[] {ny, nx});
            }
        }

        int ret = INF;
        for (int[] room : rooms) {
            ret = Math.min(ret, room[2] * dist[room[0]][room[1]]);
        }
        return ret;
    }
}