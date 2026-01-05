import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int currentSum = 0;
        for (int i = 0; i < K; i++) {
            currentSum += A[i];
        }
        int maxSum = currentSum;
        for (int i = K; i < N; i++) {
            currentSum += A[i] - A[i - K];
            maxSum = Math.max(maxSum, currentSum);
        }
        System.out.println(maxSum);
    }
}
