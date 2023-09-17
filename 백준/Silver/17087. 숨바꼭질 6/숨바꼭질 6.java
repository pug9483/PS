import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, S;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }

    public static int solve(){
        int ret = gcd(Math.abs(A[0] - S), 0);
        
        for(int i = 1; i < N; i++)
            ret = gcd(ret, Math.abs(A[i] - S));            
        return ret;
    }
    
    public static int gcd(int a, int b){
        return b == 0? a : gcd(b, a%b);
    }
} 