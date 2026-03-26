import java.util.*;

class Solution{
    private int N;
    private int[][] dp;
    
    public int solution(String s){
        N = s.length();    
        dp = new int[N][N];
        
        int answer = 1;
        
        for(int left = 0; left < N; left++){
            for(int right = left; right < N; right++){
                if(isGo(s, left, right)){
                    answer = Math.max(answer, right - left + 1);
                }
            }
        }
        
        return answer;
    }
    
    private boolean isGo(String s, int left, int right){
        if(left >= right) return true;
        if(dp[left][right] != 0) return dp[left][right] == 1;
        
        if(s.charAt(left) != s.charAt(right)){
            dp[left][right] = -1;
            return false;
        }
        
        if(isGo(s, left + 1, right - 1)){
            dp[left][right] = 1;
            return true;
        }
        
        dp[left][right] = -1;
        return false;
    }
}