/*
힌트: 하나의 열쇠를 얻으면 해당하는 모든 문을 열 수 있다.

BFS를 큐 27개를 이용하여 수행한다.
1. 큐(1): 일반적인 BFS
2. 알파벳 큐(26): 문을 열기 위해 기다리는 큐
=> 해당 알파벳에 해당하는 열쇠를 얻는 순간, 그 알파벳에 해당하는 알파벳 큐의 모든 정점들을 큐에 넣는다.
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
    public static char[][] board;
    public static int N, M;
    public static boolean[] key;
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 2;
            M = Integer.parseInt(st.nextToken()) + 2;
            board = new char[N][M];
            key = new boolean[26];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++) 
                    board[i][j] = '.';
            }
            
            for(int i = 1; i < N-1; i++){
                String s = br.readLine();
                for(int j = 1; j < M-1; j++){
                    board[i][j] = s.charAt(j-1);
                }
            }
            String s = br.readLine();
            if(!s.equals("0")){
                for(int i = 0; i < s.length(); i++)
                    key[s.charAt(i) - 'a'] = true;
            }
            System.out.println(solve());
        }
    }
    
    public static int solve(){
        int ret = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        Queue<Point>[] door = new Queue[26];
        for(int i = 0; i < 26; i++) door[i] = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            Point here = q.poll();
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(check(ny, nx) || board[ny][nx] == '*' || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                char w = board[ny][nx];
                if(w == '.') q.add(new Point(ny, nx));
                else if(w == '$'){
                    ret++;
                    q.add(new Point(ny, nx));
                }
                else if(w >= 'A' && w <= 'Z'){
                    if(key[w - 'A']) q.add(new Point(ny, nx));
                    else door[w - 'A'].add(new Point(ny, nx));
                }
                else if(w >= 'a' && w <= 'z'){
                    q.add(new Point(ny, nx));
                    if(!key[w - 'a']){
                        key[w - 'a'] = true;
                        while(!door[w - 'a'].isEmpty()){
                            q.add(door[w - 'a'].poll());
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    public static boolean check(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}
