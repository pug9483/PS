import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }

    private static int solve() {
        int left = 0, right = N;
        int ret = N;
        while(left <= right){
            int mid = (left + right) / 2;
            if(check(mid)){
                ret = mid;
                right = mid-1;
            }
            else left = mid+1;
        }
        return ret;
    }

    public static boolean check(int mid){
        int width = 0;
        for (int i = 0; i < M; i++) {
            if(A[i] - mid > width) return false;
            width = A[i] + mid;
        }
        return width >= N;
    }
}