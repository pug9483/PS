import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static long M;
    public static long[] A;
    public static long ret = 0;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Long.parseLong(st.nextToken());
        System.out.println(solve());
    }   
    
    public static long solve(){
        for(int cnt = 1; cnt <= N; cnt++){
            go(0, 1, cnt, cnt % 2);
        }
        return ret;
    }
        
    // type: 0 -> cnt가 짝수개이므로 빼줘야 한다.
    // type: 1 -> cnt가 홀수개이므로 더해줘야 한다.
    public static void go(int here, long lcm, int cnt, int type){
        if(here == N){
            if(cnt == 0){
                if(type % 2 == 0) ret -= M / lcm;
                else ret += M / lcm;
            }
            return;
        }
        go(here+1, getLcm(lcm, A[here]), cnt-1, type);
        go(here+1, lcm, cnt, type);
    }
    
    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a%b);
    }
    
    public static long getLcm(long a, long b){
        return gcd(a, b) * a * b;
    }
}