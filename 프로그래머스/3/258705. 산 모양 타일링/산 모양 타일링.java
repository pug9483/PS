class Solution {
    int MOD = 10007;
    public int solution(int n, int[] tops) {        
        int[] A = new int[n+1];
        int[] B = new int[n+1];
        
        A[1] = 1;
        if(tops[0] == 1) B[1] = 3;
        else B[1] = 2;
        
        for(int i = 2; i <= n; i++){
            A[i] = (A[i-1] + B[i-1]) % MOD;
            if(tops[i-1] == 1) B[i] = (A[i-1] * 2 + B[i-1]*3) % MOD;
            else B[i] = (A[i-1] + B[i-1] * 2) % MOD;
        }
        return (A[n] + B[n]) % MOD;
    }
}