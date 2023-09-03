import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[][] A;
    public static int[][] pSum;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        pSum = new int[N+1][N+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        partialSum();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int ret = pSum[y2][x2] - pSum[y2][x1-1] - pSum[y1-1][x2] + pSum[y1-1][x1-1];
            sb.append(ret).append("\n");
        }
        System.out.print(sb);
    }
 
    public static void partialSum(){
        int sum = 0;
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                pSum[i][j] = A[i-1][j-1] + pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1]; 
    }
}