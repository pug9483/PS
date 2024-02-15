import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static List<List<Integer>> rgraph = new ArrayList<>();
    public static int[] A;
    public static int[] B;
    public static final int INF = Integer.MAX_VALUE;
    public static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[N];
        for(int i = 0; i < N; i++){
            graph.add(new ArrayList<>());
            rgraph.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            B[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            rgraph.get(v).add(u);
        }
        solve();
    }
    
    public static void solve(){
        List<Integer> orders = topologicalSort(graph);
        if(orders.size() != N){
            System.out.println(-1);
            return;
        }
        int ret = 0;
        int left = 0, right = N-1;
        Collections.reverse(orders);
        while(left <= right){
            int mid = (left + right) / 2;
            if(go(mid, orders, graph)){
                left = mid+1;
                ret = Math.max(ret, mid);
            }
            else right = mid-1;
        }
        
        Collections.reverse(orders);
        for(int u : orders){
            A[u] = 1;
            for(int v: rgraph.get(u))
                A[u] = Math.max(A[u], A[v]+1);
            if(u < ret) A[u] = B[u];
            else if(u == ret) A[u] = Math.max(B[u]+1, A[u]);
        }
        for(int i = 0; i < N; i++)
            sb.append(A[i]).append(" ");
        System.out.println(sb);
    }
    
    public static List<Integer> topologicalSort(List<List<Integer>> g){
        int[] indegree = new int[N];
        for(int i = 0; i < N; i++){
            for(int v: g.get(i))
                indegree[v]++;
        }
        
        List<Integer> orders = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < N; i++){
            if(indegree[i] == 0){
                q.add(i);
                orders.add(i);
            }
        }
        
        while(!q.isEmpty()){
            int here = q.poll();
            for(int next: g.get(here)){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                    orders.add(next);
                }
            }
        }
        return orders;
    }
    
    public static boolean go(int k, List<Integer> orders, List<List<Integer>> g){
        int[] C = new int[N];
        for(int u: orders){
            C[u] = INF;
            for(int v: g.get(u))
                C[u] = Math.min(C[u], C[v] - 1);
            if(u < k){
                if(B[u] > C[u]) return false;
                C[u] = B[u];
            }
            if(C[u] <= 0) return false;
        }
        
        for(int i = 0; i < N; i++){
            if(C[i] > B[i]) return true;
            if(C[i] < B[i]) return false;
        }
        
        return true;
    }
}