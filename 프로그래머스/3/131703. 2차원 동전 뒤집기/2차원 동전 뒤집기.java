class Solution {
    int INF = 987654321;
    int N;
    int M;
    
    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;
        int answer = solve(0, 0, beginning, target);
        return answer >= INF ? -1 : answer;
    }
    
    public int solve(int here, int count, int[][] beginning, int[][] target){
        if(here >= M){
            return count + checkRowLines(beginning, target);
        }
        
        int a = solve(here+1, count, beginning, target);
        
        swapColLine(here, beginning);
        int b = solve(here+1, count+1, beginning, target);
        swapColLine(here, beginning);
        
        return Math.min(a, b);
    }
    
    public void swapColLine(int j, int[][] beginning){
        for(int i = 0; i < N; i++){
            beginning[i][j] = 1 - beginning[i][j];
        }
    }
    
    public int checkRowLines(int[][] beginning, int[][] target){
        int ret = 0;
        for(int i = 0; i < N; i++){
            int num = checkRowLine(beginning[i], target[i]);
            if(num == -1) return INF;
            ret += num;
        }
        return ret;
    }
    
    public int checkRowLine(int[] A, int[] B){
        int oppositeCnt = 0;
        int cnt = 0;
        
        for(int i = 0; i < A.length; i++){
            if(A[i] == B[i]) cnt++;
            else if(1 - A[i] == B[i]) oppositeCnt++;
        }
        
        if(cnt == M) return 0;
        if(oppositeCnt == M) return 1;
        return -1;
    }
}