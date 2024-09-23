import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static int[][] board;
    public static boolean[] killed;
    public static int dst;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        board = new int[N][N];
        killed = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dst = Integer.parseInt(br.readLine());
        System.out.println(solve(N, 0));
    }

    public static int solve(int cnt, int day) {
        if (killed[dst] || cnt == 1) {
            return day;
        }

        int ret = 0;
        if(cnt % 2 == 0) {
            for (int i = 0; i < N; i++) {
                if(killed[i] || i == dst) continue;
                killed[i] = true;
                calc(i, 1);
                ret = Math.max(ret, solve(cnt - 1, day + 1));
                killed[i] = false;
                calc(i, -1);
            }
        }
        else {
            int killedIdx = killAtDaytime();
            killed[killedIdx] = true;
            ret = Math.max(ret, solve(cnt - 1, day));
            killed[killedIdx] = false;
        }
        return ret;
    }

    private static int killAtDaytime() {
        int maxIndex = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (!killed[i] && maxValue < A[i]) {
                maxValue = A[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static void calc(int i, int sign) {
        for (int j = 0; j < N; j++) {
            A[j] += (sign * board[i][j]);
        }
    }
}