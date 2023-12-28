import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {   
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int M;
    public static String[] A;
    public static int[] B;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new String[N];
        B = new int[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            A[i] = name;
            B[i] = score;
        }
        for(int i = 0; i < M; i++){
            int num = Integer.parseInt(br.readLine());
            sb.append(solve(num)).append("\n");
        }
        System.out.print(sb);
    }
    
    public static String solve(int num){
        int left = 0, right = N-1;
        int index = N-1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(B[mid] >= num){
                right = mid-1;
                index = mid;
            }
            else left = mid+1;
        }
        return A[index]; 
    }
}