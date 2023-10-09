import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static long N;
    public static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        long[][] matrix2D = new long[][]{
            {1L, 1L},
            {1L, 0L}
        };
        matrix2D = pow(matrix2D, N-1);
        System.out.println((matrix2D[0][0]) % MOD);
    }   
    
    public static long[][] mul(long[][] A, long[][] B){
        long[][] C = new long[2][2];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
    
    public static long[][] pow(long[][] A, long k){
        if(k <= 1) return A;
        if(k % 2 == 0){
            long[][] B = pow(A, k / 2);
            return mul(B, B);
        }
        long[][] B = pow(A, k-1);
        return mul(A, B);
    }
}