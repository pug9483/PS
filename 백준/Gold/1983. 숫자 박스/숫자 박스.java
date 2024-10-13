import java.io.*;
import java.util.*;

// 문제 H : 단어 접두사 분석 시스템
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] A;
    static int[] B;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        List<Integer> A = new ArrayList<>(List.of(0));
        List<Integer> B = new ArrayList<>(List.of(0));

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num != 0) A.add(num);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num != 0) B.add(num);
        }

        int[][][] dp = new int[N + 1][A.size()][B.size()];

        for (int size = 1; size <= N; size++) {
            for (int i = 1; i < A.size(); i++) {
                for (int j = 1; j < B.size(); j++) {
                    if (i > size || j > size) continue;
                    int curr = dp[size - 1][i - 1][j - 1] + (A.get(i) * B.get(j));
                    if (size >= i + 1) curr = Math.max(curr, dp[size - 1][i][j - 1]);
                    if (size >= j + 1) curr = Math.max(curr, dp[size - 1][i - 1][j]);
                    dp[size][i][j] = curr;
                }
            }
        }
        System.out.println(dp[N][A.size() - 1][B.size() - 1]);
    }
}
