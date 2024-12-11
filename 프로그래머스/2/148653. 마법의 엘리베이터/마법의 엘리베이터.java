/**
 * 10^c(c>=0)형태의 정수들이 적힌 버튼이 있다.
 * 다음 층수 = 현재 층수 + 버튼에 적혀있는 값(합이 0이상일 경우)
 * 0층으로 가는 거리의 최소 개수
 */
import java.util.*;

class Solution {    
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0){
            int one = storey % 10;
            int ten = (storey / 10) % 10;
            
            if(one > 5){
                answer += (10 - one);
                storey += 10;
            }
            else if(one == 5){
                answer += one;
                storey += (ten < 5 ? 0 : 10);
            }
            else{
                answer += one;
            }
            
            storey /= 10;
        }
        
        
        return answer;
    }
}