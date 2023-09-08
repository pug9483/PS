import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solve());
    }   
    
    public static int solve(){
        int ret = 0;
        
        for(int i = N-1; i > 0; i--){
            if(A[i] <= A[i-1]){
                int gap = A[i-1] - A[i] + 1;
                ret += gap;
                A[i-1] -= gap;
            }
        }
        return ret;
    }
}