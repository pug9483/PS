// 각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나누어진 것으로 생각합니다.
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int rCount = 0;
        
        int[] rTypes = new int[10001];
        for(int i = 0; i < topping.length; i++){
            rTypes[topping[i]]++;
            if(rTypes[topping[i]] == 1){
                rCount++;
            }
        }
        
        System.out.println(rCount);
        int[] lTypes = new int[10001];
        int lCount = 0;
        for(int i = 0; i < topping.length; i++){
            lTypes[topping[i]]++;
            rTypes[topping[i]]--;
            
            if(lTypes[topping[i]] == 1){
                lCount++;
            }
            if(rTypes[topping[i]] == 0){
                rCount--;
            }
            
            if(lCount == rCount){
                answer++;
            }
        }
        return answer;
    }
}