import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point{
        int z;
        int y;
        int x;
        public Point(int z, int y, int x){
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int L, R, C;
    public static int Sx, Sy, Sz;
    public static int Ex, Ey, Ez;
    public static char[][][] A;
    public static int[] dz = {0, 0, 0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0, 0, 0};
    public static int[] dx = {0, 0, 1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());   
            L = Integer.parseInt(st.nextToken()); // 높이
            R = Integer.parseInt(st.nextToken()); // 세로
            C = Integer.parseInt(st.nextToken()); // 가로
            if(L == 0 && R == 0 && C == 0) break;
            A = new char[L][R][C];
            for(int k = 0; k < L; k++){
                for(int i = 0; i < R; i++){
                    String s = br.readLine();
                    for(int j = 0; j < C; j++){
                        A[k][i][j] = s.charAt(j);
                        if(A[k][i][j] == 'S'){
                            Sz = k; Sy = i; Sx = j;
                            A[k][i][j] = '.';
                        }
                        else if(A[k][i][j] == 'E'){
                            Ez = k; Ey = i; Ex = j;
                            A[k][i][j] = '.';
                        }
                    }
                }
                br.readLine();
            }
            solve();
        }
    }
     
    public static void solve(){
        Queue<Point> q = new LinkedList<>();
        int[][][] dist = new int[L][R][C];
        for(int i = 0; i < L; i++)
            for(int j = 0; j < R; j++)
                Arrays.fill(dist[i][j], -1);
        q.add(new Point(Sz, Sy, Sx));
        dist[Sz][Sy][Sx] = 0;
        
        while(!q.isEmpty()){
            Point here = q.poll();
            int distance = dist[here.z][here.y][here.x];
            if(here.z == Ez && here.y == Ey && here.x == Ex){
                System.out.printf("Escaped in %d minute(s).\n", distance);
                return;
            }
            for(int dir = 0; dir < 6; dir++){
                int nz = here.z + dz[dir];
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(check(nz, ny, nx) || dist[nz][ny][nx] != -1 || A[nz][ny][nx] == '#') continue;
                dist[nz][ny][nx] = distance + 1;
                q.add(new Point(nz, ny, nx));
            }
        }
        System.out.println("Trapped!");
    }
    
    public static boolean check(int z, int y, int x){
        return z < 0 || z >= L || y < 0 || y >= R || x < 0 || x >= C;
    }
}