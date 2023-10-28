import java.io.*;
import java.util.*;

public class Main {           
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] board;
    public static int[][] memo;
    public static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N];
        memo = new int[N][3];
        for(int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        String s = br.readLine();
        for(int i = 0; i < N; i++){
            char c = s.charAt(i);
            if(c == 'B') board[i] = 0;
            if(c == 'O') board[i] = 1;
            if(c == 'J') board[i] = 2;
        }
        int ret = solve(0, 0);
        System.out.println(ret == INF ? -1 : ret);
    }
    
    public static int solve(int here, int type){
        if(here == N-1) return 0;
        if(memo[here][type] != -1) return memo[here][type];
        int ret = INF;
        for(int next = here+1; next < N; next++){
            if(board[next] == (type + 1) % 3)
                ret = Math.min(ret, (next - here) * (next - here) + solve(next, board[next]));
        }
        memo[here][type] = ret;
        return ret;
    }
}