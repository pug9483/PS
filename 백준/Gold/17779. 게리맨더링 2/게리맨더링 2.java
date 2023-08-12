import java.io.*;
import java.util.*;

public class Main {          
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] board;
    public static final int INF = 987654321;
    public static int sum = 0;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                sum += board[i][j];
            }
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = INF;
        for(int y = 1; y <= N; y++){
            for(int x = 1; x <= N; x++){
                for(int d1 = 1; d1 < N; d1++){
                    for(int d2 = 1; d2 < N; d2++){
                        ret = Math.min(ret, go(y, x, d1, d2));
                    }
                }
            }
        }
        return ret;
    }
    
    public static int go(int y, int x, int d1, int d2){
        if(y+d1+d2 > N || x-d1 <= 0 || x+d2 > N) return INF; 
        // 1번
        int a = 0;
        for(int l = y+d1-1, m = x-d1; l >= 1; l--, m++){
            for(int n = 1; n <= Math.min(m, x); n++){
                a += board[l][n];
            }
        }
        // 2번
        int b = 0;
        for(int l = y+d2, m = x+d2+1; l >= 1; l--, m--){
            for(int n = Math.max(m, x+1); n <= N; n++){
                b += board[l][n];
            }
        }
        
        // 3번
        int c = 0;
        for(int l = y+d1, m = x-d1-1; l <= N; l++, m++){
            for(int n = 1; n <= Math.min(m, x + d2 - d1 - 1); n++){
                c += board[l][n];
            }
        }
        // 4번
        int d = 0;
        for(int l = y+d2+1, m = x+d2; l <= N; l++, m--){
            for(int n = Math.max(m, x + d2 - d1); n <= N; n++){
                d += board[l][n];
            }
        }
        int e = sum - (a + b + c + d);
        int max = Math.max(Math.max(Math.max(Math.max(a, b), c), d), e);
        int min = Math.min(Math.min(Math.min(Math.min(a, b), c), d), e);
        return max - min;
    }
}