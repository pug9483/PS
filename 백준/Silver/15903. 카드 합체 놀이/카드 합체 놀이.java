import java.io.*;
import java.util.*;

public class Main {  
    static class Line{
        int src, dst;
        public Line(int src, int dst){
            this.src = src;
            this.dst = dst;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static PriorityQueue<Long> pq = new PriorityQueue<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            pq.add(Long.parseLong(st.nextToken()));
        while(M-- > 0){
            long first = pq.poll();
            long second = pq.poll();
            pq.add(first+second);
            pq.add(first+second);
        }
        System.out.println(pq.stream().reduce(0L, Long::sum));
    }
}