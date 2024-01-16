import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N = 10;
    public static int[][] A = new int[N][N];
    public static final int INF = 987654321;
    public static int ret = INF;
    
    public static void main(String[] args) throws IOException{
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] counts = new int[6];
        int[][] nums = new int[N][N];
        solve(0, 0, 0, counts, nums);
        System.out.println(ret == INF ? -1 : ret);
    }
    
    public static void solve(int y, int x, int sum, int[] counts, int[][] nums){
        if(x == N){
            y++;
            x = 0;
        }
        if(y == N) {
            ret = Math.min(sum, ret);
            return;
        } 
        if(sum >= ret) return;

        if(A[y][x] == 0 || nums[y][x] == 1){
            solve(y, x+1, sum, counts, nums);  
            return;
        } 
        
        for(int len = 5; len >= 1; len--){
            if(check(y, x, len, nums) && counts[len] < 5){
                counts[len]++;
                fill(y, x, len, 1, nums);
                solve(y, x+1, sum+1, counts, nums);
                counts[len]--;
                fill(y, x, len, 0, nums);
            }
        }    
    }
    
    public static boolean check(int sy, int sx, int len, int[][] nums){
        for(int i = sy; i < sy + len; i++){
            for(int j = sx; j < sx + len; j++){
                if(i < 0 || i >= N || j < 0 || j >= N) return false;
                if(A[i][j] == 0 || nums[i][j] == 1) return false;
            }
        }
        return true;
    }
    
    public static void fill(int sy, int sx, int len, int value, int[][] nums){
        for(int i = sy; i < sy + len; i++){
            for(int j = sx; j < sx + len; j++){
                nums[i][j] = value;
            }
        }
    }
}