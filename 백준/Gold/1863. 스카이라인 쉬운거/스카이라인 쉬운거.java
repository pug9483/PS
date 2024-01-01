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
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            A[i] = y;
        }
        System.out.println(solve());
    }
    
   public static int solve(){
       int ret = 0;
       Stack<Integer> stack = new Stack<>();
       
       for(int i = 0; i < N; i++){
           if(stack.isEmpty()){
               if(A[i] > 0)stack.add(A[i]);  
               continue;
           } 
           while(!stack.isEmpty()){
               int py = stack.pop();
               if(py > A[i]) ret++;
               else if(py < A[i]){
                   stack.add(py);
                   break;
               }
           }
           if(A[i] > 0) stack.add(A[i]);
       }
       ret += stack.size();
       return ret;
   }
}