/*
조건1. 한 칸 위, 한 칸 아래, 옆 칸으로 +k이동
조건2. i초에 i번 칸이 사라진다.

조건1. (i,j) -> (i, j+1), (i, j-1), ((i+1)%2, j+k)
조건2. (i,j)에 도달하는 가장 빠른 시간 <= i초
=> BFS 사용
/*
  
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
    public static char[][] board;
    public static int N, K;
    public static int[] dy;
    public static int[] dx;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[2][N];
        dy = new int[]{0, 0, 1};
        dx = new int[]{1, -1, K};
        for(int i = 0; i < 2; i++){
            String s = br.readLine();
            board[i] = s.toCharArray();
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        int[][] dist = new int[2][N];
        for(int i = 0; i < 2; i++) Arrays.fill(dist[i], -1);
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        dist[0][0] = 0;
        
        while(!q.isEmpty()){
            Point here = q.poll();
            int distance = dist[here.y][here.x];
            for(int dir = 0; dir < 3; dir++){
                int ny = (here.y + dy[dir]) % 2;
                int nx = here.x + dx[dir];
                if(nx >= N) return 1;
                if(nx < 0 || dist[ny][nx] != -1 || board[ny][nx] == '0') continue;
                if(nx < distance + 1) continue;
                dist[ny][nx] = distance + 1;
                q.add(new Point(ny, nx));
            }
        }
        return 0;
    }   
}
