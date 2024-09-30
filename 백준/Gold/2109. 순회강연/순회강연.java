import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int p;
        int d;
        public Node(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static Node[] nodes; // [강연료, 날짜] : [p, d]배열

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(p, d);
        }
        System.out.println(solve());
    }

    public static int solve() {
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.d)); // 날짜 오름차순
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.p));

        // 불변식 : node의 마감기한 d는 항상 pq에 들어있는 node의 마감기한 d보다 크거나 같다.
        for (Node node : nodes) {
            if(pq.isEmpty() || pq.size() < node.d) pq.add(node);
            else{ // 가장 작은 것을 바꾸면 항상 최대가 된다.
                Node lowestPay = pq.poll();
                if(lowestPay.p < node.p){
                    pq.add(node);
                } else pq.add(lowestPay);
            }
        }

        return pq.stream().mapToInt(o -> o.p).sum();
    }
}