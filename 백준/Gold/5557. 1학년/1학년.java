import java.io.*;
import java.util.*;

public class Main {           
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static long[][] memo;
    
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        memo = new long[N][21];
        for(int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve(1, A[0]));
    }
    
    public static long solve(int index, int sum){
        if(index == N-1){
            if(sum == A[N-1]) return 1;
            return 0;
        }
        if(memo[index][sum] != -1) return memo[index][sum];
        long ret = 0L;
        if(sum + A[index] <= 20) ret += solve(index+1, sum + A[index]);
        if(sum - A[index] >= 0) ret += solve(index+1, sum - A[index]);
        memo[index][sum] = ret;
        return ret;
    }
}