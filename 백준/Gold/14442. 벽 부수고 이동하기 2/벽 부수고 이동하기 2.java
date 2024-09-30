import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class Main {
    static class Node{
        public Node(int y, int x, int dist, int wallsCount){
            this.y = y;
            this.x = x; 
            this.dist = dist;
            this.wallsCount = wallsCount;
        }
        public int y;
        public int x;
        public int dist;
        public int wallsCount;        
    }
    
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static final int INF = 987654321;
    private static char[][] board;
    private static boolean[][][] visited;
    private static int N;
    private static int M;
    private static int K;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][K+1];
        
        for(int i = 0; i<N; i++){
            board[i] = br.readLine().toCharArray();
        }
        
        bfs(0,0);
    }
    
    private static void bfs(int firstY, int firstX){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(firstY, firstX, 1, 0));
        visited[0][0][0] = true;
        
        while(!queue.isEmpty()){
            Node tmpNode = queue.poll();
            int y = tmpNode.y;
            int x = tmpNode.x;
            int dist = tmpNode.dist;
            int wallsCount = tmpNode.wallsCount;
            // System.out.printf("y = %d, x = %d, dist = %d, wallsCount = %d\n", y, x, dist, wallsCount);    
            if(y == N-1 && x == M-1 && wallsCount <= K){
                System.out.println(dist);
                return;
            }
            for(int dir = 0; dir < 4; dir++){
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                int nDist = dist + 1;
                int nWallsCount = wallsCount;

                // 갈 수 있는 경로일 때
                if(ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx][nWallsCount] && board[ny][nx] == '0'){
                    Node newNode = new Node(ny, nx, nDist, nWallsCount);
                    visited[ny][nx][nWallsCount] = true;
                    queue.add(newNode);
                }
                // 갈 수 없는 경로일 때
                if(ny >= 0 && ny < N && nx >= 0 && nx < M && nWallsCount < K && !visited[ny][nx][nWallsCount+1] && board[ny][nx] == '1' ){
                    Node newNode = new Node(ny, nx, nDist, nWallsCount+1);
                    visited[ny][nx][nWallsCount+1] = true;
                    queue.add(newNode);
                }
            }
        }
        System.out.println(-1);
    }
}
