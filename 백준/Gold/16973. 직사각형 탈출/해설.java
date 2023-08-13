/*
방법 1. 이동할 때마다 직사각형의 모든 칸을 검사해준다.
O(NMHW) : 시간초과

방법 2. 각각의 방향으로 이동할 때 새로 생기는 칸에 대해서만 검사를 해주면 된다.
O(NM(H+W)) : 시간초과

방법 3. 이동하려고 하는 직사각형의 합이 0인 경우에만 이동할 수 있다. -> 누적합 이용
O(NM) : 가능
*/

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
    public static int[][] pSum;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];
        pSum = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        fr = Integer.parseInt(st.nextToken());
        fc = Integer.parseInt(st.nextToken());
        partialSum();
        System.out.println(solve());
    }

    private static void partialSum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                pSum[i][j] = pSum[i - 1][j] + pSum[i][j - 1] - pSum[i - 1][j - 1] + board[i][j];
            }
        }
    }

    private static int solve() {
        Queue<Point> q = new LinkedList<>();
        int[][] dist = new int[N+1][M+1];
        for (int i = 0; i < N+1; i++) Arrays.fill(dist[i], -1);
        dist[sr][sc] = 0;
        q.add(new Point(sr, sc));
        while (!q.isEmpty()) {
            Point here = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(1 <= ny && ny + H - 1 <= N && 1 <= nx && nx + W -1 <= M){
                    if(sum(ny,nx, ny+H-1, nx+W-1) == 0){
                        if(dist[ny][nx] == -1){
                            q.add(new Point(ny, nx));
                            dist[ny][nx] = dist[here.y][here.x] + 1;
                        }
                    }
                }
            }
        }
        return dist[fr][fc];
    }

    private static int sum(int y1, int x1, int y2, int x2) {
        return pSum[y2][x2] - pSum[y1-1][x2] - pSum[y2][x1-1] + pSum[y1-1][x1-1];
    }
}
