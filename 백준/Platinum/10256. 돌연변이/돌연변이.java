import java.util.*;

class Node {
    int[] children;
    int pi;
    int cnt;

    public Node() {
        children = new int[4];
        Arrays.fill(children, -1);
        pi = -1;
        cnt = 0;
    }
}

public class Main {
    static List<Node> trie;

    public static int toNode(char x) {
        switch (x) {
            case 'A': return 0;
            case 'T': return 1;
            case 'G': return 2;
            case 'C': return 3;
            default: return -1;
        }
    }

    public static int init() {
        Node node = new Node();
        trie.add(node);
        return trie.size() - 1;
    }

    public static void add(int node, String s, int index) {
        if (index == s.length()) {
            trie.get(node).cnt = 1;
            return;
        }
        int c = toNode(s.charAt(index));
        if (trie.get(node).children[c] == -1) {
            int next = init();
            trie.get(node).children[c] = next;
        }
        add(trie.get(node).children[c], s, index + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            trie = new ArrayList<>();
            int n = sc.nextInt();
            int m = sc.nextInt();
            String s = sc.next();
            String p = sc.next();

            int root = init();
            List<String> a = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = i; j < m; j++) {
                    StringBuilder p2 = new StringBuilder(p);
                    String reversed = new StringBuilder(p.substring(i, j + 1)).reverse().toString();
                    p2.replace(i, j + 1, reversed);
                    a.add(p2.toString());
                }
            }

            a = new ArrayList<>(new HashSet<>(a));
            Collections.sort(a);

            for (String str : a) {
                add(root, str, 0);
            }

            Queue<Integer> q = new LinkedList<>();
            trie.get(root).pi = root;
            q.add(root);

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int next = trie.get(cur).children[i];
                    if (next == -1) continue;

                    if (cur == root) {
                        trie.get(next).pi = root;
                    } else {
                        int x = trie.get(cur).pi;
                        while (x != root && trie.get(x).children[i] == -1) {
                            x = trie.get(x).pi;
                        }
                        if (trie.get(x).children[i] != -1) {
                            x = trie.get(x).children[i];
                        }
                        trie.get(next).pi = x;
                    }

                    int pi = trie.get(next).pi;
                    trie.get(next).cnt += trie.get(pi).cnt;
                    q.add(next);
                }
            }

            int ans = 0;
            int node = root;
            for (int i = 0; i < s.length(); i++) {
                int c = toNode(s.charAt(i));
                while (node != root && trie.get(node).children[c] == -1) {
                    node = trie.get(node).pi;
                }
                if (trie.get(node).children[c] != -1) {
                    node = trie.get(node).children[c];
                }
                ans += trie.get(node).cnt;
            }

            System.out.println(ans);
        }
        sc.close();
    }
}
