import java.io.*;
import java.util.*;

public class Main { 
    static class Node{
        int index, num;
        public Node(int index, int num){
            this.index = index;
            this.num = num;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static int[] A;
    public static int[] B = new int[100001];
    
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
       int l = 0;
       for(int r = 0; r < N; r++){
           if(B[A[r]] < K){
               B[A[r]]++;
           }
           else if(B[A[r]] >= K){
               while(l < r && A[l] != A[r]){
                   B[A[l]]--;
                   l++;
               }
               l++;
           }
           ret = Math.max(ret, r - l + 1);
       }
       return ret;
   }
}