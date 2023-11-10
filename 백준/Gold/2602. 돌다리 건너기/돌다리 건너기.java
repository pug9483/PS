import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static String S;
    public static String[] A = new String[2];
    public static int[][][] dp = new int[110][30][2];
    
    public static void main(String[] args) throws IOException{
        S = br.readLine();
        N = S.length();
        A[0] = br.readLine();
        A[1] = br.readLine();
        M = A[0].length();
        for(int i = 0; i < 110; i++)
            for(int j = 0; j < 30; j++)
                Arrays.fill(dp[i][j], -1);
        System.out.println(solve(-1, 0, 0) + solve(-1, 0, 1));
    }
    
    // here: 다리에서의 현재 위치, pos: 두루마리에서의 현재 위치, up: 윗층이면 0, 아래면 1
    public static int solve(int here, int pos, int up){
        if(pos == S.length()) return 1;
        if(dp[here+1][pos][up] != -1) return dp[here+1][pos][up];
        
        int ret = 0;
        for(int next = here+1; next < M; next++){
            if(A[(up+1)%2].charAt(next) == S.charAt(pos))
                ret += solve(next, pos+1, (up+1)%2);
        }
        
        return dp[here+1][pos][up] = ret;
    }
}