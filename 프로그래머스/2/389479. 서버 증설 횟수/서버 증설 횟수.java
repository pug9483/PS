import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] cnts = new int[25];
        
        for(int hour = 0; hour < 24; hour++){
            int player = players[hour];
            int cnt = 0;
            for(int j = hour-1; j > Math.max(-1, hour-k); j--){
                cnt += cnts[j];
            }
            int left = Math.max(player - cnt*m, 0);
            cnts[hour] = left / m;
        }
        return Arrays.stream(cnts).reduce(0, Integer::sum);
    }
}