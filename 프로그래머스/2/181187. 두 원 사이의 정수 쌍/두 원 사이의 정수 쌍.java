class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(long x = 1; x <= r2; x++){
            int y2 = (int)Math.floor(Math.sqrt((long)r2*r2 - x*x));
            int y1 = (int)Math.ceil(Math.sqrt((long)r1*r1 - x*x));
            answer += (y2 - y1 + 1);
        }
        return answer * 4;
    }
}