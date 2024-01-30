import java.io.*;
import java.util.*;

public class Main {  
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static List<Point> points = new ArrayList<>();
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            points.add(new Point(y, x));
        }
        int ret = solve();
        System.out.println(ret == INF? "IMPOSSIBLE" : ret);
    }
    
    public static int solve(){
        int atoB = bfs(points.get(0), points.get(1), points.get(2), points.get(3));
        int bToA = bfs(points.get(2), points.get(3), points.get(0), points.get(1));
        return Math.min(atoB, bToA);
    }
    
    private static int bfs(Point a1, Point a2, Point b1, Point b2) {
        int fd = -1;
        int sd = -1;
        Point[][] parent = new Point[N+1][M+1];
        
        int[][] dist = new int[N+1][M+1];
        for(int i = 0; i < N+1; i++) Arrays.fill(dist[i], -1);
        dist[a1.y][a1.x] = 0;
        dist[b1.y][b1.x] = 0;
        dist[b2.y][b2.x] = 0;
        
        Queue<Point> q = new LinkedList<>();
        q.add(a1);
        
        while(!q.isEmpty()){
            Point here = q.poll();
            int distance = dist[here.y][here.x];
            if(here.y == a2.y && here.x == a2.x){
                fd = distance;
                break;
            }
            for(int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if (ny < 0 || nx < 0 || ny > N || nx > M || dist[ny][nx] != -1) continue;
                q.add(new Point(ny, nx));
                dist[ny][nx] = distance + 1;
                parent[ny][nx]= here;
            }
        }

        boolean visited[][] = new boolean[N+1][M+1];

        Point here = a2;
        while(true){
            visited[here.y][here.x] = true; 
            if(here.y == a1.y && here.x == a1.x) break;
            here = parent[here.y][here.x];
        }

        q = new LinkedList<>();
        q.add(b1);
        int[][] oDist = new int[N+1][M+1];
        
        for(int i = 0; i < N+1; i++) Arrays.fill(oDist[i], -1);
        oDist[b1.y][b1.x] = 0;
        
        visited[b1.y][b1.x] = true;
        
        while(!q.isEmpty()){
            here = q.poll();
            int distance = oDist[here.y][here.x];
            if(here.y == b2.y && here.x == b2.x){
                sd = distance;
                break;
            }
            for(int dir = 0; dir < 4; dir++) {
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if (ny < 0 || nx < 0 || ny > N || nx > M || visited[ny][nx] || oDist[ny][nx] != -1) continue;
                q.add(new Point(ny, nx));
                oDist[ny][nx] = distance + 1;
                visited[ny][nx] = true;
            }
        }
        return sd == -1 ? INF : fd + sd;
    }
}