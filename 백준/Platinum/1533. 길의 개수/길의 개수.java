import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N, S, E, T; 
    public static long[][] ret;
    public static long MOD = 1_000_003;
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()) - 1;
        E = Integer.parseInt(st.nextToken()) - 1;
        T = Integer.parseInt(st.nextToken());
        
        ret = new long[N*5][N*5];
        for(int i = 0; i < N*5; i++)
            ret[i][i] = 1;
        
        long[][] A = new long[N*5][N*5];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 4; j++){
                A[5*i + j][5*i + j+1] = 1;
            }
        }
        
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                int v = s.charAt(j) - '0';
                if(v > 0) A[5*i + v-1][5*j] = 1;
            }
        }
        
        while(T > 0){
            if((T & 1) == 1) ret = mul(ret, A);
            A = mul(A, A);
            T >>= 1;
        }
        System.out.println(ret[5*S][5*E]);
    }
    
    public static long[][] mul(long[][] A, long[][] B){
        int size = A.length;
        long[][] C = new long[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                for(int k = 0; k < size; k++){
                    C[i][j] += A[i][k] * B[k][j];
                }
                C[i][j] %= MOD;
            }
        }
        return C;
    }
}