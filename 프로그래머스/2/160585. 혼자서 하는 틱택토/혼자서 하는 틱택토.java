class Solution {
    int N = 3;
    
    public int solution(String[] board) {
        boolean a = checkCount(board);
        boolean b = checkWin(board);
        
        return a && b ? 1 : 0;
    }
    
    public boolean checkCount(String[] board){
        int oCount = 0;
        int xCount = 0;
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                char c = board[i].charAt(j);
                if(c == 'O') oCount++;
                else if(c == 'X') xCount++;
            }
        }
        
        return oCount == xCount || oCount == xCount + 1;
    }
    
    public boolean checkWin(String[] board){
        int totalOCount = 0;
        int totalXCount = 0;
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                char c = board[y].charAt(x);
                if(c == 'O') totalOCount++;
                else if(c == 'X') totalXCount++;
            }
        }
        
        boolean oWin = false;
        boolean xWin = false;
        
        // 가로
        for(int y = 0; y < N; y++){
            int oCount = 0;
            int xCount = 0;
            for(int x = 0; x < N; x++){
                char c = board[y].charAt(x);
                if(c == 'O') oCount++;
                else if(c == 'X') xCount++;
            }
            if(oCount == N) oWin = true;
            else if(xCount == N) xWin = true;
        }
        
        // 세로
        for(int x = 0; x < N; x++){
            int oCount = 0;
            int xCount = 0;
            for(int y = 0; y < N; y++){
                char c = board[y].charAt(x);
                if(c == 'O') oCount++;
                else if(c == 'X') xCount++;
            }
            if(oCount == N) oWin = true;
            else if(xCount == N) xWin = true;
        }
        
        // 대각선
        int oCount = 0;
        int xCount = 0;
         for(int y = 0; y < N; y++){    
            char c = board[y].charAt(y);
            if(c == 'O') oCount++;
            else if(c == 'X') xCount++;
        }
        if(oCount == N) oWin = true;
        else if(xCount == N) xWin = true;
        
        
        oCount = 0;
        xCount = 0;
        for(int y = 0; y < N; y++){    
            char c = board[y].charAt(N-1-y);
            if(c == 'O') oCount++;
            else if(c == 'X') xCount++;
        }
        if(oCount == N) oWin = true;
        else if(xCount == N) xWin = true;
        
        if(oWin && totalXCount + 1 != totalOCount) return false;
        if(xWin && totalXCount != totalOCount) return false;
        return true;
    }
}