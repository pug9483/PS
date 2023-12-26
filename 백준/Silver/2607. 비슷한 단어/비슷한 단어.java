import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
        int ret = 0;
        int[] cmp1 = getCount(A[0]);
        for (int i = 1; i < N; i++) {
            int[] cmp2 = getCount(A[i]);
            if(check(cmp1, cmp2)) ret++;
        }
        System.out.println(ret);
    }

    public static int[] getCount(String s) {
        int[] cmp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cmp[c-'A']++;
        }
        return cmp;
    }

    public static boolean check(int[] cmp1, int[] cmp2) {
        int len1 = cmp1.length;
        int len2 = cmp2.length;
        if(Math.abs(len1 - len2) > 1) return false;
        int diff1 = 0;
        int diff2 = 0;
        for (int i = 0; i < 26; i++) {
            if (cmp1[i] > cmp2[i])
                diff1 += cmp1[i] - cmp2[i];
            else if(cmp1[i] < cmp2[i])
                diff2 += cmp2[i] - cmp1[i];
        }
        return diff1 <= 1 && diff2 <= 1;
    }
}