import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
    public static char[][] A;
	public static int[][] B;
    public static int sy, sx;
    public static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static int[] heights;
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new char[N][N];
        B = new int[N][N];
        heights = new int[N*N];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                A[i][j] = s.charAt(j);
                if(A[i][j] == 'P'){
                    sy = i;
                    sx = j;
                }
            }
        }
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
                heights[i*N + j] = B[i][j];
            }
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = Integer.MAX_VALUE;
        Arrays.sort(heights);
        List<Integer> list = new ArrayList<>();
        for(int height: heights){
            // if(!list.isEmpty() && list.get(list.size() - 1) == height) continue;
            list.add(height);
        }
        for(int i = 0; i < list.size(); i++){
            int left = i;
            int right = list.size() - 1;
            int minValue = list.get(i);
            while(left <= right){
                int mid = (left + right) / 2;
                int maxValue = list.get(mid);
                if(bfs(minValue, maxValue)){
                    ret = Math.min(ret, maxValue - minValue);
                    right = mid-1;
                }
                else left = mid+1;
            }
        }
        return ret;
    }
    
    public static boolean bfs(int min, int max){
        if(B[sy][sx] < min || B[sy][sx] > max) return false;
        boolean[][] visited = new boolean[N][N];
        visited[sy][sx] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sy, sx));
        
        while(!q.isEmpty()){
            Point here = q.poll();
            for(int dir = 0; dir < 8; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
                if(B[ny][nx] < min || B[ny][nx] > max) continue;
                visited[ny][nx] = true;
                q.add(new Point(ny, nx));
            }
        }
          
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(A[y][x] == '.') continue;
                if(!visited[y][x]) return false;
            }
        }
        return true;
    } 
}