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
    public static char[][] board;
    public static int N, M;
    public static String hasKey = "";
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N+2][M+2];
            for(int i = 0; i < N+2; i++){
                for(int j = 0; j < M+2; j++) 
                    board[i][j] = '.';
            }
            
            for(int i = 1; i <= N; i++){
                String s = br.readLine();
                for(int j = 1; j <= M; j++){
                    board[i][j] = s.charAt(j-1);
                }
            }
            hasKey = br.readLine();
            System.out.println(solve());
        }
    }
    
    public static int solve(){
        int set = 0;
        int ret = 0;
        if(!hasKey.equals("0")) 
            for(int i = 0; i < hasKey.length(); i++) 
                set |= (1 << (hasKey.charAt(i) - 'a'));
        Queue<Point> q = new LinkedList<>();
        List<Stack<Point>> wList = new ArrayList<>();
        for(int i = 0; i < 26; i++) wList.add(new Stack<>());
        boolean[][] visited = new boolean[N+2][M+2];
        q.add(new Point(0, 0));
        visited[0][0] = true;
        while(!q.isEmpty()){
            Point here = q.poll();
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N+2 || nx < 0 || nx >= M+2) continue;
                if(board[ny][nx] == '*' || visited[ny][nx]) continue;
                if(board[ny][nx] >= 'A' && board[ny][nx] <= 'Z'){
                    if((set & (1 << board[ny][nx] - 'A')) != 0){
                        board[ny][nx] = '.';
                        visited[ny][nx] = true;
                        q.add(new Point(ny, nx));
                    }
                    else{
                        wList.get(board[ny][nx] - 'A').add(new Point(ny, nx));
                    }
                }
                else if(board[ny][nx] >= 'a' && board[ny][nx] <= 'z'){
                    int keyIndex = board[ny][nx] - 'a';
                    set |= (1 << keyIndex);
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                    while(!wList.get(keyIndex).isEmpty()){
                        Point p = wList.get(keyIndex).pop();
                        if(visited[p.y][p.x]) continue;
                        board[p.y][p.x] = '.';
                        visited[p.y][p.x] = true;
                        q.add(new Point(p.y, p.x));
                    }
                }
                else if(board[ny][nx] == '$'){
                    ret++;
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                }
                else{
                    visited[ny][nx] = true;
                    q.add(new Point(ny, nx));
                }
            }
        }
        return ret;
    }
}
