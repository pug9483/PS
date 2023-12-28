import java.io.*;
import java.util.*;

public class Main {   
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String S;
    public static char[] A;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        S = br.readLine();
        N = S.length();
        A = S.toCharArray();
        visited = new boolean[N];
        int one = 0;
        int zero = 0;
        
        for(int i = 0; i < N; i++){
            if(A[i] == '0') zero++;
            else one++;
        }
        one /= 2;
        zero /= 2;
        for(int i = 0; i < N; i++){
            if(one == 0) break;
            if(A[i] == '1'){
                one--;
                visited[i] = true;
            }
        }
        for(int i = N-1; i >= 0; i--){
            if(zero == 0) break;
            if(A[i] == '0'){
                zero--;
                visited[i] = true;
            }
        }
        for(int i = 0; i < N; i++)
            if(!visited[i]) sb.append(A[i]);
        System.out.println(sb);
    }
}