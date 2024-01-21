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
    public static int K;
    public static char[][] A;
    public static int[] B;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N+1][M];
        
        for(int j = 0; j < M; j++)
            A[N][j] = 'x';
        for(int i = 0; i < N; i++)
            A[i] = br.readLine().toCharArray();

        K = Integer.parseInt(br.readLine());
        B = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            B[i] = N - Integer.parseInt(st.nextToken());;
        }
        
        solve();
    }

    public static void solve(){
        for(int i = 0; i < K; i++)
            shoot(B[i], i % 2);
        printBoard(A);
    }
    
    //dir: 0(왼쪽 -> 오른쪽), 1(오른쪽 -> 왼쪽)
    public static void shoot(int height, int dir){
        boolean bomb = false;
        if(dir == 0){
            for(int j = 0; j < M; j++){
                if(A[height][j] == 'x'){
                    A[height][j] = '.';
                    bomb = true;
                    break;
                }
            }
        }
        else{
            for(int j = M-1; j >= 0; j--){
                if(A[height][j] == 'x'){
                    A[height][j] = '.';
                    bomb = true;
                    break;
                }
            }
        }
        if(bomb){
            boolean[][] visited = new boolean[N+1][M];
            dfs(N, 0, visited);
            down(visited);
        }
    }
    
    public static void dfs(int y, int x, boolean[][] visited){
        visited[y][x] = true;
        for(int dir = 0; dir < 4; dir++){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(ny < 0 || ny > N || nx < 0 || nx >= M) continue;
            if(visited[ny][nx] || A[y][x] == '.') continue;
            dfs(ny, nx, visited);
        }
    }
    
    public static void down(boolean[][] visited){
        int ret = 987654321;
        List<Point> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && A[i][j] == 'x'){
                    list.add(new Point(i, j));
                    A[i][j] = '.';
                }
            }
        }
        for(Point p: list){
            int num = 0;
            while(A[p.y+num][p.x] == '.') num++;
            ret = Math.min(ret, num-1);
        }
        
        for(Point p: list){
            A[p.y+ret][p.x] = 'x';
        }
    }
    
    public static void printBoard(char[][] A){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(A[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}