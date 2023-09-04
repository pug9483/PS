import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static long[][] memo;
    
    public static void main(String[] args) throws IOException {
        String s = "";
        while(!(s = br.readLine()).equals("0")){
            N = Integer.parseInt(s);
            memo = new long[N+1][N+1];
            for(int i = 0; i < N+1; i++) Arrays.fill(memo[i], -1);
            sb.append(solve(N, 0)).append("\n");
        }
        System.out.print(sb);
    }
    
    public static long solve(int one, int half){
        if(one < 0 || half < 0) return 0L;
        if(one == 0 && half == 0) return 1L;
        if(memo[one][half] != -1) return memo[one][half];
        long ret = solve(one-1, half+1) + solve(one, half-1);
        return memo[one][half] = ret;
    }
}