import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point{
        public int y;
        public int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    public static int R; // 세로
    public static int C; // 가로
    public static int T; // 시간
    public static int[] dy = {-1, 0, 0, 1};
    public static int[] dx = {0, -1, 1, 0};
    public static int[][] board;
    public static Point top;
    public static Point bottom;
        
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == -1){
                    if(top == null) top = new Point(i, j);
                    else bottom = new Point(i, j);
                    board[i][j] = 0;
                }
            }
        }
        System.out.println(solve());
    }
    
    public static void diffusion(){
        int[][] plusBoard = new int[R][C];
        for(int y = 0; y < R; y++){
            for(int x = 0; x < C; x++){
                if(board[y][x] != 0){
                    int minus = 0;
                    for(int dir = 0; dir < 4; dir++){
                        int ny = y + dy[dir];
                        int nx = x + dx[dir];
                        if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                        if(top.y == ny && top.x == nx) continue;
                        if(bottom.y == ny && bottom.x == nx) continue;
                        plusBoard[ny][nx] += board[y][x] / 5;
                        minus += board[y][x] / 5;
                    }
                    board[y][x] -= minus;
                }
            }
        }
        for(int y = 0; y < R; y++){
            for(int x = 0; x < C; x++){
                board[y][x] += plusBoard[y][x];
            }
        }
    }
    
    public static void airCleanerRun(){
        for(int y = top.y; y > 0; y--){
            board[y][0] = board[y-1][0];
        }
        board[top.y][top.x] = 0;
        for(int x = 0; x < C-1; x++){
            board[0][x] = board[0][x+1];
        }
        for(int y = 0; y < top.y; y++){
            board[y][C-1] = board[y+1][C-1];
        }
        for(int x = C-1; x > 0; x--){
            board[top.y][x] = board[top.y][x-1];
        }
        
        for(int y = bottom.y; y < R-1; y++){
            board[y][0] = board[y+1][0];
        }
        board[bottom.y][bottom.x] = 0;
        for(int x = 0; x < C-1; x++){
            board[R-1][x] = board[R-1][x+1];
        }
        for(int y = R-1; y > bottom.y; y--){
            board[y][C-1] = board[y-1][C-1];
        }
        for(int x = C-1; x > 0; x--){
            board[bottom.y][x] = board[bottom.y][x-1];
        }
 

    }
    
    public static void printBoard(){
        System.out.println("----------------------------");
        for(int y = 0; y < R; y++){
            for(int x = 0; x < C; x++){
                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
    
    public static int solve(){
        int ret = 0;
        
        while(T-- > 0){
            diffusion();
            airCleanerRun();
            // printBoard();
        }
        
        for(int y = 0; y < R; y++){
            for(int x = 0; x < C; x++){
                ret += board[y][x];
            }
        }
        return ret;
    }
} 
   