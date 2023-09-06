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
    public static int N, M, K;
    public static boolean[][] visited;
    public static int[] Size;
    public static List<int[][]> list = new ArrayList<>();
    public static int ret = 0;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        Size = new int[K];
        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] A = new int[R][C];
            int size = 0;
            for(int i = 0; i < R; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < C; j++){
                    int num = Integer.parseInt(st.nextToken());
                    A[i][j] = num;
                    if(num == 1) size++;
                }
            }
            list.add(A);
            Size[k] = size;
        }
        go(0);
        System.out.println(ret);
    }
    
    public static int[][] rotate(int R, int C, int[][] A){
        int[][] B = new int[C][R];
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                B[j][R-1-i] = A[i][j];
        return B;
    }
    
    public static void go(int idx){
        if(idx == K) return;
        int y = -1, x = -1;    
        int[][] A = list.get(idx);
        
        go:
        for(int r = 0; r < 4; r++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(check(i, j, A)){
                        ret += Size[idx];
                        break go;
                    }
                }
            }
            A = rotate(A.length, A[0].length, A);
        }
        go(idx+1);
    }
    
    public static boolean check(int y, int x, int[][] A){
        Stack<Point> stack = new Stack<>();
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++){
                if((y + i < 0 || y + i >= N || x + j < 0 || x + j >= M) || (A[i][j] == 1 && visited[y+i][x+j])){
                   while(!stack.isEmpty()){
                        Point here = stack.pop();
                        visited[here.y][here.x] = false;
                    }
                    return false;
                }
                else if(A[i][j] == 1 && !visited[y+i][x+j]){
                    visited[y+i][x+j] = true;
                    stack.add(new Point(y+i, x+j));
                }
            }
        }
        return true;
    }
}