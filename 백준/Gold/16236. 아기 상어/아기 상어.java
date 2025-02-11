import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    static class Fish implements Comparable<Fish>{
        Point p;
        int size;
        int dist = INF;
        
        public Fish(int y, int x, int size, int dist){
            p = new Point(y, x);
            this.size = size;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Fish f){ //사이즈는 고려하지 않음
            // 거리가 가까운 것
            int comp = Integer.compare(this.dist, f.dist);
            // 거리가 가까운 물고기가 많을 경우, 가장 위 가장 왼쪽부터
            if(comp == 0){
                int comp2 = Integer.compare(this.p.y, f.p.y);
                if(comp2 == 0){
                    return Integer.compare(this.p.x, f.p.x);
                }
                return comp2;
            }
            return comp;
        }
    }
    
    static class Shark{
        Point p;
        int eatenCnt = 0; // 먹은 물고기의 개수
        int size = 2; // 상어의 크기
        
        public Shark(int y, int x){
            p = new Point(y, x);
        }
    }
    
    public static final int INF = 987654321;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] board;
    private static List<Fish> fishes = new ArrayList<>();
    private static Shark shark;
    private static int[] dy = {-1, 0, 0, 1};
    private static int[] dx = {0, -1, 1, 0};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9) {
                    shark = new Shark(i, j);
                    board[i][j] = 0;
                }
            }
        }   
        // System.out.printf("shark -> [y = %d, x = %d, eat: %d, size: %d]\n",shark.p.y, shark.p.x, shark.eatenCnt, shark.size);
        System.out.println(solve());
    }
    
    private static int solve(){
        int ret = 0;
        while(true){
            computeDist(shark); // 상어가 먹을 수 있는 물고기들만 저장한다.
            if(fishes.size() == 0) break;
            Collections.sort(fishes); // 거리가 가장 가까우면서 왼쪽 위
            // System.out.printf("shark -> [y = %d, x = %d, eat: %d, size: %d, time: %d]\n",shark.p.y, shark.p.x, shark.eatenCnt, shark.size, ret);
            // printFishes();
            
            Fish fish = fishes.get(0); 
            ret += fish.dist;
            shark.p.y = fish.p.y;
            shark.p.x = fish.p.x;
            board[fish.p.y][fish.p.x] = 0;
            shark.eatenCnt++;
            if(shark.eatenCnt == shark.size){
                shark.size++;  
                shark.eatenCnt = 0;
            } 
            fishes.clear();
        }
        return ret;
        // printFishes();
    }
    
    private static void printFishes(){
        System.out.println("fish");
        fishes.stream().forEach(s -> System.out.printf("[y, x, size, dist] : [%d, %d, %d, %d]\n", s.p.y, s.p.x, s.size, s.dist));
    }
    
    private static void computeDist(Shark shark){
        int[][] dists = new int[N][N];
        dists[shark.p.y][shark.p.x] = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(shark.p.y, shark.p.x)); // 상어 자리
        int sharkSize = shark.size;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            int y = p.y;
            int x = p.x;
            int dist = dists[y][x];
            if(board[y][x] > 0 && sharkSize > board[y][x]){ // 상어보다 작은 물고기이므로 저장한다.
               fishes.add(new Fish(y, x, board[y][x], dist));
            }
            for(int dir = 0; dir < 4; dir++){
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || dists[ny][nx] != 0) continue;
                if(board[ny][nx] > sharkSize) continue;
                dists[ny][nx] = dist + 1;
                queue.add(new Point(ny, nx));
            }
        }
    }
}