import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M; //과자의 수, 조카의 수
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) 
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }   
    
    public static long solve(){
        long ret = 0;
        long left = 1, right = 1_000_000_000;
        while(left <= right){
            long mid = (left + right) / 2;
            if(check(mid)){
                ret = mid;
                left = mid + 1;
            }
            else right = mid - 1;
        }
        return ret;
    }
    
    public static boolean check(long d){
        int ret = 0;
        for(int i = 0; i < N; i++)
            ret += A[i] / d;
        return ret >= M;
    }
}