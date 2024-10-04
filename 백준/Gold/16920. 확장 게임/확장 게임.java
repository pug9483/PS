import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static int N;
    public static int M;
    public static int P;
    public static char[][] A;
    public static int[] B;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    public static List<Queue<Point>> list = new ArrayList<>();
    public static int[] size;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        B = new int[P + 1];
        size = new int[P + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) B[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= P; i++) list.add(new LinkedList<>());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                A[i][j] = c;

                if (c >= '1' && c <= '9') {
                    size[c - '0']++;
                    list.get(c - '0').add(new Point(i, j));
                }
            }
        }
        solve();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) sb.append(size[i]).append(" ");
        System.out.println(sb);
    }

    private static void solve() {
        int pos = 1;
        int round = 0;

        while (true) {
            int count = bfs(list.get(pos), B[pos], pos);
            size[pos] += count;
            round += count;

            pos++;
            if (pos == P + 1) {
                if(round == 0) break;
                round = 0;
                pos = 1;
            }
        }
    }

    private static int bfs(Queue<Point> q, int maxDist, int pos) {
        int ret = 0;
        int dist = 1;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Point here = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int ny = here.y + dy[dir];
                    int nx = here.x + dx[dir];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (A[ny][nx] == '.') {
                        q.add(new Point(ny, nx));
                        A[ny][nx] = (char) (pos + '0');
                        ret++;
                    }
                }
            }
            if ((++dist) > maxDist) break;
        }
        return ret;
    }
}
