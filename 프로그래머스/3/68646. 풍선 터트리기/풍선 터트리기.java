class Solution {
    public int solution(int[] a) {
        int N = a.length;
        
        if(N <= 2) return N;
        
        int[] B = new int[N];
        B[0] = a[0];
        
        for(int i = 1; i < N; i++){
            B[i] = Math.min(B[i-1], a[i]);
        }
        
        int rMin = Integer.MAX_VALUE; 
        int answer = 0;
        
        for(int i = N-1; i >= 0; i--){
            if(i == 0 || i == N-1) answer++;
            else if(a[i] < B[i-1] || a[i] < rMin){
                answer++;
            }
            rMin = Math.min(rMin, a[i]);
        }
        
        
        return answer;
    }
}