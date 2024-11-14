import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int num;
        int bright;

        public Node(int num, int bright) {
            this.num = num;
            this.bright = bright;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Long L;
    static int N, K;
    static int[] A;
    static int[] next = new int[]{-1, 1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }

    private static void solve() {
        Queue<Node> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < N; i++) {
            q.add(new Node(A[i], 0));
            visited.add(A[i]);
        }

        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            Node here = q.poll();
            sb.append(here.bright).append("\n");
            K--;
            if(K == 0) break;
            for (int i = 0; i < 2; i++) {
                int nextNum = here.num + next[i];
                if(visited.contains(nextNum) || nextNum < 0 || nextNum > L) continue;
                visited.add(nextNum);
                q.add(new Node(nextNum, here.bright + 1));
            }
        }
        System.out.print(sb);
    }
}