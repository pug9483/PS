class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        
        if (n == 1) return sticker[0];
        if (n == 2) return Math.max(sticker[0], sticker[1]);

        int maxWithFirst = calculateMaxSum(sticker, 0, n - 2);        
        int maxWithoutFirst = calculateMaxSum(sticker, 1, n - 1);

        return Math.max(maxWithFirst, maxWithoutFirst);
    }

    private int calculateMaxSum(int[] sticker, int start, int end) {
        int prev2 = 0;             
        int prev1 = sticker[start]; 

        for (int i = start + 1; i <= end; i++) {
            int current = Math.max(prev1, prev2 + sticker[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}