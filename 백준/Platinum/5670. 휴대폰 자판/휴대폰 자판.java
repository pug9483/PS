import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int terminal = -1;
        
        public void insert(String s, int here, int id){
            if(here == s.length()) terminal = id;
            else{
                char c = s.charAt(here);
                children.computeIfAbsent(c, key -> new TrieNode());
                children.get(c).insert(s, here+1, id);
            }
        }
        
        public int find(String s, int here, int id){
            if(terminal == id) return 0;
            TrieNode child = children.get(s.charAt(here));
            int ret = child.find(s, here+1, id);
            return children.size() == 1 && terminal == -1? ret : 1 + ret;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static TrieNode trie;
    public static String[] input;
    
	public static void main(String[] args) throws Exception {
        while(true){
            String S = br.readLine();
            if(S == null) break;
            trie = new TrieNode();
            int N = Integer.parseInt(S);
            input = new String[N];
            for(int i = 0; i < N; i++){
                String str = br.readLine();
                input[i] = str;
                trie.insert(str, 0, i);
            }
            trie.terminal = -1;
            int ret = 0;
            for(int i = 0; i < N; i++){
                char first = input[i].charAt(0);
                TrieNode nTrie = trie.children.get(first);
                int num = 1 + nTrie.find(input[i], 1, i);
                ret += num;
            }
            System.out.printf("%.2f\n", 1.0 * ret / N);
        }
    }
}