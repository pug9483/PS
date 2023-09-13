import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, K;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 0;
        
        // [i..j] : 홀수 K개를 포함한 가장 긴 연속 부분 수열
        int l = 0, r = 0;
        int kCount = 0;
        while(r < N){
            if(kCount > K){
                if(A[l] % 2 == 1) kCount--;
                l++;
            }
            else{
                if(A[r] % 2 == 1) kCount++;
                r++;
            }
            if(kCount <= K)
                ret = Math.max(ret, r - l - kCount);
        }
        return ret;
    }
}
