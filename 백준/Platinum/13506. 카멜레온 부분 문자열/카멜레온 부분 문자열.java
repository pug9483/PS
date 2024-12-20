import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        int[] pi = getPartialMatch(S);
        System.out.println(solve(S, pi));

    }

    private static String solve(String S, int[] pi) {
        int[] count = new int[S.length() + 1];

        for (int i = 0; i < S.length() - 1; i++) {
            count[pi[i]]++;
        }

        for (int i = S.length(); i != 0; i = pi[i - 1]) {
            if (count[i] >= 1) {
                return S.substring(0, i);
            }
        }
        return "-1";
    }

    public static int[] getPartialMatch(String N){
        int m = N.length();
        int[] pi = new int[m];
        int begin = 1;
        int matched = 0;

        while (begin + matched < m) {
            if (N.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            }
            else{
                if(matched == 0) begin++;
                else{
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return pi;
    }
}
