/*
 * [근무 태도 점수, 동료 평가 점수] 
 * 임의의 다른 사원보다 두 점수가 모두 낮으면 인센티브를 받지 못함
 * 두 점수의 합이 높은 순부터 인센티브 차등 지급
 */
import java.util.*;

class Solution {
    int N;
    public int solution(int[][] scores) {
        N = scores.length;
        int[] wonho = scores[0];
        List<int[]> persons = new ArrayList<>();
        Arrays.sort(scores, (o1, o2) -> {
            int cmp = Integer.compare(o2[0], o1[0]);
            return cmp == 0? Integer.compare(o1[1], o2[1]) : cmp;
        });
        
        int maxColleagueScore = 0;
        int prev = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            if(scores[i][1] >= maxColleagueScore){
                persons.add(scores[i]);
                maxColleagueScore = Math.max(maxColleagueScore, scores[i][1]);
            }
        }
        
        persons.sort((o1, o2) -> Integer.compare(o2[0] + o2[1], o1[0] + o1[1]));
        
        int prevTotalScore = -1;
        int ret = 1;
        int sameGrade = 0;
        for(int[] person : persons){
            if(prevTotalScore == person[0] + person[1]){
                sameGrade++;    
            }
            else{
                ret += sameGrade;
                sameGrade = 1;
            }
            prevTotalScore = person[0] + person[1];
            // System.out.printf("[%d,%d] -> [%d, %d, %d]\n", person[0], person[1], sameGrade, ret, person[0] + person[1]);
            
            if(person == wonho) return ret;
        }
        return -1;
    }
}