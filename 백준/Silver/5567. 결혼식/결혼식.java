import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 간선
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); 
            int v = Integer.parseInt(st.nextToken()); 
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        System.out.println(solve(1));
    }   
    
    public static int solve(int src){
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        int ret = 0;
        
        while(!q.isEmpty()){
            int here = q.poll();
            int distance = dist[here];
            if(distance <= 2) ret++;
            else continue;
            for(int there: graph.get(here)){
                if(dist[there] != -1) continue;
                q.add(there);
                dist[there] = distance + 1;
            }
        }
        return ret - 1;
    }
} 