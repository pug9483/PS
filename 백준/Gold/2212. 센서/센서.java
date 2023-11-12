import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static int[] A;
    public static int[] B;
    
    // 집중국의 K개 있으면 두 센서 사이의 거리가 최대인 곳을 K-1개 없앨 수 있다.
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        
        int ret = 0;
        Arrays.sort(A);
        for(int i = 0; i < N-1; i++) B[i] = A[i+1] - A[i];
        Arrays.sort(B);
        for(int i = 0; i < N-K; i++) ret += B[i];
        System.out.println(ret);
    }
}