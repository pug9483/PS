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

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N; // 연구소의 크기 [4,50]
    public static int M; // 바이러스의 개수 [1,10]
    public static int[][] board;
    public static final int INF = 987654321;
    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, 1, -1}
;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ret = solve(0, 0, M);
        System.out.println(ret == INF ? -1 : ret);
    }


    public static int bfs(){
        int ret = 0;
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 3){
                    queue.add(new Point(i, j));
                    dist[i][j] = 0;
                }
            }
        }

        while(!queue.isEmpty()){
            Point here = queue.poll();
            if(board[here.y][here.x] == 0)
                ret = Math.max(dist[here.y][here.x], ret);
            for (int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if(board[ny][nx] == 1) continue;
                if(dist[ny][nx] != -1) continue;
                queue.add(new Point(ny, nx));
                dist[ny][nx] = dist[here.y][here.x] + 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
                if(board[i][j] == 0 && dist[i][j] == -1) return INF;
        }
        return ret;
    }

    private static int solve(int y, int x, int left) {
        if(left == 0){
            return bfs();
        }
        if(x == N){
            y++;
            x = 0;
        }
        if(y == N) return INF;

        int ret = INF;
        if(board[y][x] == 2){
            board[y][x] = 3;
            ret = Math.min(ret, solve(y, x+1, left-1));
            board[y][x] = 2;
        }
        ret = Math.min(ret, solve(y, x+1, left));

        return ret;
    }
}
