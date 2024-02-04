import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean terminal = false;
        
        public void insert(String word){
            TrieNode node = this;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                node = node.children.computeIfAbsent(c, (key) -> new TrieNode());  
            }
            node.terminal = true;    
        }
        
        public int typing(String word){
            TrieNode node = this;
            int ret = 0;
            for(int i = 0; i < word.length(); i++){
                if(i == 0) ret++;
                else if(node.terminal || node.children.size() > 1) ret++;
                char c = word.charAt(i);
                TrieNode childNode = node.children.get(c);
                node = childNode;
            }
            return ret;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static TrieNode trie;
    public static String[] input;
    
	public static void main(String[] args) throws Exception {
        while(true){
            String S = br.readLine();
            if(S == null) break;
            int N = Integer.parseInt(S);
            input = new String[N];
            trie = new TrieNode();
            for(int i = 0; i < N; i++){
                String str = br.readLine();
                input[i] = str;
                trie.insert(str);
            }
            int ret = 0;
            for(int i = 0; i < N; i++){
                ret += trie.typing(input[i]);
            }
            System.out.printf("%.2f\n", 1.0 * ret / N);
        }
    }
}