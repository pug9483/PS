import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int s;
        int e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static int N, M;
    public static List<Node> list;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();

            boolean[] visited = new boolean[N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list.add(new Node(s, e));
            }
            list.sort(Comparator.comparingInt(o -> o.e));

            int ret = 0;
            for (Node node : list) {
                for (int k = node.s; k <= node.e; k++) {
                    if(!visited[k]){
                        visited[k] = true;
                        ret++;
                        break;
                    }
                }
            }
            sb.append(ret).append("\n");
        }
        System.out.println(sb);
    }
}
