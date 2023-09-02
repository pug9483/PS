/*
조건: 빈칸, 벽, 빨간, 파랑, 구멍
변하는 값: 빨간구슬(ry, rx), 파랑구슬(by, bx)

기울이기는 가중치 1에 해당한다.
최소이동(기울이기) 횟수 구하기 문제
-> BFS로 해결 가능하다.

시간복잡도: O(N^2 * M^2)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        boolean first, second;
        Pair(boolean first, boolean second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    static class Points {
        int ry, rx, by, bx;
        Points(int ry, int rx, int by, int bx) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static char[][] board;
    public static int N, M;
    public static int ry, rx, by, bx;
    public static int hy, hx;
    public static final int[] dy = {1, -1, 0, 0};
    public static final int[] dx = {0, 0, 1, -1};
    
    public static void main(String args[]) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            board[i] = s.toCharArray();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'O') {
                    hy = i; hx = j;
                } 
                else if (board[i][j] == 'R') {
                    ry = i; rx = j;
                    board[i][j] = '.';
                } 
                else if (board[i][j] == 'B') {
                    by = i; bx = j;
                    board[i][j] = '.';
                }
            }
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        Queue<Points> q = new LinkedList<>();
        int[][][][] dist = new int[N][M][N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    Arrays.fill(dist[i][j][k],-1);
                }
            }
        }
        q.add(new Points(ry, rx, by, bx));
        dist[ry][rx][by][bx] = 0;
        
        while (!q.isEmpty()) {
            Points here = q.poll();
            int ry = here.ry;
            int rx = here.rx;
            int by = here.by;
            int bx = here.bx;
            for (int dir = 0; dir < 4; dir++) {
                boolean hole1 = false, hole2 = false;
                int nry = ry, nrx = rx, nby = by, nbx = bx;
                Points there = new Points(nry, nrx, nby, nbx);
                Pair p = next(there, dir);
                nry = there.ry;
                nrx = there.rx;
                nby = there.by;
                nbx = there.bx;
                hole1 = p.first;
                hole2 = p.second;
                if (hole2) continue;
                if (hole1) return dist[ry][rx][by][bx] + 1; 
                if (dist[nry][nrx][nby][nbx] == -1){
                    q.add(new Points(nry, nrx, nby, nbx));
                    dist[nry][nrx][nby][nbx] = dist[ry][rx][by][bx] + 1;
                }
            }
        }
        return -1;
    }
    
    public static Pair next(Points p, int dir) {
        char[][] b = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                b[i][j] = board[i][j];
            }
        }
        int ry = p.ry, rx = p.rx, by = p.by, bx = p.bx;
        b[ry][rx] = 'R';
        b[by][bx] = 'B';
        Point red = new Point(ry, rx);
        Point blue = new Point(by, bx);
        boolean hole1 = false, hole2 = false;
        while (true) {
            Pair p1 = simulate(b, dir, red);
            Pair p2 = simulate(b, dir, blue);
            if (p1.first == false && p2.first == false) break;
            if (p1.second) hole1 = true;
            if (p2.second) hole2 = true;
        }
        p.ry = red.y; p.rx = red.x; p.by = blue.y; p.bx = blue.x;
        return new Pair(hole1, hole2);
    }

    public static Pair simulate(char[][] a, int dir, Point p) {
        int y = p.y;
        int x = p.x;
        if (a[y][x] == '.') return new Pair(false, false);
        boolean moved = false;
        while (true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                p.y = y; p.x = x;
                return new Pair(moved, false);
            }
            char ch = a[ny][nx];
            if (ch == '#' || ch == 'R' || ch == 'B') {
                p.y = y; p.x = x;
                return new Pair(moved, false);
            } 
            else if (ch == '.') {
                char temp = a[ny][nx];
                a[ny][nx] = a[y][x];
                a[y][x] = temp;
                y = ny;
                x = nx;
                moved = true;
            } 
            else if (ch == 'O') {
                a[y][x] = '.';
                moved = true;
                p.y = y; p.x = x;
                return new Pair(moved, true);
            }
        }
    }    
        
    public static void printBoard(char[][] board){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println("-------------------------");
    }
}





