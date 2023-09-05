/*
D[i][j] = 판다가 (i,j)에서 이동을 시작했을 때, 최대한 움직일 수 있는 일 수

힌트: (i,j)로 갈 수 있다면 (j,i)로는 갈 수 없다.
(y1,x1) -> (y2,x2)로 갔을 경우, D[y1][x1] = D[y2][x2] + 1이다.

A[i][j]의 크기를 기준으로 이차원 칸의 정보를 내림차순으로 정렬해놓아야 한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    static class Point{
        int y;
        int x;
        int val;
        public Point(int y, int x, int val){
            this.y = y;
            this.x = x;
            this.val = val;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] A;
    public static Point[] B;
    public static int[][] memo;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());   
        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        memo = new int[N][N];
        B = new Point[N*N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());   
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                B[i*N + j] = new Point(i, j, A[i][j]);
            }
        }
        Arrays.sort(B, (o1, o2) -> Integer.compare(o2.val, o1.val));
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 1;
        for(int i = 0; i < N*N; i++){
            int y = B[i].y;
            int x = B[i].x;
            memo[y][x] = 1;
            for(int dir = 0; dir < 4; dir++){
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if(check(ny, nx) || A[y][x] >= A[ny][nx]) continue;
                memo[y][x] = Math.max(memo[y][x], memo[ny][nx] + 1);
                ret = Math.max(ret, memo[y][x]);
            }
        }
        return ret;
    }
    
    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}
