import java.io.*;
import java.util.*;

public class Main { 
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
            
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] A;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][2];
        int maxValue = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            A[i][0] = x;
            A[i][1] = y;
            maxValue = Math.max(maxValue, A[i][1]);
        }
        System.out.println(maxValue == 0? 0 : solve());
    }
    
   public static int solve(){
       int ret = 0;
       Stack<Node> stack = new Stack<>();
       for(int i = 0; i < N; i++){
           int x = A[i][0];
           int y = A[i][1];
           if(stack.isEmpty()){
               stack.add(new Node(x, y));  
               continue;
           } 
           Node prev = stack.peek();
           if(prev.y < y) stack.add(new Node(x, y));
           else{
               while(!stack.isEmpty()){
                   Node node = stack.pop();
                   if(node.y == 0) continue;
                   if(node.y < y) {
                       stack.add(node);
                       break;
                   }
                   if(node.y > y) ret++;
               }
               stack.add(new Node(x, y));
           }
       }
       ret += stack.size();
       return ret;
   }
}