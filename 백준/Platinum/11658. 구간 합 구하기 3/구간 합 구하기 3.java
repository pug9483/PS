import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    public static int N; 
    public static int M; 
    public static int K; 
    public static long[][] A;
    public static long[][] tree; 
    
    public static long sum(int y, int x){ // 합 구하기
        long ans = 0;
        for (int i = y; i > 0; i -= i&-i) {
            for (int j = x; j > 0; j -= j&-j) {
                ans += tree[i][j];
            }
        }
        return ans;
    }
    
    public static void update(int y, int x, long val){ // 갱신하기
         for (int i = y; i <= N; i += i&-i) {
            for (int j = x; j <= N; j += j&-j) {
                tree[i][j] += val;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        A = new long[N+1][N+1];
        tree = new long[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                A[i][j] = Long.parseLong(st.nextToken());
                update(i, j, A[i][j]);
            }
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            if(w == 0){ // 바꾸기 연산
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                Long c = Long.parseLong(st.nextToken());
                update(y, x, c - A[y][x]);
                A[y][x] = c;
            }
            else{ // 출력
                int y1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                long ret = sum(y2, x2) - sum(y2, x1-1) - sum(y1-1, x2) + sum(y1-1, x1-1);
                sb.append(ret).append("\n");
            }
        }
        System.out.print(sb);
    }
}