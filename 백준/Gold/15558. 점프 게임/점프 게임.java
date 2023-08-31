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
    public static int[][] board;
    public static int N, K;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[2][N+100005];
        for(int i = 0; i < 2; i++) Arrays.fill(board[i], 1);
        for(int i = 0; i < 2; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++)
                board[i][j] = s.charAt(j) - '0';
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        int[][] dist = new int[2][N+100005];
        for(int i = 0; i < 2; i++) Arrays.fill(dist[i], -1);
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        dist[0][0] = 0;
        
        while(!q.isEmpty()){
            Point here = q.poll();
            int d = dist[here.y][here.x];
            if(d > here.x) continue;
            if(here.x > N) return 1;
            if(board[here.y][here.x+1] == 1 && dist[here.y][here.x+1] == -1){
                dist[here.y][here.x+1] = d+1;
                q.add(new Point(here.y, here.x + 1));
            }
            if(here.x > 0 && board[here.y][here.x-1] == 1 && dist[here.y][here.x-1] == -1){
                dist[here.y][here.x-1] = d+1;
                q.add(new Point(here.y, here.x-1));
            }
            if(board[(here.y+1)%2][here.x + K] == 1 && dist[(here.y+1)%2][here.x + K] == -1){
                dist[(here.y+1)%2][here.x + K] = d+1;
                q.add(new Point((here.y+1)%2, here.x + K));
            }
        }
        return 0;
    }   
}