import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MOD = 1_000_000_007;
    private static long[][][] cache = new long[101][101][101];
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        cache[1][1][1] = 1;

        for (int n = 2; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    cache[n][l][r] = (cache[n][l][r] + cache[n-1][l-1][r]) % MOD;
                    cache[n][l][r] = (cache[n][l][r] + cache[n-1][l][r-1]) % MOD;
                    cache[n][l][r] = (cache[n][l][r] + (cache[n-1][l][r] * (n-2))) % MOD;
                }
            }
        }
        System.out.println(cache[N][L][R]);
    }
}
