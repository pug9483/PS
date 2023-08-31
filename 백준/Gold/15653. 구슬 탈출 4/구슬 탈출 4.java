import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class DP{
        Point u;
        Point v;
        public DP(Point u, Point v){
            this.u = new Point(u.y, u.x);
            this.v = new Point(v.y, v.x);
        }
    }
    
    static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static char[][] board;
    public static Point red;
    public static Point blue;
    public static int[] dy = {-1, 0, 1, 0}; // 위. 오른쪽, 아래, 왼쪽
    public static int[] dx = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'R'){
                    red = new Point(i, j);
                    board[i][j] = '.';
                }
                else if(board[i][j] == 'B'){
                    blue = new Point(i, j);
                    board[i][j] = '.';
                } 
            }
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        Queue<DP> q = new LinkedList<>();
        int[][][][] dist = new int[N][M][N][M];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                for(int k = 0; k < N; k++)
                    Arrays.fill(dist[i][j][k], -1);
        q.add(new DP(red, blue));
        dist[red.y][red.x][blue.y][blue.x] = 0;
        
        while(!q.isEmpty()){
            DP here = q.poll();
            Point u = here.u;
            Point v = here.v;
            int d = dist[u.y][u.x][v.y][v.x];
            if(board[v.y][v.x] == 'O') continue;
            if(board[u.y][u.x] == 'O') return d;
            for(int dir = 0; dir < 4; dir++){
                Point rollU = roll(u, dir);
                Point rollV = roll(v, dir);
                if(board[rollV.y][rollV.x] == 'O') continue;
                if(rollU.y == rollV.y && rollU.x == rollV.x){
                    // u가 v의 위에 있었을 경우
                    if(u.y < v.y && u.x == v.x){
                        if(dir == 0){
                            rollV.y++;
                        }
                        else if(dir == 2){
                            rollU.y--;
                        }
                    }
                    // u가 v의 오른쪽에 있었을 경우
                    else if(u.y == v.y && u.x > v.x){
                        if(dir == 1){
                            rollV.x--;
                        }
                        else if(dir == 3){
                            rollU.x++;
                        }
                    }
                    // u가 v의 아래쪽에 있었을 경우
                    else if(u.y > v.y && u.x == v.x){
                        if(dir == 0){
                            rollU.y++;
                        }
                        else if(dir == 2){
                            rollV.y--;
                        }
                    }
                    // u가 v의 왼쪽에 있었을 경우
                    else if(u.y == v.y && u.x < v.x){
                        if(dir == 1){
                            rollU.x--;
                        }
                        else if(dir == 3){
                            rollV.x++;
                        }
                    }
                }
                if(dist[rollU.y][rollU.x][rollV.y][rollV.x] == -1){
                    q.add(new DP(rollU, rollV));
                    dist[rollU.y][rollU.x][rollV.y][rollV.x] = d + 1;
                }
            }
        }
        return -1;
    }
    
    public static Point roll(Point p, int dir){
        int y = p.y;
        int x = p.x;
        while(true){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M || board[ny][nx] == '#') break;
            if(board[ny][nx] == 'O') return new Point(ny, nx);
            y = ny;
            x = nx;
        }
        return new Point(y, x);
    }
}
