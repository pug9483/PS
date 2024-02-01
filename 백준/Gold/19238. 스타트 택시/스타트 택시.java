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
    public static int N;
    public static int M;
    public static int fuel;
    public static int[][] A;
    public static int sy, sx;
    public static List<Point> srcList = new ArrayList<>();
    public static List<Point> dstList = new ArrayList<>();
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    public static final int INF = 987654231;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            srcList.add(new Point(y1, x1));
            dstList.add(new Point(y2, x2));
        }
        
        System.out.println(solve());
    }
    
    public static int solve(){
        while(srcList.size() > 0){
            int pIndex = findPerson();
            if(fuel <= 0 || pIndex == -1) return -1;
            if(!bfs(pIndex)) return -1;
            srcList.remove(pIndex);
            dstList.remove(pIndex);
        }
        return fuel;
    }
    
    public static int findPerson(){
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
        dist[sy][sx] = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sy, sx));
        
        while(!q.isEmpty()){
            Point here = q.poll();
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || dist[ny][nx] != -1 || A[ny][nx] == 1) continue;
                dist[ny][nx] = dist[here.y][here.x] + 1;
                q.add(new Point(ny, nx));
            }
        }
        
        int y = INF;
        int x = INF;
        int minDist = INF;
        int index = INF;
        
        for(int i = 0; i < srcList.size(); i++){
            Point p = srcList.get(i);
            if(dist[p.y][p.x] == -1) continue;
            if(dist[p.y][p.x] < minDist){
                y = p.y;
                x = p.x;
                index = i;
                minDist = dist[p.y][p.x];
            }
            else if(dist[p.y][p.x] == minDist && (p.y < y || (p.y == y && p.x < x))){
                y = p.y;
                x = p.x;
                index = i;
                minDist = dist[p.y][p.x];
            }
        }
        fuel -= minDist;
        return y == INF ? -1 : index;
    }
    
    public static boolean bfs(int index){
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
        Point src = srcList.get(index);
        Point dst = dstList.get(index);
        dist[src.y][src.x] = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(src.y, src.x));
        while(!q.isEmpty()){
            Point here = q.poll();
            if(here.y == dst.y && here.x == dst.x) break;
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || dist[ny][nx] != -1 || A[ny][nx] == 1) continue;
                q.add(new Point(ny, nx));
                dist[ny][nx] = dist[here.y][here.x] + 1;
            }
        }
        if(fuel < dist[dst.y][dst.x] || dist[dst.y][dst.x] == -1) return false;
        fuel += dist[dst.y][dst.x];
        sy = dst.y;
        sx = dst.x;

        return true;
    }
}