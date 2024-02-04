import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static char[] A;
    public static int[][] dpMax;
    public static int[][] dpMin;
    
	public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        A = new char[N];
        A = br.readLine().toCharArray();
        dpMax = new int[N][N];
        dpMin = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dpMax[i], Integer.MIN_VALUE);
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i < N; i += 2){
            for(int j = i; j < N; j += 2){
                solve(i, j);
            }
        }
        solve(0, N-1);
        // System.out.printf("%d, %d\n", dpMax[0][4], dpMax[6][8]);
        System.out.println(dpMax[0][N-1]);
    }
    public static void solve(int start, int end){
        if(start == end){
            dpMin[start][end] = dpMax[start][end] = A[start] - '0';
            return;
        } 
        
        if(dpMax[start][end] != Integer.MIN_VALUE) return;
        
        // [start, end]에서의 최댓값, 최솟값을 구한다.
        for(int k = start; k <= end - 2; k += 2){
            solve(start, k);
            solve(k + 2, end);
            char op = A[k+1];
            int[] sums = new int[4];
            sums[0] = calculate(dpMax[start][k], op, dpMax[k+2][end]);
            sums[1] = calculate(dpMax[start][k], op, dpMin[k+2][end]);
            sums[2] = calculate(dpMin[start][k], op, dpMax[k+2][end]);
            sums[3] = calculate(dpMin[start][k], op, dpMin[k+2][end]);
            dpMax[start][end] = Math.max(dpMax[start][end], getMax(sums));
            dpMin[start][end] = Math.min(dpMin[start][end], getMin(sums));
        }
    }
    
    public static int calculate(int left, char op, int right){
        if(op == '+') return left + right;
        if(op == '-') return left - right;
        return left * right;
    }
                            
    public static int getMax(int[] sums){
        int ret = Integer.MIN_VALUE;
        for(int i = 0; i < sums.length; i++)
            ret = Math.max(ret, sums[i]);
        return ret;
    }
     
    public static int getMin(int[] sums){
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < sums.length; i++)
            ret = Math.min(ret, sums[i]);
        return ret;
    }
}