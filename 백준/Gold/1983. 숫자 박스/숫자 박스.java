import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Integer> listA = new ArrayList<>();
    public static List<Integer> listB = new ArrayList<>();
    public static int[][][] dp;
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N+1][N+1][N+1];
        st = new StringTokenizer(br.readLine());
        listA.add(0);
        listB.add(0);
        for(int j = 0; j < N; j++){
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) continue;
            listA.add(num);
        }
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < N; j++){
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) continue;
            listB.add(num);
        }
        System.out.println(solve());
    }
     
    public static int solve(){
        for(int k = 1; k <= N; k++){
            for(int i = 1; i < listA.size(); i++){
                for(int j = 1; j < listB.size(); j++){
                    if(i > k) continue;
                    if(j > k) continue;
                    int cur = dp[k-1][i-1][j-1] + listA.get(i) * listB.get(j);
                    if(j >= 1 && k >= i+1) cur = Math.max(cur, dp[k-1][i][j-1]);
                    if(i >= 1 && k >= j+1) cur = Math.max(cur, dp[k-1][i-1][j]);
                    dp[k][i][j] = cur;
                }
            }
        }
        return dp[N][listA.size()-1][listB.size()-1];
    }
}