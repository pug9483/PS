import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        int order;
        int count = 0;
    }

    static class TrieNode {
        Node rootNode = new Node();
        int depth = 0;
        String prefix;
        int firstOrder = Integer.MAX_VALUE;

        public TrieNode() {
        }

        public void insert(int order, String str) {
            Node node = this.rootNode;
            for (int i = 0; i < str.length(); i++){
                if (node.children.containsKey(str.charAt(i))) {
                    Node child = node.children.get(str.charAt(i));
                    child.count++;
                    if(child.count >= 2 && i+1 > depth){
                        prefix = str.substring(0, i+1);
                        depth = i+1;
                        firstOrder = child.order;
                    } else if (child.count >= 2 && i + 1 == depth && child.order < firstOrder) {
                        prefix = str.substring(0, i+1);
                        depth = i+1;
                        firstOrder = child.order;
                    }
                    node = child;
                }
                else{
                    Node child = new Node();
                    child.order = order;
                    child.count++;
                    node.children.put(str.charAt(i), child);
                    node = child;
                }
            }
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        TrieNode trieNode = new TrieNode();
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        int order = 1;
        for (String s : set) {
            trieNode.insert(order++, s);
        }

        int depth = trieNode.depth;
        String prefix = trieNode.prefix;

        String first = null;
        String second = null;
        for (String s : set) {
            if (s.length() >= depth && s.substring(0, trieNode.depth).equals(prefix)) {
                if(first == null)
                    first = s;
                else if(second == null)
                    second = s;
            }
        }
        System.out.println(first);
        System.out.println(second);
    }
}