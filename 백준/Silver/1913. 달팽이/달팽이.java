import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        solve(0, 0, N * N, N);
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int[] ret = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == M) {
                    ret[0] = i+1;
                    ret[1] = j+1;
                }
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(ret[0]).append(" ").append(ret[1]);
        System.out.println(sb);
    }

    private static void solve(int fy, int fx, int num, int size) {
        if (size <= 1) {
            board[fy][fx] = 1;
            return;
        }
        board[fy][fx] = num--;

        for (int y = fy + 1; y < fy + size; y++) {
            board[y][fx] = num--;
        }

        for (int x = fx + 1; x < fx + size; x++) {
            board[fy + size - 1][x] = num--;
        }

        for (int y = fy + size - 2; y >= fy; y--) {
            board[y][fx + size - 1] = num--;
        }

        for (int x = fx + size - 2; x > fx; x--) {
            board[fy][x] = num--;
        }

        solve(fy + 1, fx + 1, num, size - 2);
    }
}