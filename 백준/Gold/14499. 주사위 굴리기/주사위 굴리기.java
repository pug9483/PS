import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] dice = new int[4][3];
    public static int[][] board;
    public static int N, M, y, x, K;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (checkIsRoll(num, y, x)) {
                int[] point = rollDice(num, y, x);
                y = point[0];
                x = point[1];
                if (board[y][x] == 0) {
                    board[y][x] = dice[3][1];
                } else {
                    dice[3][1] = board[y][x];
                    board[y][x] = 0;
                }
                System.out.println(dice[1][1]);
            }
        }
    }

    private static boolean checkIsRoll(int num, int y, int x) {
        switch (num) {
            case 1:
                if (x + 1 < M) return true;
                break;
            case 2:
                if (x - 1 >= 0) return true;
                break;
            case 3:
                if (y - 1 >= 0) return true;
                break;
            case 4:
                if (y + 1 < N) return true;
                break;
        }
        return false;
    }

    private static int[] rollDice(int num, int y, int x) {
        int tmp = 0;
        switch (num) {
            case 1:
                tmp = dice[3][1];
                dice[3][1] = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = tmp;
                x++;
                break;
            case 2:
                tmp = dice[3][1];
                dice[3][1] = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = tmp;
                x--;
                break;
            case 3:
                tmp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = tmp;
                y--;
                break;
            case 4:
                tmp = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = tmp;

                y++;
                break;
        }
        return new int[]{y, x};
    }
}
