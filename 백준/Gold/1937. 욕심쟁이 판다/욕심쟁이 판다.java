import java.io.BufferedReader;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.*;
import java.lang.Math;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] board;
    private static int[][] cache;
    private static boolean[][] visited;
    private static final int INF = 987564321;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        cache = new int[N][N];
        visited = new boolean[N][N];
        initCache(cache);
        int ret = -INF;
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                visited[i][j] = true;
                ret = Math.max(ret, solve(i, j));
                visited[i][j] = false;
            }
        }
        System.out.println(ret);
    }
    
    public static int solve(int y, int x){
        int ret = 1;
        
        if(cache[y][x] != -1) return cache[y][x];
        
        for(int dir = 0; dir < 4; dir++){
            int ny = y + dy[dir], nx = x + dx[dir];
            if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
            if(board[ny][nx] <= board[y][x]) continue;
            visited[ny][nx] = true;
            ret = Math.max(ret, 1 + solve(ny, nx));            
            visited[ny][nx] = false;
        }
        cache[y][x] = ret;
        return ret;
    }
    
    public static void initCache(int[][] cache){
        for(int i = 0; i < N; i++)
            Arrays.fill(cache[i], -1);
    }
}
