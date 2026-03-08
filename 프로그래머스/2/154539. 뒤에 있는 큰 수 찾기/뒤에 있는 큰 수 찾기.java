import java.util.*;

class Solution {
    public int N;
    
    public int[] solution(int[] numbers) {
        N = numbers.length;
        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                int idx = stack.pop();
                answer[idx] = numbers[i];
            }
            stack.push(i);
        }
        
        return answer;
    }
}