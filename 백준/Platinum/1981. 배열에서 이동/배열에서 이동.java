import java.io.*;
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
    public static int N;
    public static int[][] A;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }
        
    public static boolean go(int diff){
        for(int mn = 0; mn + diff <= 200; mn++){
            if(bfs(mn, mn+diff)) return true;
        }
        return false;
    }
    
    public static boolean bfs(int mn, int mx){
        if(mn > A[0][0] || mx < A[0][0]) return false;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(p.y == N-1 && p.x == N-1) return true;
            for(int dir = 0; dir < 4; dir++){
                int ny = p.y + dy[dir];
                int nx = p.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
                if(mn <= A[ny][nx] && A[ny][nx] <= mx){
                    queue.add(new Point(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
        return false;
    }
    
    public static int solve(){
        int left = 0;
        int right = 200;
        int ret = 0;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(go(mid)){
                ret = mid;
                right = mid-1;
            }
            else left = mid+1;
        }
        return ret;
    }
}