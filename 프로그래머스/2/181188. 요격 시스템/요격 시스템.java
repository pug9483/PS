import java.util.*;

class Solution {
    int N;
    public int solution(int[][] targets) {
        N = targets.length;
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        int e = -1;
        for(int i = 0; i < N; i++){
            if(targets[i][0] >= e){
                answer++;
                e = targets[i][1];
            }
        }
        return answer;
    }
}