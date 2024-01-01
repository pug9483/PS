import java.io.*;
import java.util.*;

public class Main { 
    static class Node{
        int y, x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int sy, sx;
    public static int[][] A;
    public static int[][] dist;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        dist = new int[N][M];
        for(int i = 0; i < N; i++)
            Arrays.fill(dist[i], -1);
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                A[i][j] = num;
                if(A[i][j] == 2){
                    sy = i;
                    sx = j;
                }
            }
        }
        solve(sy, sx);
        printDist();
    }
    
   public static void solve(int sy, int sx){
       Queue<Node> q = new LinkedList<>();;
       q.add(new Node(sy, sx));
       dist[sy][sx] = 0;
       while(!q.isEmpty()){
           Node here = q.poll();
           for(int dir = 0; dir < 4; dir++){
               int ny = here.y + dy[dir];
               int nx = here.x + dx[dir];
               if(ny < 0 || ny >= N || nx < 0 || nx >= M || A[ny][nx] == 0) continue;
               if(dist[ny][nx] != -1) continue;
               dist[ny][nx] = dist[here.y][here.x] + 1;
               q.add(new Node(ny, nx));
           }
       }
    }
    public static void printDist(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 0) sb.append("0").append(" ");
                else if(dist[i][j] == -1) sb.append("-1").append(" ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}