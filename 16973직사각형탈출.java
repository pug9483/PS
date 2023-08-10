import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int y;
        int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static int N, M; // 격자판의 크기
    public static int H, W; // 직사각형의 크기
    public static int sr, sc, fr, fc; // 시작 좌표, 도착 좌표
    public static int[][] board;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        fr = Integer.parseInt(st.nextToken()) - 1;
        fc = Integer.parseInt(st.nextToken()) - 1;
        init();
        System.out.println(solve());
    }

    // 직사각형의 크기가 [[y .. y+H-1], [x .. x+W-1]]인 것에 1이 있어 가지 못하는 경우를 확인하는 것과
    // 직사각형의 크기를 1인 정사각형으로 바꾸고, 보드에서 [[y-H+1 .. y], [x-W+1 .. x]]를 모두 1로 바꿔주고 확인하는 것과 동일하다.
    private static void init() {
        int[][] newBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 1){
                    for(int k = i; k > i - H; k--){
                        for (int l = j; l > j - W; l--) {
                            if(k >= 0 && l >= 0)
                                newBoard[k][l] = 1;
                        }
                    }
                }
            }
        }
        board = newBoard;
    }

    private static int solve() {
        Queue<Point> q = new LinkedList<>();
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        // 문제의 조건에는 입력으로 주어진 직사각형에 벽이 없지만, 만약을 대비한 예외 처리
        if(board[sr][sc] != -1) {
            q.add(new Point(sr, sc));
            dist[sr][sc] = 0;
        }

        while (!q.isEmpty()) {
            Point here = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || dist[ny][nx] != -1 || board[ny][nx] == 1) continue;
                if(ny + H - 1 >= N || nx + W - 1 >= M ) continue;
                dist[ny][nx] = dist[here.y][here.x] + 1;
                q.add(new Point(ny, nx));
            }
        }
        return dist[fr][fc];
    }
}
