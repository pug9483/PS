/*
힌트: 수가 감소하는 방향으로만 이동할 수 있기 때문에, 사이클이 생기기 않는다 => DP 가능
for(int dir = 0; dir < 4; dir++)
    D[y][x] += D[y+dy[dir]][x+dx[dir]];

go(y, x): (y,x)에서 이동을 시작했을 때, (N-1, M-1)로 이동할 수 있는 방법의 수
*/

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
    public static int N;
    public static int M;
    public static int[][] A;
    public static int[][] memo;
    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());   
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());   
            for(int j = 0; j < M; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        memo = new int[N][M];
        for(int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        System.out.println(solve(0, 0));
    }

    public static int solve(int y, int x){
        if(y == N-1 && x == M-1) return 1;
        if(memo[y][x] != -1) return memo[y][x];
        int ret = 0;
        for(int dir = 0; dir < 4; dir++){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(check(ny, nx) || A[ny][nx] >= A[y][x]) continue;
            ret += solve(ny, nx);
        }
        return memo[y][x] = ret;
    }
    
    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}
