import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String[] A;
    public static int K;
    public static long[][] dp;
    public static int[][] mods;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());
        dp = new long[K][1 << N];
        mods = new int[K][N];

        for (int i = 0; i < K ; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(mods[i], -1);
        }

        long ret = shortestPath(0, 0);
        if(ret == 0) System.out.println("0/1");
        else{
            long gcd = getGcd(factorial(N), ret);
            System.out.println((ret / gcd) + "/" + (factorial(N) / gcd));
        }
    }

    private static long factorial(int n) {
        long ret = 1;
        for (int i = 2; i <= n; i++) {
            ret *= i;
        }
        return ret;
    }

    public static long shortestPath(int here, int visited) {
        if(visited == ((1 << N) - 1)) return here == 0 ? 1 : 0;

        if(dp[here][visited] != -1) return dp[here][visited];

        long ret = 0;

        for (int idx = 0; idx < N; idx++) {
            if ((visited & (1 << idx)) != 0) continue;
            ret += shortestPath(getMod(here, idx), visited | (1 << idx));
        }
        return dp[here][visited] = ret;
    }

    public static long getGcd(long n, long m) {
        if(m == 0) return n;
        return getGcd(m, n % m);
    }

    public static int getMod(int mod, int idx) {
        if(mods[mod][idx] != -1) return mods[mod][idx];

        int num = mod;
        for (int i = 0; i < A[idx].length(); i++) {
            num *= 10;
            num += (A[idx].charAt(i) - '0');
            num %= K;
        }
        return mods[mod][idx] = num;
    }
}