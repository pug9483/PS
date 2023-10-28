import java.io.*;
import java.util.*;

public class Main {           
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static boolean[][][][][] memo;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        int a = 0, b = 0, c = 0;
        
        String s = br.readLine();
        N = s.length();
        A = new int[N];
        memo = new boolean[N+1][N+1][N+1][4][4];
        
        for(int i = 0; i < N; i++){
            if(s.charAt(i) == 'A') a++;
            else if(s.charAt(i) == 'B') b++;
            else c++;
        }
        boolean ok = solve(0, a, b, c, -1, -1);
        if(ok)
            for(int i = 0; i < N; i++){
                if(A[i] == 0) sb.append('A');
                else if(A[i] == 1) sb.append('B');
                else if(A[i] == 2) sb.append('C');
            }
        else sb.append(-1);
        System.out.println(sb);
    }
    
    public static boolean solve(int here, int a, int b, int c, int ll, int l){
        if(a < 0 || b < 0 || c < 0) return false;
        if(here == N){
            if(a == 0 && b == 0 && c == 0) return true;
            return false;
        }
        
        if(memo[here][a][b][ll+1][l+1]) return false;        
        memo[here][a][b][ll+1][l+1] = true;
        
        A[here] = 0;
        if(solve(here+1, a-1, b, c, l, 0)) return true;
        
        if(l != 1){
            A[here] = 1;    
            if(solve(here+1, a, b-1, c, l, 1)) return true;
        }
        if(l != 2 && ll != 2){
            A[here] = 2;    
            if(solve(here+1, a, b, c-1, l, 2)) return true;
        }
        return false;
    }
}