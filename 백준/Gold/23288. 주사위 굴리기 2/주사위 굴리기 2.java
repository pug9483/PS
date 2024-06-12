import java.io.*;
import java.util.*;

public class Main {
    static class Dice{
        public int yPos;
        public int xPos;
        public int dir;
        public int[][] dice = new int[4][3];

        public Dice(int yPos, int xPos, int dir) {
            this.yPos = yPos;
            this.xPos = xPos;
            this.dir = dir;

            dice[0][1] = 2;
            dice[1][0] = 4;
            dice[1][1] = 1;
            dice[1][2] = 3;
            dice[2][1] = 5;
            dice[3][1] = 6;
        }

        public void moveRight() {
            int tmp = dice[3][1];
            dice[3][1] = dice[1][2];
            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = tmp;
            xPos++;
        }

        public void moveLeft() {
            int tmp = dice[1][0];
            dice[1][0] = dice[1][1];
            dice[1][1] = dice[1][2];
            dice[1][2] = dice[3][1];
            dice[3][1] = tmp;
            xPos--;
        }

        public void moveDown() {
            int tmp = dice[3][1];
            dice[3][1] = dice[2][1];
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = tmp;
            yPos++;
        }

        public void moveUp() {
            int tmp = dice[0][1];
            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
            dice[3][1] = tmp;
            yPos--;
        }

        public int getNumber() {
            return dice[3][1];
        }

        public void printDice() {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(dice[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N; // 세로 크기 (2 <= N <= 20)
    public static int M; // 가로 크기 (2 <= M <= 20)
    public static int K; // 이동 횟수 (1 <= K <= 1000)

    public static int[][] board;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;
        int k = 0;
        Dice dice = new Dice(0, 0, 1);

        while (k++ < K) {
            // 주사위가 이동방향으로 한 칸 굴러감.
            roll(dice);
            // 점수 획득
            ret += getScore(dice);
            // 이동방향 결정
            decideDir(dice);
        }
        return ret;
    }

    private static void decideDir(Dice dice) {
        int diceNum = dice.getNumber();
        int diceDir = dice.dir;
        int boardNum = board[dice.yPos][dice.xPos];

        if(diceNum > boardNum) diceDir = (diceDir + 1) % 4;
        else if(diceNum < boardNum) diceDir = (diceDir + 3) % 4;

        dice.dir = diceDir;
    }

    private static int getScore(Dice dice) {
        boolean[][] visited = new boolean[N][M];
        int sum =  getScoreHelper(dice.yPos, dice.xPos, board[dice.yPos][dice.xPos], visited);
        int num = board[dice.yPos][dice.xPos];
        return num * sum;
    }

    private static int getScoreHelper(int y, int x, int num, boolean[][] visited) {
        int ret = 1;
        visited[y][x] = true;
        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || board[ny][nx] != num) continue;
            ret += getScoreHelper(ny, nx, num, visited);
        }
        return ret;
    }

    private static void roll(Dice dice) {
        int diceDir = dice.dir;
        int yPos = dice.yPos;
        int xPos = dice.xPos;

        if (diceDir == 0) {
            if (yPos == 0) {
                dice.moveDown();
                diceDir = 2;
            }
            else{
                dice.moveUp();
            }
        } else if (diceDir == 1) {
            if (xPos == M-1) {
                dice.moveLeft();
                diceDir = 3;
            }
            else{
                dice.moveRight();
            }
        } else if (diceDir == 2) {
            if (yPos == N-1) {
                dice.moveUp();
                diceDir = 0;
            }
            else{
                dice.moveDown();
            }
        }else{
            if (xPos == 0) {
                dice.moveRight();
                diceDir = 1;
            }
            else{
                dice.moveLeft();
            }
        }

        dice.dir = diceDir;
    }

    private static int findIndex(int[] roll, int num) {
        for(int i = 0; i < roll.length; i++){
            if(roll[i] == num) return i;
        }
        return -1;
    }
}
