import java.io.*;
import java.util.*;

public class Main {
    static class Seat{
        int state, count;
        public Seat(int state, int count){
            this.state = state;
            this.count = count;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static char[][] A;
    public static int[][] dp;
    public static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new char[N+1][M];
            dp = new int[N+1][1<<M];
            for(int i = 1; i <= N; i++){
                A[i] = br.readLine().toCharArray();
            }
            sb.append(solve()).append("\n");
        }
        System.out.print(sb);
    }
    
    public static int solve(){
        int ret = 0;
        List<Seat> seats = new ArrayList<>();
        for(int state = 0; state < (1 << M); state++){
            if(isAdjacent(state)) continue;
            int count = 0;
            for(int j = 0; j < M; j++){
                if(((1<<j) & state) >= 1) count++;
                seats.add(new Seat(state, count));
            }
        }
        
        for(int i = 1; i <= N; i++){
            for(Seat seat: seats){
                if(isBrokenChair(i, seat.state)) continue;
                for(Seat fseat: seats){
                    if(check(seat.state, fseat.state)){
                        dp[i][seat.state] =  Math.max(dp[i][seat.state], dp[i-1][fseat.state] + seat.count);
                        ret = Math.max(ret, dp[i][seat.state]);
                    }
                }
            }
        }
        return ret;
    }
    
    public static boolean isAdjacent(int state){
        for(int j = 0; j < M-1; j++){
            int val = (3 << j); // 붙어있는 자리 표시
            if((state & val) == val) return true;
        }
        return false;
    }
    
    public static boolean isBrokenChair(int row, int state){
        for(int j = 0; j < M; j++){
            if(A[row][j] == 'x' && (state & (1 << j)) >= 1) return true;
        }
        return false;
    }
    
    public static boolean check(int state1, int state2){
        for(int j = 0; j < M; j++){
            if(((1 << j) & state2) >= 1){
                if(j > 0 && (((1 << (j-1)) & state1) >= 1)) return false;
                if(((1 << (j+1)) & state1) >= 1) return false;
            }
        }
        return true;
    }
}