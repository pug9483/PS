import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N; // 최대 수
    public static int K; // 지수
    public static int P; // 바꿀 개수
    public static int X; // 시작 수
    public static int[] A;
    
    public static final int[][] trans = new int[][]{
        {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
        {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
        {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
        {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
        {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
        {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
        {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
        {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
        {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
        {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new int[K];
        int tmpNum = X;
        for(int i = K-1; i >= 0; i--){
            A[i] = tmpNum % 10;
            tmpNum /= 10;
        }
        System.out.println(go(0, 0, 0));
    }
    
    public static int go(int here, int cnt, int sum){
        if(cnt > P) return 0;
        if(here == K){
            if(cnt == 0) return 0;
            return (sum <= N && sum >= 1)? 1 : 0;
        }
        int ret = 0;
        
        for(int num = 0; num <= 9; num++){
            int change = trans[A[here]][num];
            ret += go(here+1, cnt + change, sum * 10 + num);
        }
        return ret;
    }
}