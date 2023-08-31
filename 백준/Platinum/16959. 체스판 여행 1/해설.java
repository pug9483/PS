/*
1 -> 2
2 -> 3
...
N^2-1 -> N^2
=> 각각의 모든 경로가 최소여야 한다.

(r,c)에서 3가지 타입 중 어떤 말인지도 알아야 한다.

(r, c, level, type)
   (r, c): 말이 놓여져 있는 위치
    level: 현재 몇 번까지 방문을 마쳤는지 저장.(level -> level+1 가는 중)
    type: 말의 종류(0: 나이트, 1: 룩, 2: 비숍)

움직이는 방법
   1) 다른 말로 교체한다.
   2) 이동한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;

public class Main {
    static class Point{
        int y;
        int x;
        int level;
        int type;
        public Point(int y, int x, int level, int type){
            this.y = y;
            this.x = x;
            this.level = level;
            this.type = type;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] board;
    public static int[][][][] dist;
    public static int dy1[] = {1,2,2,1,-1,-2,-2,-1}; // 나이트
    public static int dx1[] = {-2,-1,1,2,2,1,-1,-2};
    public static int dy2[] = {1,-1,0,0}; // 룩
    public static int dx2[] = {0,0,1,-1};
    public static int dy3[] = {1,-1,1,-1}; // 비숍
    public static int dx3[] = {1,1,-1,-1};
    public static Queue<Point> q = new LinkedList<>();
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        dist = new int[N][N][N*N+1][3];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                for(int k = 0; k < N * N; k++)
                    Arrays.fill(dist[i][j][k], -1);
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken()) - 1;
                if(board[i][j] == 0){
                    for(int type = 0; type < 3; type++){
                        dist[i][j][0][type] = 0;
                        q.add(new Point(i, j, 0, type));
                    }
                }
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int ret = INF;
        while (!q.isEmpty()) {
            Point here = q.poll();
            int distance = dist[here.y][here.x][here.level][here.type];
            if (here.level == N * N - 1) return distance;
            for (int type = 0; type < 3; type++) {
                if (here.type == type) continue;
                if(dist[here.y][here.x][here.level][type] == -1){
                    dist[here.y][here.x][here.level][type] = distance+1;
                    q.add(new Point(here.y, here.x, here.level, type));
                }
            }
            if(here.type == 0){
                for(int dir = 0; dir < 8; dir++){
                    int ny = here.y + dy1[dir];
                    int nx = here.x + dx1[dir];
                    if(check(ny, nx)) continue;
                    int nextLevel = here.level;
                    if(board[ny][nx] == here.level + 1) nextLevel++;
                    if(dist[ny][nx][nextLevel][here.type] == -1){
                        dist[ny][nx][nextLevel][here.type] = distance+1;
                        q.add(new Point(ny, nx, nextLevel, here.type));
                    }
                }
            }
            else if(here.type == 1){
                for(int dir = 0; dir < 4; dir++){
                    for(int l = 1;; l++){
                        int ny = here.y + dy2[dir] * l;
                        int nx = here.x + dx2[dir] * l;
                        if(!check(ny, nx)){
                            int nextLevel = here.level;
                            if(board[ny][nx] == here.level + 1) nextLevel++;
                            if(dist[ny][nx][nextLevel][here.type] == -1){
                                dist[ny][nx][nextLevel][here.type] = distance+1;
                                q.add(new Point(ny, nx, nextLevel, here.type));
                            }
                        }
                        else break;
                    }
                }
            }
            else{
                for(int dir = 0; dir < 4; dir++){
                    for(int l = 1;; l++){
                        int ny = here.y + dy3[dir] * l;
                        int nx = here.x + dx3[dir] * l;
                        if(!check(ny, nx)){
                            int nextLevel = here.level;
                            if(board[ny][nx] == here.level + 1) nextLevel++;
                            if(dist[ny][nx][nextLevel][here.type] == -1){
                                dist[ny][nx][nextLevel][here.type] = distance+1;
                                q.add(new Point(ny, nx, nextLevel, here.type));
                            }
                        }
                        else break;
                    }
                }
            }
        }
        return ret;
    }

    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}
