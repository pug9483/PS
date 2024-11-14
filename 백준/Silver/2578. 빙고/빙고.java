import java.io.*;
import java.util.*;

public class Main {
    static class Bingo{
        int[][] board;

        public Bingo(int[][] board) {
            this.board = board;
        }

        public void add(int num) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == num) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        public boolean hasLines() {
            int bingoCount = 0;
            // 가로
            for (int y = 0; y < board.length; y++) {
                boolean isBingo = true;
                for (int x = 0; x < board[0].length; x++) {
                    if (board[y][x] != -1) {
                        isBingo = false;
                        break;
                    }
                }
                if(isBingo) bingoCount++;
            }

            // 세로
            for (int x = 0; x < board[0].length; x++) {
                boolean isBingo = true;
                for (int y = 0; y < board[0].length; y++) {
                    if (board[y][x] != -1) {
                        isBingo = false;
                        break;
                    }
                }
                if(isBingo) bingoCount++;
            }

            // 대각선
            boolean isBingo = true;
            for (int size = 0; size < board.length; size++) {
                if (board[size][size] != -1) {
                    isBingo = false;
                    break;
                }
            }
            if(isBingo) bingoCount++;

            isBingo = true;
            for (int size = 0; size < board.length; size++) {
                if (board[size][board[0].length - 1 - size] != -1) {
                    isBingo = false;
                    break;
                }
            }
            if(isBingo) bingoCount++;
            return bingoCount >= 3;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N = 5;

    public static void main(String[] args) throws IOException {
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Bingo bingo = new Bingo(board);

        multi:
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                bingo.add(num);
                if(bingo.hasLines()){
                    System.out.println(i * N + j);
                    break multi;
                }
            }
        }
    }
}