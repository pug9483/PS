/*
문제1. 방법을 출력하지 않는 숫자 맞추기

조건1. 왼쪽으로 돌리면 아래에 있는 나사가 같이 돈다.
조건2. 오른쪽으로 돌리면 하나만 돌아간다.

D[i][j] = i번 나사가 현재 왼쪽으로 j번 돌은 상황일 때 최소 횟수

1) 왼쪽으로 돌리는 경우
int costL = (b[index] - cur + 10) % 10;
// 아래쪽 나사가 돌아가야 하는 수를 두번째 매개변수에 저장
int left = go(index+1, (turn + costL) % 10) + costL; 

2) 오른쪽으로 돌리는 경우
int costR = (cur - b[index] + 10) % 10;
int right = go(index+1, turn) = costR;
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String src;
    public static String dst;
    public static int[][] dp;
    public static Map<String, Integer> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        src = br.readLine();
        dst = br.readLine();
        dp = new int[N][10];
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        System.out.println(solve(0, 0));
    }
    
    public static int solve(int here, int turn){
        if(here == N) return 0;
        if(dp[here][turn] != -1) return dp[here][turn];
        int from = src.charAt(here) - '0';
        from = (from + turn) % 10;
        int to = dst.charAt(here) - '0';
        int left = (to - from + 10) % 10;
        int ret = left + solve(here+1, (turn + left) % 10);
        int right = (from - to + 10) % 10;
        ret = Math.min(ret, right + solve(here+1, turn));
        return dp[here][turn] = ret;
    }
}
