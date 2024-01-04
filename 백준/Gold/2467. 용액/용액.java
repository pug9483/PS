import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        
        int left = 0, right = N-1;
        int ret = getAbsoluteAB(left, right);
        int l = A[left], r = A[N-1];
        while(true){
            if(getAbsoluteAB(left, right) < ret){
                l = A[left];
                r = A[right];
                ret = getAbsoluteAB(left, right);
            }
            if(left + 1 == right) break;
            int a = getAbsoluteAB(left+1, right);
            int b = getAbsoluteAB(left, right-1);
            if(a < b) left++;
            else right--;
        }
        System.out.println(l + " " + r);
    }
    
    public static int getAbsoluteAB(int i, int j){
        return (int)(Math.abs(A[i] + A[j]));
    }
}