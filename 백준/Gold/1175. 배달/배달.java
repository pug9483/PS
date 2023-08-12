import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int y;
        int x;
        int d;
        int one;
        int two;
        public Node(int y, int x, int d, int one, int two) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.one = one;
            this.two = two;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static char[][] board;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    public static int fy = -1, fx = -1, cy = -1, cx = -1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int sy = -1, sx = -1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'S'){
                    sy = i;
                    sx = j;
                }
                if(board[i][j] == 'C'){
                    if(fy == -1){
                        fy = i;
                        fx = j;
                    }
                    else{
                        cy = i;
                        cx = j;
                    }
                }
            }
        }
        System.out.println(solve(sy, sx));
    }

    public static int solve(int sy, int sx){
        Queue<Node> q = new LinkedList<>();
        // 첫번째, 두번째, 방향, N, M
        int[][][][][] dist = new int[2][2][4][N][M];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < N; l++) {
                        Arrays.fill(dist[i][j][k][l], -1);
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            q.add(new Node(sy, sx, i,0,0));
            dist[0][0][i][sy][sx] = 0;
        }
        while (!q.isEmpty()) {
            Node here = q.poll();
            int distance = dist[here.one][here.two][here.d][here.y][here.x];
            if(here.one == 1 && here.two == 1) return distance;
            for (int dir = 0; dir < 4; dir++) {
                if(dir == here.d) continue;
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(board[ny][nx] == '#' || dist[here.one][here.two][dir][ny][nx] != -1) continue;

                if(board[ny][nx] == 'C'){
                    if(ny == fy && nx == fx){
                        q.add(new Node(ny, nx, dir, 1, here.two));
                        dist[1][here.two][dir][ny][nx] = distance + 1;
                    }
                    else{
                        q.add(new Node(ny, nx, dir, here.one, 1));
                        dist[here.one][1][dir][ny][nx] = distance + 1;
                    }
                }
                else{
                    q.add(new Node(ny, nx, dir, here.one, here.two));
                    dist[here.one][here.two][dir][ny][nx] = distance + 1;
                }
            }
        }
        return -1;
    }

}