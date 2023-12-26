import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int H;
    public static int N;
    public static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int ret = 0;
        for (int j = 1; j <= H; j++) {
            int left = 0, right = 0;
            for (int i = 0; i < N; i++) {
                if(A[i] >= j){
                    left = i;
                    break;
                }
            }
            for (int i = N-1; i >= 0; i--) {
                if(A[i] >= j){
                    right = i;
                    break;
                }
            }
            for (int i = left+1; i < right; i++) {
                if(A[i] < j) ret++;
            }
        }
        System.out.println(ret);
    }
}