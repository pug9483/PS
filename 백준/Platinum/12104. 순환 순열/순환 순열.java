import java.io.*;
import java.util.*;

public class Main {
    static String A;
    static String B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine();
        B = br.readLine();

        B = B + B.substring(0, B.length() - 1);
        List<Integer> ret = kmp(B, A);
        System.out.println(ret.size());
    }

    public static List<Integer> kmp(String H, String N){
        int n = H.length();
        int m = N.length();
        List<Integer> ret = new ArrayList<>();

        int[] pi = getPartialMatch(N);
        int begin = 0, matched = 0;

        while(begin <= n - m){
            if (matched < m && H.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                if (matched == m) {
                    ret.add(begin);
                }
            }
            else{
                if (matched == 0) {
                    matched++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return ret;
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