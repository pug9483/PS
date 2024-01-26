import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int L;
    public static int[] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        A = new int[N+2];
        if(N > 0){
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++)
                A[i] = Integer.parseInt(st.nextToken());
        }
        A[0] = 0;
        A[N+1] = L;
        Arrays.sort(A);
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 0;
        int left = 1, right = L;
        while(left <= right){
            int mid = (left + right) / 2;
            if(check(mid)){
                ret = mid;
                right = mid-1;
            }
            else left = mid+1;
        }
        return ret;
    }
    
    public static boolean check(int distance){
        int ret = 0;
        for(int i = 1; i <= N+1; i++){
            int diff = A[i] - A[i-1];
            if(diff < distance) continue;
            ret += diff % distance == 0 ? (diff / distance) - 1: (diff / distance);
        }   
        return ret <= M;
    }
}