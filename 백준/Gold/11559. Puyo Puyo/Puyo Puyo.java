import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public static int N = 12;
    public static int M = 6;
    public static char[][] A = new char[N][M];
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        for(int i = 0; i < N; i++){
            A[i] = br.readLine().toCharArray();
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 0;
        
        while(true){
            boolean ok = false;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(A[i][j] == '.') continue;
                    if(bfs(i, j)){
                        ok = true;
                    }
                }
            }
            if(ok) move();
            else break;
            ret++;
        }
        return ret;
    }
    
    public static boolean bfs(int i, int j){
        char c = A[i][j];
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        visited[i][j] = true;
        Stack<Point> stack = new Stack<>();
        
        while(!q.isEmpty()){
            Point here = q.poll();
            stack.add(here);
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) continue;
                if(A[ny][nx] == c){
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                }
            }
        }
        
        boolean ret = false;
        if(stack.size() >= 4){
            ret = true;
            while(!stack.isEmpty()){
                Point here = stack.pop();
                A[here.y][here.x] = '.';
            }
        }
        return ret;
    }
    
    public static void move(){
        for(int x = 0; x < M; x++){
            Queue<Character> q = new LinkedList<>();
            for(int y = N-1; y >= 0; y--){
                if(A[y][x] != '.') {
                    q.add(A[y][x]);
                    A[y][x] = '.';
                }
            }
            int ny = N-1;
            while(!q.isEmpty()){
                A[ny--][x] = q.poll();
            }
        }
    }
}