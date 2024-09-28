import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] scores = new int[3];
    public static String X;
    public static String Y;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        X = br.readLine();
        Y = br.readLine();

        dp = new int[X.length()][Y.length()];
        for (int i = 0; i < X.length(); i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        System.out.println(solve(0, 0));
    }

    private static int solve(int i, int j) {
        if (i == X.length() && j == Y.length()) return 0;
        if (i == X.length()) return (Y.length() - j) * scores[1];
        if (j == Y.length()) return (X.length() - i) * scores[1];

        if (dp[i][j] != Integer.MIN_VALUE) return dp[i][j];

        int ret = Integer.MIN_VALUE;
        // i번 문자에 공백을 넣는 경우
        ret = Math.max(ret, scores[1] + solve(i, j + 1));
        // j번 문자에 공백을 넣는 경우
        ret = Math.max(ret, scores[1] + solve(i + 1, j));
        // 공백을 넣지 않는 경우
        if (X.charAt(i) == Y.charAt(j)) ret = Math.max(ret, scores[0] + solve(i + 1, j + 1));
        else ret = Math.max(ret, scores[2] + solve(i + 1, j + 1));

        return dp[i][j] = ret;
    }

}