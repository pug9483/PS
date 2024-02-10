/* 정렬 + 그리디 알고리즘으로 해결
 * 1. 버스 노선의 정보를 (id, start, end)로 저장한다.
 * 1_1. start > end일 경우, 시작점을 한 번 지난 노선이기 때문에 end + N을 한다.
 * 1_2. start > end일 경우, 시작점을 지나는 end 중 가장 큰 값을 last 지역 변수에 저장한다. last값은 0으로 초기화한다.
 *      (last값을 저장하는 이유는 뒤에 설명한다)
 *
 * 2. 정렬
 * 2_1. 시작 정류장을 기준으로 오름차순 정렬한다.
 * 2_2. 시작 정류장이 같은 경우 도착 정류장 기준으로 내림차순 정렬한다.
 *
 * 3. 그리디
 * 3_1. 이전 노선의 도착 정류장보다 해당 노선의 도착 정류장이 작을 경우 삭제한다. 
 *      노선의 정보가 시작 정류장을 기준으로 오름차순이기 때문에 이전 노선보다 도착 정류장의 값이 작으면 항상 이전 정류장에 포함된다.
 * 3_2. last를 저장한 노선보다 도착 값이 적은 노선이 있으면 삭제한다.
 *      위의 3_1을 통해 노선들을 삭제한 후를 생각해보자. last값이 0이 아니라면 last값을 가지는 노선은 가장 마지막에 있는 노선이다.
 *      이 노선보다 start가 뒤에 있으면서 end < last인 노선은 3_1을 통해 없어졌고, end > last은 모순이기 때문에 불가능하다.
 *      마지막 노선보다 앞에 있으면서 end <= last % N인 노선들은 마지막 노선에 포함되는 노선이므로 삭제하면 된다.
 *      
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static class Node{
        int id, start, end;
        public Node(int id, int start, int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
    
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
	public static int M; 
    public static int last;
    public static List<Node> list = new ArrayList<>();
    public static List<Integer> retList = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        last = 0;
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(start > end){
                last = Math.max(last, end);
                end += N;
            }
            list.add(new Node(i+1, start, end));
        }
        list.sort((o1, o2) -> {
            int cmp = Integer.compare(o1.start, o2.start);
            return cmp == 0? Integer.compare(o2.end, o1.end) : cmp;
        });
        
        Deque<Node> dq = new ArrayDeque<>();
        for(Node node: list){
            if(dq.isEmpty() || dq.getLast().end < node.end) dq.add(node);
        }
        while(!dq.isEmpty() && dq.getFirst().end <= last){
            dq.removeFirst();
        }
        
        dq.stream().mapToInt(m -> m.id).sorted().forEach(s -> System.out.print(s + " "));
    }
}
