import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int[] children;
        boolean valid;

        public Node() {
            children = new int[2];
            Arrays.fill(children, -1);
        }
    }

    static class Trie {
        int root;
        List<Node> trie = new ArrayList<>();

        public Trie() {
            root = init();
        }

        public int init() {
            Node x = new Node();
            trie.add(x);
            return trie.size() - 1;
        }

        public void add(int num) {
            add(root, num, 31);
        }

        private void add(int node, int num, int bit) {
            if (bit == -1) {
                trie.get(node).valid = true;
                return;
            }

            int c = (num >> bit) & 1;
            if (trie.get(node).children[c] == -1) {
                int next = init();
                trie.get(node).children[c] = next;
            }
            add(trie.get(node).children[c], num, bit - 1);
        }

        public int query(int num) {
            return query(root, num, 31, 0);
        }

        private int query(int node, int num, int bit, int value) {
            if (bit == -1) {
                return value;
            }
            int c = (num >> bit) & 1;
            c = 1 - c;
            if (trie.get(node).children[c] == -1) {
                c = 1 - c;
            }
            value += (c << bit);

            int next = trie.get(node).children[c];
            return query(next, num,bit - 1, value);
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            int prefix = 0;
            trie.add(0);
            int ret = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                prefix ^= num;
                trie.add(prefix);
                ret = Math.max(ret, trie.query(prefix) ^ prefix);
            }
            sb.append(ret).append("\n");
        }
        System.out.print(sb);
    }
}
