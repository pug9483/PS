import java.io.*;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[][] A; // 위치
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][2];
        
        int maxIndex = 0;
        int maxHeight = 0;
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        for(int i = 0; i < N; i++){
            if(maxHeight < A[i][1]){
                maxIndex = i;
                maxHeight = A[i][1];
            }
        }
        System.out.println(solve(maxIndex, maxHeight));
    }
    
    public static int solve(int maxIndex, int maxHeight){
        int pos = A[0][0];
        int height = A[0][1];
        int ret = 0;
        
        for(int i = 1; i <= maxIndex; i++){
            int width = A[i][0] - pos; // [pos, A[i][0])
            ret += width * height;
            pos = A[i][0];
            height = Math.max(height, A[i][1]);
        }
        
        pos = A[N-1][0];
        height = A[N-1][1];
        for(int i = N-2; i >= maxIndex; i--){
            int width = pos - A[i][0]; // (A[i][0], pos]
            ret += width * height;
            pos = A[i][0];
            height = Math.max(height, A[i][1]);
        }
        
        ret += maxHeight;
        return ret;
    }
}