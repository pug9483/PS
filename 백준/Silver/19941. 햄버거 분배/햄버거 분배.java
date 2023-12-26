import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static char[] A;
    public static boolean[] eaten;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = br.readLine().toCharArray();
        eaten = new boolean[N];
        int ret = 0;
        for (int i = 0; i < N; i++) {
            char type = A[i];
            boolean ok = false;
            if(type == 'H') continue;
            for (int j = Math.max(0, i-K); j < i; j++) {
                if(A[j] == 'H' && !eaten[j]){
                    eaten[j] = true;
                    ok = true;
                    break;
                }
            }
            if(ok) {
                ret++;
                continue;
            }
            for (int j = i+1; j < Math.min(i+K+1, N); j++) {
                if(A[j] == 'H' && !eaten[j]){
                    eaten[j] = true;
                    ok = true;
                    break;
                }
            }
            if(ok) ret++;
        }
        System.out.println(ret);
    }
}