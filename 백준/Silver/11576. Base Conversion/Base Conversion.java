import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int f1, f2;
    public static int N;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        f1 = Integer.parseInt(st.nextToken());
        f2 = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        long sum = go(0);
        solve(sum);
        System.out.println(sb);
    }
    
    public static long go(int i){
        if(i == N) return 0;
        int num = A[i];
        return (int)Math.pow(f1, N - i - 1) * num + go(i+1);
    }
    
    public static void solve(long num){
        if(num == 0) return;
        long div = num / f2;
        long mod = num % f2;
        solve(div);
        sb.append(mod).append(" ");
    }
}