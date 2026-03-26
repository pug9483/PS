import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int[] answer = new int[N];
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            map.put(enroll[i], i);
        }
        
        int[] parent = new int[N];
        for(int i = 0; i < N; i++){
            if(referral[i].equals("-")) parent[i] = -1;
            else parent[i] = map.get(referral[i]);
        }
        
        for(int i = 0; i < seller.length; i++){
            int idx = map.get(seller[i]);
            int money = amount[i] * 100;
            
            while(idx != -1 && money > 0){
                int up = money / 10;
                
                answer[idx] += money - up;
                
                idx = parent[idx];
                money = up;
            }
        }
        
        return answer;
    }
}