import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int y;
        int x;
        int type;
        int dist;
        int level;
        int change;
        public Point(int y, int x, int type, int dist, int level, int change) {
            this.y = y;
            this.x = x;
            this.type = type;
            this.dist = dist;
            this.level = level;
            this.change = change;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] board;
    public static final int INF = 987654321;
    public static int[][][] dir = {
            {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}},
            {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}},
            {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}};
    public static int retSwitch = INF;
    public static int retMove = INF;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(retMove);
        System.out.println(retSwitch);
    }

    private static void solve() {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt((Point a) -> a.dist).thenComparingInt(a -> a.change));
        int[][][][] dist = new int[N][N][3][N * N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    Arrays.fill(dist[i][j][k], INF);
                }
            }
        }
        out:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    for (int type = 0; type < 3; type++) {
                        pq.add(new Point(i, j, type, 0, 1, 0));
                        dist[i][j][type][1] = 0;
                    }
                    break out;
                }
            }
        }
        while (!pq.isEmpty()) {
            Point here = pq.poll();
            if (here.level == N * N) {
                retSwitch = here.change;
                retMove = here.dist;
                return;
            }

            for (int type = 0; type < 3; type++) {
                if (here.type == type || dist[here.y][here.x][type][here.level] <= here.dist + 1) continue;
                pq.add(new Point(here.y, here.x, type, here.dist + 1, here.level, here.change+1));
            }
            if (here.type == 0) goKnight(here, dist, pq);
            else goBishopOrRook(here, dist, pq);
        }
    }

    private static void goBishopOrRook(Point here, int[][][][] dist, PriorityQueue<Point> pq) {
        for (int d = 0; d < dir[here.type].length; d++) {
            int ny = here.y + dir[here.type][d][0];
            int nx = here.x + dir[here.type][d][1];

            while (check(ny, nx)) {
                if (board[ny][nx] == here.level + 1 && dist[ny][nx][here.type][here.level + 1] > here.dist + 1) {
                    dist[ny][nx][here.type][here.level + 1] = here.dist + 1;
                    pq.offer(new Point(ny, nx, here.type, here.dist + 1, here.level + 1, here.change));
                }
                else if (dist[ny][nx][here.type][here.level] > here.dist+1) {
                    dist[ny][nx][here.type][here.level] = here.dist + 1;
                    pq.offer(new Point(ny, nx, here.type, here.dist + 1, here.level, here.change));
                }
                ny += dir[here.type][d][0];
                nx += dir[here.type][d][1];
            }
        }
    }

    private static void goKnight(Point here, int[][][][] dist, PriorityQueue<Point> pq) {
        for (int d = 0; d < dir[here.type].length; d++) {
            int ny = here.y + dir[here.type][d][0];
            int nx = here.x + dir[here.type][d][1];
            if (!check(ny, nx) || dist[ny][nx][here.type][here.level] <= here.dist + 1) continue;
            if (board[ny][nx] == here.level + 1 && dist[ny][nx][here.type][here.level + 1] > here.dist + 1) {
                dist[ny][nx][here.type][here.level + 1] = here.dist + 1;
                pq.offer(new Point(ny, nx, here.type, here.dist + 1, here.level + 1, here.change));
            } else if (dist[ny][nx][here.type][here.level] > here.dist + 1) {
                dist[ny][nx][here.type][here.level] = here.dist + 1;
                pq.offer(new Point(ny, nx, here.type, here.dist + 1, here.level, here.change));
            }
        }
    }

    private static boolean check(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
}