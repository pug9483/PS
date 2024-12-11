import java.util.*;

class Solution {
    double[] rates = {1.0, 2.0/3.0, 2.0/4.0, 3.0/4.0};
    int N;
    public long solution(int[] weights) {
        long answer = 0;
        N = weights.length;
        Map<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights);
        
        for(int num : weights){
            for(double rate : rates){
                double value = 1.0 * num * rate;
                if(map.containsKey(value)) answer += map.get(value);
            }
            map.put(num * 1.0, map.getOrDefault((num * 1.0), 0) + 1);
        }
        
        return answer;
    }
}