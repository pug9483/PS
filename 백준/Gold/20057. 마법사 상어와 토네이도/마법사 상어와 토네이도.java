import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] A;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int ret = 0;
    public static int[][] ddy = new int[][]{
        {-2, -1, 0, 1, 0, -1, 0, 1, 0, -1},
        {0, -1, -1, -1, -2, 1, 1, 1, 2, 0},
        {2, 1, 0, -1, 0, 1, 0, -1, 0, 1},
        {0, -1, -1, -1, -2, 1, 1, 1, 2, 0}
    };
    public static int[][] ddx = new int[][]{
        {0, -1, -1, -1, -2, 1, 1, 1, 2, 0},
        {2, 1, 0, -1, 0, 1, 0, -1, 0, 1},
        {0, -1, -1, -1, -2, 1, 1, 1, 2, 0},
        {-2, -1, 0, 1, 0, -1, 0, 1, 0, -1}
    };
    public static int[] pers = new int[]{5, 10, 7, 1, 2, 10, 7, 1, 2};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(ret);
    }
    
    public static void solve(){
        int sy = N / 2;
        int sx = N / 2;
        go(sy, sx, 1, 0, 3);
    }
    
    // dir -> 0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽
    public static void go(int y, int x, int len, int cnt, int dir){
        if(y == 0 && x == 0) return;
        for(int i = 0; i < len; i++){
            spread(y, x, dir);
            y += dy[dir];
            x += dx[dir];
            if(y == 0 && x == 0) return;
        }
        if(cnt == 0) go(y, x, len, cnt+1, (dir+3) % 4);
        else if(cnt == 1) go(y, x, len+1, 0, (dir+3) % 4);
    }
    
    public static void spread(int y, int x, int dir){
        int ny = y + dy[dir];
        int nx = x + dx[dir];
        int num = A[ny][nx];
        for(int i = 0; i < 9; i++){
            int nny = ny + ddy[dir][i];
            int nnx = nx + ddx[dir][i];
            int left = (int)(num * pers[i] / 100);
            if(check(nny, nnx)) ret += left;
            else A[nny][nnx] += left;
            A[ny][nx] -= left;
        }
        int ay = ny + ddy[dir][9];
        int ax = nx + ddx[dir][9];
        if(check(ay, ax)) ret += A[ny][nx];
        else A[ay][ax] += A[ny][nx];
        A[ny][nx] = 0;
    }
    
    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}