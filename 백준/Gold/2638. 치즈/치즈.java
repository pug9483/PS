import java.io.*;
import java.util.*;

public class Main {
    /**
     * N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다. 이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면
     * 공기와 접촉하여 천천히 녹는다. 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져
     * 버린다. 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Board {
        private int n;
        private int m;
        private int[][] board;

        // 각 보드는 3가지의 상태가 있을 수 있다. (치즈, 공기와 접촉한 칸, 공기와 접촉하지 않은 칸)
        private static final int CHEESE = 1;
        private static final int AIR_EXPOSED_CELL = 2;
        private static final int NON_EXPOSED_CELL = 3;
        private static final int[] dy = {0, 0, 1, -1};
        private static final int[] dx = {1, -1, 0, 0};

        public Board(int n, int m, int[][] board) {
            this.n = n;
            this.m = m;
            this.board = board;
        }

        // 1. 공기와 접촉한 칸과 공기와 접촉하지 않은 칸 구분하기
        // 1_1. 한 칸에서 시작해서 bfs로 주변을 확장해 나간다. -> 한 칸이라도 모서리랑 만나면 여긴 모두 공기와 접촉한 칸
        // 1_2. 확장하다가 모서리를 만나면 이건 공기와 접촉한 칸이다.
        // 1_3. 모두 확장을 했는데 치즈만 만나면 공기와 접촉하지 않은 칸이다.
        public void markAirExposure() {
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] || board[i][j] == 1) {
                        continue;
                    }
                    bfs(i, j, visited);
                }
            }
        }

        private void bfs(int sy, int sx, boolean[][] visited) {
            Queue<Point> q = new LinkedList<>();
            List<Point> list = new ArrayList<>();
            q.add(new Point(sy, sx));

            boolean exposed = false;

            while (!q.isEmpty()) {
                Point here = q.poll();
                list.add(here);

                for (int dir = 0; dir < 4; dir++) {
                    int ny = here.y + dy[dir];
                    int nx = here.x + dx[dir];
                    if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                        exposed = true;
                        continue;
                    }
                    if (visited[ny][nx] || board[ny][nx] == 1) {
                        continue;
                    }
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                }
            }

            for (Point p : list) {
                int num = exposed ? AIR_EXPOSED_CELL : NON_EXPOSED_CELL;
                board[p.y][p.x] = num;
            }
        }

        public int meltExposedCheese() {
            int meltingCheeseCount = 0;
            List<Point> meltingCells = new ArrayList<>();
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if(board[y][x] != 1) continue;
                    int airExposedCellCount = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int ny = y + dy[dir];
                        int nx = x + dx[dir];
                        if(ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                        if(board[ny][nx] == AIR_EXPOSED_CELL) airExposedCellCount++;
                    }
                    if (airExposedCellCount >= 2) {
                        meltingCells.add(new Point(y, x));
                    }
                }
            }
            meltingCheeseCount = meltingCells.size();
            for (Point p : meltingCells) {
                board[p.y][p.x] = 0;
            }
            return meltingCheeseCount;
        }

        public int getTotalMeltingTime() {
            int totalMeltingTime = 0;

            while(true){
                markAirExposure();
                int meltingCheeseCount = meltExposedCheese();
                if(meltingCheeseCount == 0) break;
//                printBoard();
                totalMeltingTime++;
            }

            return totalMeltingTime;
        }

        public void printBoard() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int num = board[i][j];
                    if(num == 2) num = 0;
                    System.out.print(num + " ");
                }
                System.out.println();
            }
            System.out.println("--------------------------------");
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] A = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Board board = new Board(n, m, A);
        System.out.println(board.getTotalMeltingTime());
    }
}