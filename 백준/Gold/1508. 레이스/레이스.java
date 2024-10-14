import java.io.*;
import java.util.*;

// 문제 H : 단어 접두사 분석 시스템
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int K;
    static int[] A;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        long ret = solve();
        StringBuilder sb = new StringBuilder();
        int prev = Integer.MIN_VALUE;
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            if (A[i] >= prev + ret && cnt++ < M) {
                sb.append("1");
                prev = A[i];
            } else sb.append("0");
        }

        System.out.println(sb);
    }

    public static long solve() {
        long ret = 0;
        int left = 0, right = 1_000_000;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid)) { // 두 심판 사이의 거리가 mid일 때 가능한가?
                ret = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return ret;
    }

    private static boolean check(int distance) {
        int ret = 0;
        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < K; i++) {
            if (A[i] >= prev + distance) {
                ret++;
                prev = A[i];
            }
        }
        return ret >= M;
    }
}
