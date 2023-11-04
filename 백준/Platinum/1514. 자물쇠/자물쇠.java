/*
 * dp[i][j][k]: i번째 디스크가 현재 j만큼 돌아가 있고, 다음 칸은 k만큼 돌아가 있을 때, 디스크를 돌리는 횟수의 최솟값
 * i번째 디스크를 기준으로 연속 세 칸은 three만큼 연속 두 칸은 two만큼 돌린다.
 * 다음 상태: dp[i+1][(k+two+three)%10][three]
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String src;
    public static String dst;
    public static int[][][] dp = new int[100][10][10];
    public static final int[] turn = {0, 1, 1, 1, 2, 2, 2, 1, 1, 1};
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        src = br.readLine();
        dst = br.readLine();
        for(int i = 0; i < 100; i++)
            for(int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        System.out.println(solve(0, 0, 0));
    }
    
    public static int solve(int i, int j, int k){
        if(i == N) return 0;
        if(dp[i][j][k] != -1) return dp[i][j][k];
        
        int ret = INF;
        int ori = (src.charAt(i) - '0' + j + 10) % 10;
        int to = dst.charAt(i) - '0';
        for(int three = 0; three < 10; three++){
            for(int two = 0; two < 10; two++){
                int from = (ori + two + three) % 10;
                int one = (to - from + 10) % 10;
                int cost = solve(i+1, (k+two+three)%10, three) + turn[one] + turn[two] + turn[three];
                ret = Math.min(ret, cost);
            }
        }
        return dp[i][j][k] = ret;
    }
}