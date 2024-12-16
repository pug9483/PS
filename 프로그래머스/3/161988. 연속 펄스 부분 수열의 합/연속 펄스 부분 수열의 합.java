import java.util.*;

class Solution {
    int N;
    public long solution(int[] sequence) {
        N = sequence.length;
        long answer = 0;
        
        long[] A = new long[N];
        long[] B = new long[N];
        
        for(int i = 0, pulse = 1; i < N; i++, pulse *= -1){
            A[i] = sequence[i] * pulse * 1L;
            B[i] = sequence[i] * pulse * -1L;
        }
        
        return Math.max(solve(A), solve(B));
    }
    
    public long solve(long[] A){
        long ret = 0;
        
        long[] dp = new long[N];
        ret = dp[0] = A[0];
        
        for(int i = 1; i < N; i++){
            dp[i] = Math.max(dp[i-1] + A[i], A[i]);
            ret = Math.max(ret, dp[i]);
        }
    
        return ret;
    }
}