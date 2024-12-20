import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        int[] pi = getPartialMatch(S);
        solve(S, pi);
    }

    private static void solve(String S, int[] pi) {
        int[] count = new int[S.length() + 1];

        for (int i = 0; i < S.length(); i++) {
            count[pi[i]]++;
        }

        for (int i = S.length(); i > 0; i--) {
            count[pi[i - 1]] += count[i];
        }

        List<int[]> list = new ArrayList<>();
        for (int i = S.length(); i != 0; i = pi[i - 1]) {
            list.add(new int[]{i, count[i] + 1});
        }

        list.sort(Comparator.comparingInt(o -> o[0]));
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (int[] A : list) {
            sb.append(A[0]).append(" ").append(A[1]).append("\n");
        }
        System.out.print(sb);
    }

    public static int[] getPartialMatch(String N) {
        int m = N.length();
        int[] pi = new int[m + 1];
        int begin = 1;
        int matched = 0;

        while (begin + matched < m) {
            if (N.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return pi;
    }
}
