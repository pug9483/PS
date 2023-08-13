import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {        
    static class Node{
        int num;
        int dist;
        public Node(int num, int dist){
            this.num = num;
            this.dist = dist;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            solve();
        }
    }
    
    public static void solve(){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        int[] parent = new int[N+1];
        Arrays.fill(parent, -1);
        q.add(new Node(1, 1));
        visited[1] = true;
        
        while(!q.isEmpty()){
            Node here = q.poll();
            if(here.num == 0 && here.dist <= 100){
                System.out.println(reconstruct(here.num, here.dist, parent));
                return; 
            }
            for(int i = 0; i < 2; i++){
                int next = (here.num * 10 + i) % N;
                if(!visited[next]){
                    visited[next] = true;
                    q.add(new Node(next, here.dist+1));
                    parent[next] = here.num;
                }
            }
        }
        System.out.println("BRAK");
    }
    
    public static String reconstruct(int num, int dist, int[] parent){
        StringBuilder sb = new StringBuilder();
        while(dist-- > 0){
            int prev = parent[num];
            if((prev * 10) % N == num) sb.append("0");
            else sb.append("1");
            num = parent[num];
        }
        return sb.reverse().toString();
    }
}