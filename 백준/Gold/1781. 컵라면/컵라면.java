import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int deadline;
        int count;

        public Node(int deadline, int count) {
            this.deadline = deadline;
            this.count = count;
        }
    }

    public static int N;
    public static List<Node> list = new ArrayList<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            list.add(new Node(deadline, count));
        }
        System.out.println(solve());
    }

    public static int solve() {
        int ret = 0;
        list.sort((Comparator.comparingInt(o -> o.deadline)));
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));
        pq.add(list.get(0));

        for (int i = 1; i < N; i++) {
            Node here = list.get(i);
            Node lowest = pq.peek();
            if(pq.size() < here.deadline) pq.add(here);
            else if(lowest.count < here.count){
                pq.poll();
                pq.add(here);
            }
        }

        return pq.stream().mapToInt(o -> o.count).sum();
    }
}
