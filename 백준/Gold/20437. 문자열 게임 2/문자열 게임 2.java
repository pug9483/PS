import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int K;
    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s = br.readLine();
            K = Integer.parseInt(br.readLine());
            if(K == 1){
                System.out.println("1 1");
                continue;
            }
            int a = getShortestLen(s);
            int b = getLongestLen(s);
            if(a == INF && b == 0) System.out.println(-1);
            else System.out.println(a + " " + b);
        }
    }

    private static int getShortestLen(String s) {
        int ret = INF;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;

        for (int i = 0; i < s.length(); i++) {
            if(count[s.charAt(i) - 'a'] < K) continue;
            int sum = 1;
            for (int j = i+1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)) sum++;
                if(sum == K){
                    ret = Math.min(ret, j - i + 1);
                    break;
                }
            }
        }
        return ret;
    }

    private static int getLongestLen(String s) {
        int ret = 0;

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;

        for (int i = 0; i < s.length(); i++) {
            if(count[s.charAt(i) - 'a'] < K) continue;
            int sum = 1;
            for (int j = i+1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)) sum++;
                if(sum == K){
                    ret = Math.max(ret, j - i + 1);
                    break;
                }
            }
        }
        return ret;
    }
}