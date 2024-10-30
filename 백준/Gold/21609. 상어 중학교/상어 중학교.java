import java.io.*;
import java.util.*;

public class Main {
    static class Group {
        Point anchorPoint;
        int rainbowBlockCount;
        List<Point> totalBlocks;

        public Group(Point anchorPoint, int rainbowBlockCount, List<Point> totalBlocks) {
            this.anchorPoint = anchorPoint;
            this.rainbowBlockCount = rainbowBlockCount;
            this.totalBlocks = totalBlocks;
        }

        public int getBlockSize() {
            return totalBlocks.size();
        }

        public boolean isBetterGroup(Group otherGroup) {
            boolean ret = getBlockSize() < otherGroup.getBlockSize();
            ret |= getBlockSize() == otherGroup.getBlockSize() && rainbowBlockCount < otherGroup.rainbowBlockCount;
            ret |= getBlockSize() == otherGroup.getBlockSize() && rainbowBlockCount == otherGroup.rainbowBlockCount && (
                    anchorPoint.y < otherGroup.anchorPoint.y || (anchorPoint.y == otherGroup.anchorPoint.y && anchorPoint.x < otherGroup.anchorPoint.x));
            return ret;
        }

        public void updateGroup(Group otherGroup) {
            if (isBetterGroup(otherGroup)) {
                totalBlocks = otherGroup.totalBlocks;
                rainbowBlockCount = otherGroup.rainbowBlockCount;
                anchorPoint = otherGroup.anchorPoint;
            }
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N; // 보드의 크기
    static int M; // 색상의 개수
    static int[][] A;
    static final int[] dy = {0, 0, 1, -1};
    static final int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;

        while (true) {
            Group maxGroup = findMaxGroup();
            if (maxGroup.getBlockSize() < 2) {
                break;
            }
            ret += removeMaxGroup(maxGroup.totalBlocks);
            goDown();
            rotate();
            goDown();
        }

        return ret;
    }

    /**
     * 일반 블록이 적어도 하나 있어야 한다. 일반 블록의 색은 모두 같아야 한다. 검은색 블록(-1)은 포함되면 안 되고, 무지개 블록(0)은 얼마나 들어있든 상관없다. 그룹에 속한 블록의 개수는 2보다
     * 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다.
     */
    private static Group findMaxGroup() {
        Group group = new Group(new Point(-1, -1), 0, new ArrayList<>());

        boolean[][] visited = new boolean[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x] || A[y][x] <= 0) {
                    continue;
                }
                Group otherGroup = bfs(y, x, visited);
                group.updateGroup(otherGroup);
            }
        }
        return group;
    }

    private static Group bfs(int sy, int sx, boolean[][] visited) {
        int rainbowBlockCnt = 0;
        List<Point> ret = new ArrayList<>();
        Stack<Point> stack = new Stack<>();

        Queue<Point> q = new LinkedList<>();
        visited[sy][sx] = true;
        q.add(new Point(sy, sx));

        while (!q.isEmpty()) {
            Point here = q.poll();
            ret.add(here);

            for (int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {
                    continue;
                }
                if (A[ny][nx] == A[sy][sx] || A[ny][nx] == 0) {
                    if (A[ny][nx] == 0) {
                        rainbowBlockCnt++;
                        stack.add(new Point(ny, nx));
                    }
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                }
            }

        }
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            visited[p.y][p.x] = false;
        }
        return new Group(new Point(sy, sx), rainbowBlockCnt, ret);
    }

    private static int removeMaxGroup(List<Point> maxGroup) {
        int ret = maxGroup.size() * maxGroup.size();
        for (Point p : maxGroup) {
            A[p.y][p.x] = -2;
        }
        return ret;
    }

    private static void goDown() {
        for (int y = N - 1; y >= 0; y--) {
            for (int x = N - 1; x >= 0; x--) {
                if (A[y][x] <= -1) {
                    continue;
                }
                for (int down = 1; down < N; down++) {
                    int ny = y + down;
                    if (ny < 0 || ny >= N || A[ny][x] >= -1) {
                        break;
                    }
                    A[ny][x] = A[ny - 1][x];
                    A[ny - 1][x] = -2;
                }
            }
        }
    }

    private static void rotate() {
        int[][] newA = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newA[N - 1 - j][i] = A[i][j];
            }
        }
        A = newA;
    }
}