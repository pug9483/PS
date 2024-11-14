import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static class Node{
        BigInteger num;
        BigInteger bright;

        public Node(BigInteger num, BigInteger bright) {
            this.num = num;
            this.bright = bright;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long L;
    static int N, K;
    static BigInteger[] A;
    static BigInteger[] next = new BigInteger[]{BigInteger.ONE.multiply(BigInteger.valueOf(-1)), BigInteger.ONE};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new BigInteger[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = new BigInteger(st.nextToken());
        }
        solve();
    }

    private static void solve() {
        Queue<Node> q = new LinkedList<>();
        Set<BigInteger> visited = new HashSet<>();

        for (int i = 0; i < N; i++) {
            q.add(new Node(A[i], BigInteger.ZERO));
            visited.add(A[i]);
        }

        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            Node here = q.poll();
            sb.append(here.bright).append("\n");
            K--;
            if(K == 0) break;
            for (int i = 0; i < 2; i++) {
                BigInteger nextNum = here.num.add(next[i]);
                if(visited.contains(nextNum) || nextNum.compareTo(BigInteger.valueOf(0)) < 0 || nextNum.compareTo(BigInteger.valueOf(L)) > 0) continue;
                visited.add(nextNum);
                q.add(new Node(nextNum, here.bright.add(BigInteger.valueOf(1))));
            }
        }
        System.out.print(sb);
    }
}