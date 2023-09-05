/*
D[i][j] = (i,j)를 오른쪽 아래로 하는 가장 큰 정사각형의 변의 길이
=> min(D[i-1][j], D[i][j-1], D[i-1][j-1]) + 1
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[][] A;
    public static int[][] memo;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());   
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N+1][M+1];
        memo = new int[N+1][M+1];
        for(int i = 1; i <= N; i++){
            String s = br.readLine();
            for(int j = 1; j <= M; j++)
                A[i][j] = s.charAt(j-1) - '0';
        }
        int ret = 0;
        for(int y = 1; y <= N; y++){
            for(int x = 1; x <= M; x++){
                if(A[y][x] == 1){
                    int minV = Math.min(memo[y-1][x], memo[y][x-1]);
                    memo[y][x] = Math.min(memo[y-1][x-1], minV) + 1;
                    ret = Math.max(ret, memo[y][x]);
                }
            }
        }
        System.out.println(ret * ret);
    }
}
