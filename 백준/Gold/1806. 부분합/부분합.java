import java.io.*;
import java.util.*;

public class Main {   
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N; // 크기
    public static int S; // 합
    public static int[] A;
    public static final int INF = 987654321;
    
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
        int ret = INF;
        int sum = 0;
        int left = 0, right = 0;
        while(right < N){
            sum += A[right];
            while(left <= right && sum >= S){
                ret = Math.min(ret, (right - left + 1));
                sum -= A[left++];
            }
            right++;
        }   
        return ret == INF? 0 : ret; 
    }
}