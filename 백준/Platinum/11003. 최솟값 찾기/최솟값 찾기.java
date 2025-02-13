import java.io.*;
import java.util.*;

public class Main {   
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int L;
    public static ArrayDeque<int[]> deque = new ArrayDeque<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            while(!deque.isEmpty() && deque.peekLast()[0] > num) deque.pollLast();
            deque.offer(new int[]{num, i});
            if(deque.peek()[1] < i - L + 1) deque.poll();
            sb.append(deque.peek()[0]).append(" ");
        }
        System.out.println(sb);
    }
}