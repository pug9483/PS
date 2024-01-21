import java.io.*;
import java.util.*;

public class Main {  
    static class Point{
        int y, x, type;
        public Point(int y, int x, int type){
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int G;
    public static int R;
    public static int[][] A;
    public static int possibleGroundCnt = 0;
    public static List<Point> list = new ArrayList<>();
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j] == 2) list.add(new Point(i, j, 0));
            }
        }
        
        System.out.println(solve());
    }
    
    public static int solve(){
        possibleGroundCnt = list.size();
        int[] color = new int[possibleGroundCnt];
        return go(0, 0, 0, color);
    }
    
    // color: 0(x), 1(초록), 2(빨강)
    public static int go(int here, int g, int r, int[] color){
        if(g == G && r == R){
            return bfs(color);
        }
        if(here >= possibleGroundCnt || g > G || r > R) return 0;
        
        int ret = 0;
        // here에 초록색을 뿌리는 경우
        color[here] = 1;
        ret = Math.max(ret, go(here+1, g+1, r, color));
        
        // here에 빨간색을 뿌리는 경우
        color[here] = 2;
        ret = Math.max(ret, go(here+1, g, r+1, color));
        
        // here에 아무것도 뿌리지 않는 경우
        color[here] = 0;
        ret = Math.max(ret, go(here+1, g, r, color));
        return ret;
    }
    
    public static int bfs(int[] color){
        int ret = 0;
        Queue<Point> q = new LinkedList<>();
        int[][] dist = new int[N][M];
        int[][] mark = new int[N][M];
        for(int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
        
        for(int i = 0; i < possibleGroundCnt; i++){
            Point p = list.get(i);
            if(color[i] == 1){
                q.add(new Point(p.y, p.x, 1));
                mark[p.y][p.x] = 1;
            }
            if(color[i] == 2){
                q.add(new Point(p.y, p.x, 2));
                mark[p.y][p.x] = 2;
            }
            dist[p.y][p.x] = 0;
        }
        
        while(!q.isEmpty()){
            Point here = q.poll();
            if(mark[here.y][here.x] == 3) continue;
            int type = here.type;
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || A[ny][nx] == 0) continue;
                if(mark[ny][nx] == 0){
                    q.add(new Point(ny, nx, type));
                    dist[ny][nx] = dist[here.y][here.x] + 1;
                    mark[ny][nx] = type;
                }
                else if(mark[ny][nx] == 1 && type == 2 && dist[ny][nx] == dist[here.y][here.x] + 1){
                    ret++;
                    mark[ny][nx] = 3;
                }
                else if(mark[ny][nx] == 2 && type == 1 && dist[ny][nx] == dist[here.y][here.x] + 1){
                    ret++;
                    mark[ny][nx] = 3;
                }
            }
        }
        
        return ret;
    }
    
    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}