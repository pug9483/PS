class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int gcdA = getGcd(arrayA);
        int gcdB = getGcd(arrayB);
        
        int num1 = isTrue(gcdA, arrayB) ? gcdA : 0;
        int num2 = isTrue(gcdB, arrayA) ? gcdB : 0;
        
        return Math.max(num1, num2);
    }
    
    public boolean isTrue(int gcdNum, int[] A){
        for(int i = 0; i < A.length; i++){
            if(A[i] % gcdNum == 0){
                return false;
            }
        }
        return true;
    }
    
    public int getGcd(int[] A){
        int ret = A[0];
        for(int i = 0; i < A.length; i++){
            ret = gcd(ret, A[i]);
        }
        return ret;
    }
    
    public int gcd(int a, int b){
        return b == 0? a : gcd(b, a%b);
    }
}