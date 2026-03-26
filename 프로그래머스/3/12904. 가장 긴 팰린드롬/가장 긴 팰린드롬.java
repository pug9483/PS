class Solution{
    private int N;
    public int solution(String s){
        N = s.length();    
        int answer = 1;
        
        for(int i = 0; i < N; i++){
            answer = Math.max(answer, go(s, i, i));
            answer = Math.max(answer, go(s, i, i+1));
        }
        
        return answer;
    }
    
    private int go(String s, int left, int right){
        while(left >= 0 && right < N && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        
        return right - left - 1;
    }
}