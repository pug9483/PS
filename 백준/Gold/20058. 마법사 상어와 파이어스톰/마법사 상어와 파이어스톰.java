import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, Q;
    public static int[][] A;
    public static int[] qs;
    public static int ret = 0;
    public static int area = 0;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        N = (int)Math.pow(2, N);
        A = new int[N][N];
        qs = new int[Q];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                ret += A[i][j];
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++)
            qs[i] = Integer.parseInt(st.nextToken());
        solve();
        System.out.println(ret);
        System.out.println(area);
    }
    
    public static void solve(){
        for(int i = 0; i < Q; i++){
            int L = qs[i];
            int m = (int)Math.pow(2, L);
            rotate(m);
            shrink();
            area = getBigArea();
        }
    }
    
    public static void rotate(int m){
        int[][] tmp = new int[N][N];
        for(int y = 0; y < N; y += m){
            for(int x = 0; x < N; x += m){
                for(int i = 0; i < m; i++){
                    for(int j = 0; j < m; j++){
                        tmp[y+j][x+m-i-1] = A[y+i][x+j];
                    }
                }
            }
        }
        A = tmp;
    }

    public static void shrink(){
        List<Point> list = new ArrayList<>();
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(A[y][x] == 0) continue;
                int sum = 0;
                for(int dir = 0; dir < 4; dir++){
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if(ny < 0 || ny >= N || nx < 0 || nx >= N || A[ny][nx] == 0) continue;
                    sum++;
                }
                if(sum < 3) list.add(new Point(y, x));
            }
        }
        for(Point p: list){
            A[p.y][p.x]--;
            ret--;
        }
    }
    
    public static int getBigArea(){
        int ret = 0;
        boolean[][] visited = new boolean[N][N];
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(visited[y][x] || A[y][x] == 0) continue;
                ret = Math.max(ret, bfs(y, x, visited));
            }
        }
        return ret;
    }

    public static int bfs(int y, int x, boolean[][] visited){
        int ret = 0;
        Queue<Point> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new Point(y, x));
        while(!q.isEmpty()){
            Point here = q.poll();
            ret++;
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || A[ny][nx] == 0) continue;
                q.add(new Point(ny, nx));
                visited[ny][nx] = true;
            }
        }
        return ret;
    }
}