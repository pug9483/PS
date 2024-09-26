import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int C;
    public static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[N];

        int l = Integer.MAX_VALUE;
        int r = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            r = Math.max(A[i], r);
        }

        Arrays.sort(A);
        System.out.println(solve(0, r));
    }

    public static int solve(int left, int right) {
        int ret = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid)) {
                ret = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        return ret;
    }

    // 거리가 distance일 때, 공유기를 C개 이상 설치할 수 있는지 여부를 반환한다.
    private static boolean check(int distance) {
        int ret = 0;
        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (A[i] >= distance + prev) {
                ret++;
                prev = A[i];
            }
        }
        return ret >= C;
    }
}
