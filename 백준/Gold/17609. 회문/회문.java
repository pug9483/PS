import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            String s = br.readLine();
            System.out.println(solve(s));
        }
    }

    private static int helper(int l, int r, String s, int count) {
        if(l >= r) return count;

        int ret = Integer.MAX_VALUE;
        if(s.charAt(l) == s.charAt(r)) ret = Math.min(ret, helper(l + 1, r - 1, s, count));
        if(count == 0) {
            if (s.charAt(l) == s.charAt(r - 1)) ret = Math.min(ret, helper(l, r - 1, s, 1));
            if (s.charAt(l + 1) == s.charAt(r)) ret = Math.min(ret, helper(l + 1, r, s, 1));
        }
        return ret;
    }
    private static int solve(String s) {
        return Math.min(2, helper(0, s.length() - 1, s, 0));
    }
}