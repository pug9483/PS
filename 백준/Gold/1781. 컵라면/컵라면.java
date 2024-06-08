import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair{
        int deadLine;
        int count;
        
        public Pair(int deadLine, int count){
            this.deadLine = deadLine;
            this.count = count;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Pair> list = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            list.add(new Pair(deadLine, count));
        }
        
        int ret = 0;
        
        list.sort((o1, o2) -> {
            int cmp = Integer.compare(o1.deadLine, o2.deadLine);
            return cmp == 0 ? Integer.compare(o2.count, o1.count) : cmp; 
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        
        for(Pair pair : list){
            int deadLine = pair.deadLine;
            int count = pair.count;
            // pq의 size가 0이면 추가하기
            if(pq.isEmpty() || pq.size() < deadLine){
                pq.add(count);
            }
            // pq의 첫번째 값보다 count의 값이 작으면 넘어가기
            else if(pq.peek() > count) continue;
            // pq의 첫번째 값보다 count의 값이 크면 교체하기
            else if(pq.peek() < count){
                pq.poll();
                pq.add(count);
            }
        }
        
        while(!pq.isEmpty()) ret += pq.poll();
            
        System.out.println(ret);
    }
}