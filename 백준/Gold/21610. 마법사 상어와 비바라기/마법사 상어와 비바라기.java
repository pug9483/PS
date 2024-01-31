import java.io.*;
import java.util.*;

public class Main {  
    static class Node{
        int d, s;
        public Node(int d, int s){
            this.d = d;
            this.s = s;
        }
    }
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static int[][] A;
    public static List<Node> list = new ArrayList<>();
    public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            list.add(new Node(d, s));
        }
        System.out.println(solve());
    }    
    
    public static int solve(){
        int ret = 0;
        
        List<Point> clouds = new ArrayList<>();
        clouds.add(new Point(N-1, 0));
        clouds.add(new Point(N-1, 1));
        clouds.add(new Point(N-2, 0));
        clouds.add(new Point(N-2, 1));
        
        for(int i = 0; i < M; i++){
            boolean[][] visited = new boolean[N][N];
            Node info = list.get(i);
            
            // 모든 구름이 di방향으로 si칸 이동
            List<Point> movesClouds = moveClouds(clouds, info);
            
            // 물의 양 늘리기
            watering(movesClouds);
            
            // 구름 지우기
            clouds.clear();
            removeClouds(movesClouds, visited);
            
            // 물복사 버그 시전하기
            waterCopyBug(movesClouds);

            // 구름 생성
            createClouds(clouds, visited);
        }
        
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                ret += A[i][j];
        
        return ret;
    }
    
    public static List<Point> moveClouds(List<Point> clouds, Node info){
        List<Point> points = new ArrayList<>();
        for(Point point: clouds){
            int ny = 0, nx = 0;
            
            ny = (point.y + dy[info.d]*info.s + 100*N) % N;
            nx = (point.x + dx[info.d]*info.s + 100*N) % N;
            points.add(new Point(ny, nx));
        }
        return points;
    }
    
    public static void watering(List<Point> clouds){
        for(Point point: clouds){
            A[point.y][point.x]++;
        }
    }
    
    public static void removeClouds(List<Point> clouds, boolean[][] visited){
        for(Point point: clouds){
            visited[point.y][point.x] = true;
        }

    }
    
    public static void waterCopyBug(List<Point> clouds){
        for(Point point: clouds){
            int sum = 0;
            for(int dir = 1; dir < 8; dir+=2){
                int ny = point.y + dy[dir];
                int nx = point.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if(A[ny][nx] > 0) sum++;
            }
            A[point.y][point.x] += sum;
        }
    }
    
    public static void createClouds(List<Point> clouds, boolean[][] visited){
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(A[y][x] >= 2 && !visited[y][x]){
                    clouds.add(new Point(y, x));
                    A[y][x] -= 2;
                }
            }
        }
    }
}