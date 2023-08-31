/*
조건1. c1-> c2 or c2 -> c1 이동
조건2. 계속해서 방향을 바꿔야 한다

1. 조건1 처리
  1) C1, C2에 간 적이 없음: 0
  2) C1만 간 적이 있음: 1
  3) C2만 간 적이 있음: 2
  4) C1, C2 모두 간 적이 있음: 3
  => (r, c, s) : 어느 C에 방문한 적이 있는지가 s에 저장된다

2. 조건2 처리
  (r,c)를 어떤 방향에서 와서 도착했는가 => 이전에 왔던 방향을 알아야 한다.
  => (r, c, s, d) : (r,c)에 들어온 방향 d 저장된다.

(r, c, s, d): (r,c)에 d방향으로 들어오고, C의 방문 상태가 s에 저장된다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point{
        int y;
        int x;
        int s;
        int d;
        public Point(int y, int x, int s, int d){
            this.y = y;
            this.x = x;
            this.s = s;
            this.d = d;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static char[][] board;
    public static int[][][][] dist;
    public static Queue<Point> q = new LinkedList<>();
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    public static int y1 = -1, x1 = -1, y2 = -1, x2 = -1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        dist = new int[N][M][4][4];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                for(int k = 0; k < 4; k++)
                    Arrays.fill(dist[i][j][k], -1);
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'S'){
                    for(int dir = 0; dir < 4; dir++){
                        q.add(new Point(i, j, 0, dir));
                        dist[i][j][0][dir] = 0;
                    }
                }
                if(board[i][j] == 'C'){
                    if(y1 == -1){
                        y1 = i;
                        x1 = j;
                    }
                    else{
                        y2 = i;
                        x2 = j;
                    }
                }
            }
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        while(!q.isEmpty()){
            Point here = q.poll();
            int distance = dist[here.y][here.x][here.s][here.d];
            if(here.s == 3) return distance;
            for(int dir = 0; dir < 4; dir++){
                if(dir == here.d) continue;
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || board[ny][nx] == '#') continue;
                int ns = here.s;
                if(board[ny][nx] == 'C'){
                    if(ny == y1 && nx == x1) ns |= 1;
                    else ns |= 2;
                }
                if(dist[ny][nx][ns][dir] == -1){
                    dist[ny][nx][ns][dir] = distance + 1;
                    q.add(new Point(ny, nx, ns, dir));
                }
            }
        }
        return -1;
    }
}
