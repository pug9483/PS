import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = "";
        while(!(s = br.readLine()).equals("end")){
            char[][] A = new char[3][3];
            for (int i = 0; i < 9; i++)
                A[i/3][i%3] = s.charAt(i);
            System.out.println(check(A) ? "valid" : "invalid");
        }
    }

    public static boolean check(char[][] A) {
        int xCnt = 0; // 선공
        int oCnt = 0; // 후공
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(A[i][j] == 'X') xCnt++;
                else if(A[i][j] == 'O')oCnt++;
            }
        }

        if(oCnt > xCnt) return false;
        if(xCnt > oCnt + 1) return false;

        boolean xFinished = false;
        boolean oFinished = false;
        //가로
        for (int i = 0; i < 3; i++) {
            int xLineCnt = 0;
            int oLineCnt = 0;
            for (int j = 0; j < 3; j++) {
                if(A[i][j] == 'X') xLineCnt++;
                else if(A[i][j] == 'O') oLineCnt++;
            }
            if(xLineCnt == 3) xFinished = true;
            if(oLineCnt == 3) oFinished = true;
            if(xLineCnt == 3 && xCnt == oCnt) return false;
            if(oLineCnt == 3 && xCnt == oCnt + 1) return false;
        }
        //세로
        for (int j = 0; j < 3; j++) {
            int xLineCnt = 0;
            int oLineCnt = 0;
            for (int i = 0; i < 3; i++) {
                if(A[i][j] == 'X') xLineCnt++;
                else if(A[i][j] == 'O') oLineCnt++;
            }
            if(xLineCnt == 3) xFinished = true;
            if(oLineCnt == 3) oFinished = true;
            if(xLineCnt == 3 && xCnt == oCnt) return false;
            if(oLineCnt == 3 && xCnt == oCnt + 1) return false;
        }
        //대각선
        if(A[0][0] == A[1][1] && A[1][1] == A[2][2]){
            if(A[0][0] == 'X') xFinished = true;
            if(A[0][0] == 'O') oFinished = true;
            if(A[0][0] == 'X' && xCnt == oCnt) return false;
            if(A[0][0] == 'O' && xCnt == oCnt + 1) return false;
        }
        if(A[0][2] == A[1][1] && A[1][1] == A[2][0]){
            if(A[0][2] == 'X') xFinished = true;
            if(A[0][2] == 'O') oFinished = true;
            if(A[0][2] == 'X' && xCnt == oCnt) return false;
            if(A[0][2] == 'O' && xCnt == oCnt + 1) return false;
        }
        return (xFinished || oFinished) || (xCnt + oCnt == 9);
    }
}