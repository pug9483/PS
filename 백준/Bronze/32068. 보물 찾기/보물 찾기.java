import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            sb.append(solve(L, R, S)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int l, int r, int s) {
        int rCnt = (r - s) * 2;
        int lCnt = (s - l) * 2 + 1;

        return Math.min(lCnt, rCnt);
    }
}