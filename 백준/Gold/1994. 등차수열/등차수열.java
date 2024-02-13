import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    /* dp[i][j] = A[j] - A[i]를 공차로 하면서 만들 수 있는 가장 큰 등차수열의 길이
     * dp[i][j] = dp[i][next] + 1 (A[next] - A[j] == A[j] - A[i])
     * next는 이분탐색을 통해 얻는다.
     */
    
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
    public static int[] A;
    public static int[][] dp;
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        for(int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);
        Arrays.sort(A);
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 1;
        // 공차가 0인 것들은 따로 구해주기
        int zd = 1;
        for(int i = 1; i < N; i++){
            if(A[i-1] == A[i]){
                zd++;
                ret = Math.max(ret, zd);
            }
            else zd = 1;
        }
        
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                ret = Math.max(ret, go(i, j));
            }
        }
        return ret;
    }
    
    public static int go(int i, int j){
        if(i > j) return 0;
        if(i == j) return 1;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        int ret = 0;
        int d = A[j] - A[i];
        int next = Arrays.binarySearch(A, A[j] + d);
        if(next < 0) return 2;
        ret = go(j, next) + 1;
        return dp[i][j] = ret;
    }
}