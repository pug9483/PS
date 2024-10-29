import java.io.*;
import java.util.*;

public class Main {
    static class Shark {
        int y, x, dir, sum;

        public Shark(int y, int x, int dir, int sum) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.sum = sum;
        }
    }

    static class Fish {
        int y, x, num, dir;
        boolean isAlive = true;

        public Fish(int y, int x, int num, int dir) {
            this.y = y;
            this.x = x;
            this.num = num;
            this.dir = dir;
        }

        public Fish(int y, int x, int num, int dir, boolean isAlive) {
            this.y = y;
            this.x = x;
            this.num = num;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N = 4;
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        int[][] board = new int[N][N];
        List<Fish> fishes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                fishes.add(new Fish(i, j, num, dir));
                board[i][j] = num;
            }
        }

        fishes.sort(Comparator.comparingInt(o -> o.num));
        Shark shark = init(board, fishes);
        dfs(shark, board, fishes);
        System.out.println(max);
    }

    private static void dfs(Shark shark, int[][] board, List<Fish> fishes) {
        if (max < shark.sum) {
            max = shark.sum;
        }

        fishes.forEach(e -> moveFish(e, board, fishes));
        for (int dist = 1; dist < 4; dist++) {
            int ny = shark.y + dy[shark.dir] * dist;
            int nx = shark.x + dx[shark.dir] * dist;

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || board[ny][nx] <= 0) {
                continue;
            }
            int[][] arrCopies = copyArr(board);
            List<Fish> fishCopies = copyFishes(fishes);

            arrCopies[shark.y][shark.x] = 0;
            Fish f = fishCopies.get(board[ny][nx] - 1);
            Shark newShark = new Shark(f.y, f.x, f.dir, shark.sum + f.num);
            f.isAlive = false;
            arrCopies[f.y][f.x] = -1;

            dfs(newShark, arrCopies, fishCopies);
        }
    }

    private static void moveFish(Fish fish, int[][] board, List<Fish> fishes) {
        if (!fish.isAlive) {
            return;
        }

        for (int dir = 0; dir < 8; dir++) {
            int nextDir = (fish.dir + dir) % 8;
            int ny = fish.y + dy[nextDir];
            int nx = fish.x + dx[nextDir];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || board[ny][nx] < 0) {
                continue;
            }
            board[fish.y][fish.x] = 0;

            if (board[ny][nx] == 0) {
                fish.y = ny;
                fish.x = nx;
            } else {
                Fish temp = fishes.get(board[ny][nx] - 1);
                temp.y = fish.y;
                temp.x = fish.x;
                board[fish.y][fish.x] = temp.num;

                fish.y = ny;
                fish.x = nx;
            }

            board[ny][nx] = fish.num;
            fish.dir = nextDir;
            return;
        }
    }

    private static Shark init(int[][] board, List<Fish> fishes) {
        Fish fish = fishes.get(board[0][0] - 1);
        Shark shark = new Shark(0, 0, fish.dir, fish.num);
        board[0][0] = -1;
        fish.isAlive = false;
        return shark;
    }

    static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> temp = new ArrayList<>();
        fishes.forEach(e -> temp.add(new Fish(e.y, e.x, e.num, e.dir, e.isAlive)));
        return temp;
    }
}