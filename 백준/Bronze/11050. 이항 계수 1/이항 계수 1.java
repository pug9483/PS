import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(comb(N, K));
    }   
    
    public static long comb(int N, int K){
        long ret = 1;
        for(int cnt = 0; cnt < K; cnt++)
            ret *= N-cnt;
        for(int cnt = 0; cnt < K; cnt++)
            ret /= K-cnt;
        return ret;
    }
}