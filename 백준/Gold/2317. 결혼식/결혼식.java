import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static int[] lions;
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        lions = new int[K];

        int ret = 0;
        int[] pH = new int[2];
        int[] lH = new int[2];
        pH[0] = lH[0] = INF;

        for (int i = 0; i < K; i++) {
            lions[i] = Integer.parseInt(br.readLine());
            lH[0] = Math.min(lH[0], lions[i]);
            lH[1] = Math.max(lH[1], lions[i]);
        }


        for (int i = 0; i < N - K; i++) {
            int num = Integer.parseInt(br.readLine());
            pH[0] = Math.min(pH[0], num);
            pH[1] = Math.max(pH[1], num);
        }

        for (int i = 0; i < K-1; i++) {
            ret += Math.abs(lions[i + 1] - lions[i]);
        }

        if(pH[0] < lH[0]) ret += helper(pH[0], lH[0]);
        if(pH[1] > lH[1]) ret += helper(pH[1], lH[1]);

        System.out.println(ret);
    }

    private static int helper(int p, int l) {
        int ret = Math.abs(p - l) * 2;
        ret = Math.min(ret, Math.abs(lions[0] - p));
        ret = Math.min(ret, Math.abs(lions[K-1] - p));
        return ret;
    }
}
