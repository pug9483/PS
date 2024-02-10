import java.io.*;
import java.util.*;

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
    public static List<Node> list = new ArrayList<>();
    public static List<Integer> ret = new ArrayList<>();
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(start < end)
                list.add(new Node(i+1, start, end));
            else{
                list.add(new Node(i+1, start, end + N));
                list.add(new Node(i+1, start - N, end));
            }
        }
        list.sort((o1, o2) -> {
            int cmp = Integer.compare(o1.start, o2.start);
            return cmp == 0? Integer.compare(o2.end, o1.end) : cmp;
        });
        
        for(int i = 0; i < list.size(); i++){
            Node here = list.get(i);
            ret.add(here.id);
            int j = i+1;
            while(j < list.size()){
                Node next = list.get(j);
                if(!(here.start <= next.start && here.end >= next.end)) break;
                j++;
            }
            i = j-1;
        }
        ret.stream().sorted().distinct().forEach(s -> System.out.printf("%d ", s));
    }
}