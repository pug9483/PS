import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Marbles {
        public int ry;
        public int rx;
        public int by;
        public int bx;
        public int dist;

        public Marbles(int ry, int rx, int by, int bx, int dist) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.dist = dist;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] board;
    private static int[] red = new int[2];
    private static int[] blue = new int[2];
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                }
                else if (board[i][j] == 'B'){
                    blue[0] = i;
                    blue[1] = j;
                }
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<Marbles> queue = new LinkedList<>();
        queue.add(new Marbles(red[0], red[1], blue[0], blue[1], 0));
        visited[red[0]][red[1]][blue[0]][blue[1]] = true;

        while (!queue.isEmpty()) {
            Marbles marbles = queue.poll();
            int ry = marbles.ry;
            int rx = marbles.rx;
            int by = marbles.by;
            int bx = marbles.bx;
            int dist = marbles.dist;
            if (dist >= 10) return -1;

            for (int dir = 0; dir < 4; dir++) {
                boolean redFinish = false;
                boolean blueFinish = false;

                int newRy = ry;
                int newRx = rx;
                while (board[newRy + dy[dir]][newRx + dx[dir]] != '#') {
                    newRy += dy[dir];
                    newRx += dx[dir];
                    if (board[newRy][newRx] == 'O') {
                        redFinish = true;
                        break;
                    }
                }

                int newBy = by;
                int newBx = bx;
                while (board[newBy + dy[dir]][newBx + dx[dir]] != '#') {
                    newBy += dy[dir];
                    newBx += dx[dir];
                    if (board[newBy][newBx] == 'O') {
                        blueFinish = true;
                        break;
                    }
                }

                if (blueFinish) continue;
                if (redFinish) return dist + 1;

                // 겹치는 경우
                if (newRy == newBy && newRx == newBx) {
                    if (dir == 0) { // 위쪽으로 기울이기
                        if (ry > by) newRy += 1;
                        else newBy += 1;
                    } else if (dir == 1) { // 오른쪽으로 기울이기
                        if (rx < bx) newRx -= 1;
                        else newBx -= 1;
                    } else if (dir == 2) { // 아래쪽으로 기울이기
                        if (ry < by) newRy -= 1;
                        else newBy -= 1;
                    } else { // 왼쪽으로 기울이기
                        if (rx > bx) newRx += 1;
                        else newBx += 1;
                    }
                }

                if (!visited[newRy][newRx][newBy][newBx]) {
                    visited[newRy][newRx][newBy][newBx] = true;
                    queue.add(new Marbles(newRy, newRx, newBy, newBx, dist+1));
                }
            }
        }
        return -1;
    }
}
