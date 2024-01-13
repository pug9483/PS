import java.io.*;
import java.util.*;

public class Main {  
    static class Point{
        public int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    static class Node{
        public int v, w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[][] A;
    public static int[][] group;
    public static int[][] graph;
    
    public static int groupNum = 1;
    
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        group = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        groupingIsland();
        graph = new int[groupNum][groupNum];
        for(int i = 0; i < groupNum; i++)
            Arrays.fill(graph[i], INF);
        makeGraph();
        System.out.println(prim());
    }
    
    public static int prim(){
        int ret = 0;
        boolean[] visited = new boolean[groupNum];
        int[] dist = new int[groupNum];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        dist[1] = 0;
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node here = pq.poll();
            if(visited[here.v]) continue;
            visited[here.v] = true;
            ret += here.w;
            for(int next = 1; next < groupNum; next++){
                if(graph[here.v][next] == INF) continue;
                if(!visited[next] && graph[here.v][next] < dist[next]){
                    dist[next] = graph[here.v][next];
                    pq.add(new Node(next, dist[next]));
                }
            }
        }
        
        boolean ok = true;
        for(int i = 1; i < groupNum; i++)
            if(dist[i] == INF) ok = false;
        
	    return ok ? ret : -1;
    }
    
    public static void makeGraph(){
        for(int y = 0; y < N; y++){
            for(int x = 0; x < M; x++){
                if(A[y][x] == 0) continue;
                int fg = group[y][x];
                // y 가로줄
                for(int k = x+1; k < M; k++){
                    int sg = group[y][k];
                    if(A[y][k] == 1 && fg == sg) break;
                    if(A[y][k] == 1 && sg > 0 && sg != fg){
                        int diff = (int)(Math.abs(k - x)) - 1;
                        if(diff >= 2) 
                            graph[fg][sg] = graph[sg][fg] = Math.min(graph[fg][sg], diff);
                        break;
                    }
                }
                // x 세로줄
                for(int k = y+1; k < N; k++){
                    int sg = group[k][x];
                    if(A[k][x] == 1 && fg == sg) break;
                    if(A[k][x] == 1 && sg > 0 && sg != fg){
                        int diff = (int)(Math.abs(k - y)) - 1;
                        if(diff >= 2)
                            graph[fg][sg] = graph[sg][fg] = Math.min(graph[fg][sg], diff);
                        break;
                    }
                }
            }
        }
    }
    
    public static void groupingIsland(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 1 && group[i][j] == 0)
                    bfs(i, j, groupNum++);
            }
        }
    }
    
    public static void bfs(int sy, int sx, int num){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sy, sx));
        group[sy][sx] = num;
        
        while(!q.isEmpty()){
            Point here = q.poll();
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(A[ny][nx] == 0 || group[ny][nx] > 0) continue;
                q.add(new Point(ny, nx));
                group[ny][nx] = num;
            }
        }
    }
}