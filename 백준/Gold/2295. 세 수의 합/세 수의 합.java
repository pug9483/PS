import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static List<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine());
        System.out.println(solve());
    }
    
    // x+y+z = k -> x+y == k-z로 두 수씩 묶어서 풀기
    public static int solve(){
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                list.add(A[i] + A[j]);
        
        Arrays.sort(A);
        Collections.sort(list);
        for(int r = N-1; r >= 0; r--)
            for(int l = r; l >= 0; l--)
                if(Collections.binarySearch(list, A[r] - A[l]) > -1)
                    return A[r];
        return -1;
    }
}
