import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, D, K, C;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[N+K];
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++)
            A[N+i] = A[i];
        System.out.println(solve());
    }   
    
    public static int solve(){
        int ret = 0;
        int dishes = 0;
        int speciesCnt = 0;
        int l = 0, r = 0;
        int[] has = new int[D+1];
        
        while(r < N + K ){
            if(dishes > K){
                has[A[l]]--;
                if(has[A[l]] == 0) speciesCnt--;
                dishes--;
                l++;
            }
            else{
                if(has[A[r]] == 0) speciesCnt++;
                has[A[r]]++;
                dishes++;
                r++;
            }
            if(dishes <= K) ret = Math.max(ret, has[C] == 0? speciesCnt + 1 : speciesCnt);
        }
        return ret;
    }
}