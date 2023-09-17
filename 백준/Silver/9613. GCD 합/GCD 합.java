import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = new int[N];
            for(int i = 0; i < N; i++)
                A[i] = Integer.parseInt(st.nextToken());
            long ret = 0;
            for(int i = 0; i < N; i++)
                for(int j = i+1; j < N; j++)
                    ret += gcd(A[i], A[j]);
            System.out.println(ret);
        }
    }

    public static long gcd(int a, int b){
        return b == 0L? a : gcd(b, a%b);
    }
} 