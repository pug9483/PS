import java.io.*;
import java.util.*;

/**
 * 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
 * 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다.
 * 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다.
 * 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안 된다.
 * 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
 *
 * 각각의 동작에서 공은 동시에 움직인다.
 * 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다.
 * 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
 * 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다.
 * 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다.
 * 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
 *
 * 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
 * 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.
 */

public class Main {

    static class Ball{
        int y, x;

        public Ball(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Ball move(int dir, char[][] board) {
            int sy = y;
            int sx = x;
            while(true){
                int ny = sy + dy[dir];
                int nx = sx + dx[dir];
                if(board[ny][nx] == '#') break;
                sy = ny;
                sx = nx;
                if(board[sy][sx] == 'O') break;
            }
            return new Ball(sy, sx);
        }

        public boolean isHole(char[][] board) {
            return board[y][x] == 'O';
        }
    }

    static class BallContainer{
        Ball redBall;
        Ball blueBall;
        int rollCount;

        public BallContainer(Ball redBall, Ball blueBall, int rollCount) {
            this.redBall = redBall;
            this.blueBall = blueBall;
            this.rollCount = rollCount;
        }

        public BallContainer roll(int dir, char[][] board) {
            Ball movedRedBall = redBall.move(dir, board);
            Ball movedBlueBall = blueBall.move(dir, board);

            if(movedBlueBall.isHole(board)) return null;

            if (isDuplicated(movedRedBall, movedBlueBall)) {
                if(dir == 0){
                    if(redBall.x < blueBall.x) movedRedBall.x--;
                    else movedBlueBall.x--;
                } else if(dir == 1){
                    if(blueBall.x > redBall.x) movedBlueBall.x++;
                    else movedRedBall.x++;
                } else if (dir == 2) {
                    if(redBall.y < blueBall.y) movedRedBall.y--;
                    else movedBlueBall.y--;
                } else if (dir == 3) {
                    if(redBall.y > blueBall.y) movedRedBall.y++;
                    else movedBlueBall.y++;
                }
            }

            return new BallContainer(movedRedBall, movedBlueBall, rollCount+1);
        }

        public boolean isSuccess(char[][] board) {
            return redBall.isHole(board) && !blueBall.isHole(board);
        }

        private boolean isDuplicated(Ball movedRedBall, Ball movedBlueBall) {
            return movedRedBall.y == movedBlueBall.y && movedRedBall.x == movedBlueBall.x;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[][] board;
    static final int[] dy = {0, 0, 1, -1}; // 우, 좌, 하, 상
    static final int[] dx = {1, -1, 0, 0};
    static final int LIMIT = 10;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        Ball redBall = null;
        Ball blueBall = null;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'R') {
                    redBall = new Ball(i, j);
                } else if (board[i][j] == 'B') {
                    blueBall = new Ball(i, j);
                }
            }
        }
        BallContainer ballContainer = new BallContainer(redBall, blueBall, 0);
        System.out.println(solve(ballContainer));
    }

    private static int solve(BallContainer ballContainer) {
        Queue<BallContainer> q = new LinkedList<>();
        boolean[][][][][] visited = new boolean[N][M][N][M][LIMIT+2];
        q.add(ballContainer);

        while (!q.isEmpty()) {
            BallContainer container = q.poll();
            if(container.rollCount > LIMIT) continue;

            if (container.isSuccess(board)) {
                return container.rollCount;
            }

            for (int dir = 0; dir < 4; dir++) {
                BallContainer rolledBallContainer = container.roll(dir, board);
                if(rolledBallContainer == null) continue;

                if (!visited[rolledBallContainer.redBall.y][rolledBallContainer.redBall.x][rolledBallContainer.blueBall.y][rolledBallContainer.blueBall.x][rolledBallContainer.rollCount]) {
                    visited[rolledBallContainer.redBall.y][rolledBallContainer.redBall.x][rolledBallContainer.blueBall.y][rolledBallContainer.blueBall.x][rolledBallContainer.rollCount] = true;
                    q.add(rolledBallContainer);
                }
            }
        }

        return -1;
    }
}