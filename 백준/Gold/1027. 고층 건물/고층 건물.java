import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 0;
        for(int i = 0; i < N; i++){
            int sum = 0;
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                boolean flag = true;
                for(int k = Math.min(i, j) + 1; k < Math.max(i, j); k++){
                    double criterion = (1.0 * A[j] - A[i]) / (1.0 * j - i) * (1.0 * k - i) + A[i]; 
                    if(!check(k, criterion)){
                        flag = false;
                        break;
                    }
                }
                if(flag) sum++;
            }
            ret = Math.max(ret, sum);
        }
        return ret;
    }
    
    
    public static boolean check(int here, double criterion){
        return A[here] < (int)Math.ceil(criterion);    
    }
}