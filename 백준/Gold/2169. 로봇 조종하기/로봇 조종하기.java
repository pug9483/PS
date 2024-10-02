import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행의 수
        M = Integer.parseInt(st.nextToken()); // 열의 수

        grid = new int[N][M]; // 격자 정보를 저장할 배열
        dp = new int[N][M]; // DP 배열

        // 격자 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 행 처리
        dp[0][0] = grid[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 나머지 행 처리
        for (int i = 1; i < N; i++) {
            int[] left = new int[M];
            int[] right = new int[M];

            // 아래로부터 오는 값
            for (int j = 0; j < M; j++) {
                dp[i][j] = dp[i - 1][j] + grid[i][j];
            }

            // 왼쪽에서 오는 값
            left[0] = dp[i][0];
            for (int j = 1; j < M; j++) {
                left[j] = Math.max(left[j - 1] + grid[i][j], dp[i][j]);
            }

            // 오른쪽에서 오는 값
            right[M - 1] = dp[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] + grid[i][j], dp[i][j]);
            }

            // 현재 dp 값 갱신
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        // 결과 출력
        System.out.println(dp[N - 1][M - 1]);
    }
}
