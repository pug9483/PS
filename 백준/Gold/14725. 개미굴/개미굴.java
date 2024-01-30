import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {  
    static class Node{
        Map<String, Node> children = new HashMap<>();
    }

    static class TrieNode{
        Node rootNode = new Node();
        public TrieNode(){};
        
        public  void insert(List<String> list){
            Node node = this.rootNode;
            for(int i = 0; i < list.size(); i++){
                String str = list.get(i);
                if(!node.children.containsKey(str));
                    node = node.children.computeIfAbsent(str, key -> new Node());     
            }
        }
    }
    
    public static void printInAscendingOrder(Node node, int depth){
        List<String> list = node.children
            .keySet()
            .stream()
            .sorted()
            .collect(Collectors.toList());
        for(String s : list){
            for(int i = 0; i < depth; i++) System.out.print("--");
            System.out.println(s);
            printInAscendingOrder(node.children.get(s), depth+1);
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Node> list = new ArrayList<>();
    public static TrieNode trieNode = new TrieNode();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            List<String> list = new ArrayList<>();
            for(int j = 0; j < size; j++){
                list.add(st.nextToken());
                trieNode.insert(list);
            }
        }  
        printInAscendingOrder(trieNode.rootNode, 0);
    }    
}