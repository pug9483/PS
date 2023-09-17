import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static long N;
    public static long M;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        int two = 0, five = 0;
        
        two = calc(N, M, 2);
        five = calc(N, M, 5);
        System.out.println(Math.min(two, five));
    }
    
    public static int calc(long N, long M, int num){
        int ret = 0;
        
        for(long i = num; i <= N; i *= num) ret += N / i;
        for(long i = num; i <= M; i *= num) ret -= M / i;
        for(long i = num; i <= N-M; i *= num) ret -= (N-M) / i;
        
        return ret;
    }
} 