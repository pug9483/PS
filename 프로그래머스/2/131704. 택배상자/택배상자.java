// 1번 상자부터 n번 상자까지 번호가 증가하는 순서대로 컨테이너 벨트에 일렬로 놓여 영재에게 전달됩니다.
// 택배 기사님이 미리 알려준 순서에 맞게 영재가 택배상자를 실어야 합니다.
// Stack 보조 컨테이너 벨트를 추가로 설치하였습니다.

import java.util.*;

class Solution {
    public int N;
    public int solution(int[] order) {
        int answer = 0;
        N = order.length;
        int idx = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < N; i++){
            stack.add(i+1);
            
            while(!stack.isEmpty()){
                if(stack.peek() == order[idx]){
                    stack.pop();
                    answer++;
                    idx++;
                }
                else break;
            }
        }
        
        return answer;
    }
}