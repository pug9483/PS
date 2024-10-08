import java.io.*;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class Main {
    static class Node{
        int idx;
        int color; // 공의 색깔
        int size; // 공의 크기

        public Node(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    static int N;
    static List<Node> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        list.sort(comparingInt(o -> o.size));

        int totalSum = 0;
        int[] colorSum = new int[N]; // 현재까지 들어간 수들의 합
        int[] ret = new int[N];


        int j = 0;
        for (int i = 0; i < N; i++) {
            Node here = list.get(i);

            while (list.get(j).size < here.size) {
                totalSum += list.get(j).size;
                colorSum[list.get(j).color] += list.get(j).size;
                j++;
            }

            ret[here.idx] = totalSum - colorSum[here.color];
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(ret).forEach(o -> sb.append(o).append("\n"));
        System.out.println(sb);
    }
}
