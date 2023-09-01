/*
각각의 턴마다 BFS를 확장해준다.
첫번째 턴: (player1 이동, player2 이동 ... player P 이동)
두번째 턴: ..

시간복잡도: O(NMP)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static int N, M, P;
    public static int[][] board;
    public static int[] s;
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};
	public static Queue<Point>[] q;
	public static Queue<Point>[] nq;
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        s = new int[P+1];
        q = new Queue[P+1];
        nq = new Queue[P+1];
        board = new int[N][M];
        
        st = new StringTokenizer(br.readLine());
    	for(int i = 1 ; i <= P ; ++i){
            s[i] = Integer.parseInt(st.nextToken());
            q[i] = new LinkedList<>();
            nq[i] = new LinkedList<>();
        }
        
	    for(int i = 0 ; i < N ; ++i) {
	        String s = br.readLine();
	        for(int j = 0 ; j < M ; ++j) {
	            char c = s.charAt(j);
	            if(c == '.') board[i][j] = 0;
                else if(c == '#') board[i][j] = -1;
                else board[i][j] = c - '0';
	        }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] > 0)
                    q[board[i][j]].add(new Point(i, j));
            }
        }
        solve();
    }
    
    public static void solve(){
        int[] ret = new int[P+1];
        while(true){
            boolean ok = false;
            for(int player = 1; player <= P; player++){
                int[][] dist = new int[N][M];
                while(!q[player].isEmpty()){
                    ok = true;
                    Point here = q[player].poll();
                    if(dist[here.y][here.x] == s[player])
                        nq[player].add(new Point(here.y, here.x));
                    if(board[here.y][here.x] > 0 && board[here.y][here.x] != player) continue;
                    board[here.y][here.x] = player;
                    for(int dir = 0; dir < 4; dir++){
                        int ny = here.y + dy[dir];
                        int nx = here.x + dx[dir];
                        if(ny < 0 || ny >= N || nx < 0 || nx >= M || board[ny][nx] != 0) continue;
                        dist[ny][nx] = dist[here.y][here.x] + 1;
                        if(dist[ny][nx] <= s[player]){
                            board[ny][nx] = player;
                            q[player].add(new Point(ny, nx));
                        }
                    }
                }
                q[player] = nq[player];
                nq[player] = new LinkedList<>();
            }
            if(!ok) break;
        }
        
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                if(board[i][j] > 0)
                    ret[board[i][j]]++;

        for(int i = 1; i <= P; i++)
            System.out.print(ret[i] + " ");
    }
}
