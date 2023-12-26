import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int X;
    public static int[] pSum;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        pSum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            pSum[i] = pSum[i - 1] + num;
        }
        int ret = 0;
        int cnt = 0;
        for (int i = X; i <= N; i++) {
            int num = pSum[i] - pSum[i-X];
            if(ret < num){
                cnt = 1;
                ret = num;
            }
            else if(ret == num){
                cnt++;
            }
        }
        if(ret == 0) System.out.println("SAD");
        else{
            System.out.println(ret);
            System.out.println(cnt);
        }
    }
}