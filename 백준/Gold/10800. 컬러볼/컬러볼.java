import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int idx;
        int ci; // 공의 색깔
        int si; // 공의 크기

        public Node(int idx, int ci, int si) {
            this.idx = idx;
            this.ci = ci;
            this.si = si;
        }
    }

    static int N;
    static List<Node> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // sum - A[color] (단, prev != here일 때, A[color]에 prev값을 집어넣는다. List에 저장함)
    public static void  main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int ci = Integer.parseInt(st.nextToken()) - 1;
            int si = Integer.parseInt(st.nextToken());
            list.add(new Node(i, ci, si));
        }
        solve();
    }

    private static void solve() {
        list.sort((Comparator.comparingInt((Node o) -> o.si).thenComparingInt(o -> o.ci)));

        int sum = 0;
        int[] colorSum = new int[N]; // 현재까지 들어간 수들의 합
        int[] ret = new int[N];
        Queue<Node> waitings = new LinkedList<>(); // 크기가 같은 공들의 저장

        int prevSi = -1;
        for (Node here : list) {
            if (here.si != prevSi) {
                while (!waitings.isEmpty()) {
                    Node prevNode = waitings.poll();
                    sum += prevNode.si;
                    colorSum[prevNode.ci] += prevNode.si;
                }
            }
            ret[here.idx] = sum - colorSum[here.ci];
            waitings.add(here);
            prevSi = here.si;
        }


        StringBuilder sb = new StringBuilder();
        Arrays.stream(ret).forEach(o -> sb.append(o).append("\n"));
        System.out.println(sb);
    }
}
