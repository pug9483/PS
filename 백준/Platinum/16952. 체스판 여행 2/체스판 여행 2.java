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
        int swtch;

        public Point(int y, int x, int type, int dist, int level, int swtch) {
            this.y = y;
            this.x = x;
            this.type = type;
            this.dist = dist;
            this.level = level;
            this.swtch = swtch;
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
    public static int retMove = INF;
    public static int retSwtch = INF;

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
        System.out.println(retSwtch);
    }

    private static void solve() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][][][] visited = new boolean[N][N][3][N * N + 1][4 * N * N + 1];
        out:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    for (int type = 0; type < 3; type++) {
                        q.add(new Point(i, j, type, 0, 1, 0));
                        visited[i][j][type][1][0] = true;
                    }
                    break out;
                }
            }
        }
        while (!q.isEmpty()) {
            Point here = q.poll();
            if (here.level == N * N && here.dist <= retMove) {
                retMove = Math.min(retMove, here.dist);
                retSwtch = Math.min(retSwtch, here.swtch);
            }

            for (int type = 0; type < 3; type++) {
                if (here.swtch >= 4 * N * N - 1) continue;
                if (here.type == type || visited[here.y][here.x][type][here.level][here.swtch + 1]) continue;
                q.add(new Point(here.y, here.x, type, here.dist + 1, here.level, here.swtch + 1));
                visited[here.y][here.x][type][here.level][here.swtch + 1] = true;
            }
            if (here.type == 0) goKnight(here, visited, q);
            else goBishopOrRook(here, visited, q);
        }
    }

    private static void goBishopOrRook(Point here, boolean[][][][][] visited, Queue<Point> q) {
        for (int d = 0; d < dir[here.type].length; d++) {
            int ny = here.y + dir[here.type][d][0];
            int nx = here.x + dir[here.type][d][1];

            while (check(ny, nx)) {
                if (board[ny][nx] == here.level + 1 && !visited[ny][nx][here.type][here.level + 1][here.swtch]) {
                    visited[ny][nx][here.type][here.level + 1][here.swtch] = true;
                    q.offer(new Point(ny, nx, here.type, here.dist + 1, here.level + 1, here.swtch));
                } else if (!visited[ny][nx][here.type][here.level][here.swtch]) {
                    visited[ny][nx][here.type][here.level][here.swtch] = true;
                    q.offer(new Point(ny, nx, here.type, here.dist + 1, here.level, here.swtch));
                }
                ny += dir[here.type][d][0];
                nx += dir[here.type][d][1];
            }
        }
    }

    private static void goKnight(Point here, boolean[][][][][] visited, Queue<Point> q) {
        for (int d = 0; d < dir[here.type].length; d++) {
            int ny = here.y + dir[here.type][d][0];
            int nx = here.x + dir[here.type][d][1];
            if (!check(ny, nx) || visited[ny][nx][here.type][here.level][here.swtch]) continue;
            if (board[ny][nx] == here.level + 1 && !visited[ny][nx][here.type][here.level + 1][here.swtch]) {
                visited[ny][nx][here.type][here.level + 1][here.swtch] = true;
                q.offer(new Point(ny, nx, here.type, here.dist + 1, here.level + 1, here.swtch));
            } else if (!visited[ny][nx][here.type][here.level][here.swtch]) {
                visited[ny][nx][here.type][here.level][here.swtch] = true;
                q.offer(new Point(ny, nx, here.type, here.dist + 1, here.level, here.swtch));
            }
        }
    }

    private static boolean check(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
}