import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    public static int N;
    public static boolean[][] visited = new boolean[101][101];
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken()); // (y, x)
            int d = Integer.parseInt(st.nextToken()); // 시작 방향(0: ->, 1: ^, 2: <-, 3 v)
            int g = Integer.parseInt(st.nextToken()); // 세대
            draw(y, x, d, g);
        }
        System.out.println(solve());
        // printBoard();
    }
    
    public static void printBoard(){
        int NUM = 10;
        for(int i = 0; i < NUM; i++){
            for(int j = 0; j < NUM; j++){
                if(visited[i][j]) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
        }
    }
    
    public static void draw(int y, int x, int d, int g){
        visited[y][x] = true;
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d);
        
        while(g-- > 0){    
            for(int i = dirs.size() - 1; i >= 0; i--){
                int dir = (dirs.get(i) + 1) % 4;
                dirs.add(dir);
            }
        }
        
        for(int dir: dirs){
            if(dir == 0) visited[y][++x] = true;
            else if(dir == 1) visited[--y][x] = true;
            else if(dir == 2) visited[y][--x] = true;
            else visited[++y][x] = true;
        }
    }
    
    public static int solve(){
        int ret = 0;
        
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1])
                    ret++;
            }
        }
        
        
        return ret;
    }
}
