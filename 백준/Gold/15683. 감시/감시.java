import java.io.*;
import java.util.*;

public class Main {   
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int M;
    public static int[][] board;
    public static int ret = Integer.MAX_VALUE;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    public static int[][] visited;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0, 0);
        System.out.println(ret);
        
    }

    public static void roll(int y, int x, int dir, int set){
        int ny = y;
        int nx = x;
        while(ny < N && ny >= 0 && nx < M && nx >= 0 && board[ny][nx] != 6){
            visited[ny][nx] += set;
            ny += dy[dir];
            nx += dx[dir];
        }
    }
    
    public static void solve(int a, int b){
        if(b == M){
            a++;
            b = 0;
        }
        
        if(a == N){
            int cnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(visited[i][j] == 0 && board[i][j] == 0) cnt++;
                }
            }
            ret = Math.min(ret, cnt);
            return;  
        } 
        if(board[a][b] == 0 || board[a][b] == 6){
            solve(a, b+1);  
            return;
        } 
        
        
        if(board[a][b] == 1){
            for(int dir = 0; dir < 4; dir++){
                roll(a, b, dir, 1);
                solve(a, b+1);
                roll(a, b, dir, -1);
            }
        }
        
        else if(board[a][b] == 2){
             for(int dir = 0; dir < 2; dir++){
                roll(a, b, 2*dir, 1);
                roll(a, b, 2*dir+1, 1);
                solve(a, b+1);
                roll(a, b, 2*dir, -1);
                roll(a, b, 2*dir+1, -1);
            }
        }
        else if(board[a][b] == 3){
            for(int dir = 0; dir < 2; dir++){
                roll(a, b, dir, 1);
                roll(a, b, (dir+2) % 4, 1);
                solve(a, b+1);
                roll(a, b, dir, -1);
                roll(a, b, (dir+2) % 4, -1);
            }
            roll(a, b, 2, 1);
            roll(a, b, 1, 1);
            solve(a, b+1);
            roll(a, b, 2, -1);
            roll(a, b, 1, -1);
            roll(a, b, 3, 1);
            roll(a, b, 0, 1);
            solve(a, b+1);
            roll(a, b, 3, -1);
            roll(a, b, 0, -1);
        }
        else if(board[a][b] == 4){
            for(int dir = 0; dir < 4; dir++){
                roll(a, b, dir, 1);
                roll(a, b, (dir+1) % 4, 1);
                roll(a, b, (dir+2) % 4, 1);
                solve(a, b+1);
                roll(a, b, dir, -1);
                roll(a, b, (dir+1) % 4, -1);
                roll(a, b, (dir+2) % 4, -1);
            }
        }
        else if(board[a][b] == 5){
            roll(a, b, 0, 1);
            roll(a, b, 1, 1);
            roll(a, b, 2, 1);
            roll(a, b, 3, 1);
            solve(a, b+1);
            roll(a, b, 0, -1);
            roll(a, b, 1, -1);
            roll(a, b, 2, -1);
            roll(a, b, 3, -1);
        }
    }
}