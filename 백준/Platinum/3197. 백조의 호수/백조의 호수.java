import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
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
    public static int N, M;
    public static char[][] board;
    public static int sy = -1, sx = -1, ey = -1, ex = -1;
    public static Queue<Point> swan = new LinkedList<>();
    public static Queue<Point> nSwan = new LinkedList<>();
    public static Queue<Point> water = new LinkedList<>();
    public static Queue<Point> nWater = new LinkedList<>();
    public static boolean[][] sVisited;
    public static boolean[][] wVisited;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        sVisited = new boolean[N][M];
        wVisited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'L'){
                    if(sy == -1){
                        sy = i;
                        sx = j;
                    }
                    else{
                        ey = i;
                        ex = j;
                    }
                    board[i][j] = '.';
                }
                if(board[i][j] == '.'){
                    water.add(new Point(i, j));
                    wVisited[i][j] = true;
                }
            }
        }
        System.out.println(solve());
    }
    
    private static int solve() {
        swan.add(new Point(sy, sx));
        sVisited[sy][sx] = true;
        for(int time = 0;; time++){
            while(!water.isEmpty()){
                Point here = water.poll();
                board[here.y][here.x] = '.';
                for(int dir = 0; dir < 4; dir++){
                    int ny = here.y + dy[dir];
                    int nx = here.x + dx[dir];
                    if(check(ny, nx) || wVisited[ny][nx]) continue;
                    if(board[ny][nx] == '.'){
                        water.add(new Point(ny, nx));
                        wVisited[ny][nx] = true;
                    }
                    else{
                        nWater.add(new Point(ny, nx));
                        wVisited[ny][nx] = true;
                    }
                }
            }
            while(!swan.isEmpty()){
                Point here = swan.poll();
                for(int dir = 0; dir < 4; dir++){
                    int ny = here.y + dy[dir];
                    int nx = here.x + dx[dir];
                    if(check(ny, nx) || sVisited[ny][nx]) continue;
                    if(board[ny][nx] == '.'){
                        swan.add(new Point(ny, nx));
                        sVisited[ny][nx] = true;
                    }
                    else{
                        nSwan.add(new Point(ny, nx));
                        sVisited[ny][nx] = true;
                    }
                }
            }
            if(sVisited[ey][ex]){
                return time;
            }
            water = nWater;
            swan = nSwan;
            nWater = new LinkedList<>();
            nSwan = new LinkedList<>();
        }
    }

    private static boolean check(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}