import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int K;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] indegree;
    static int[][] relations;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        indegree = new int[N];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
        }

        solve();

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            sb.append(relations[u][v]).append("\n");
        }
        System.out.println(sb);

    }

    public static void solve() {
        List<Integer> orders = topologicalSort();
        relations = new int[N][N];
        for(int num: orders){
            dfs(num, num, new boolean[N]);
        }
    }

    private static void dfs(int start, int here, boolean[] visited) {
        visited[here] = true;

        for(int next: graph.get(here)) {
            if(visited[next]) continue;
            relations[start][next] = -1;
            relations[next][start] = 1;
            dfs(start, next, visited);
        }
    }


    public static List<Integer> topologicalSort(){
        List<Integer> order = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < N; i++)
            if(indegree[i] == 0) q.add(i);

        while(!q.isEmpty()){
            int here = q.poll();
            order.add(here);
            for(int there : graph.get(here)){
                indegree[there]--;
                if(indegree[there] == 0) q.add(there);
            }
        }
        return order;
    }
}