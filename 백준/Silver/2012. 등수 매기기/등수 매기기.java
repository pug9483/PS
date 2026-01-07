import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
    public static int N;
    public static int[] A;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }

    private static long solve() {
        Arrays.sort(A);
        long totalDifference = 0;
        for (int i = 0; i < N; i++) {
            totalDifference += Math.abs(A[i] - (i + 1));
        }
        return totalDifference;
    }
}
