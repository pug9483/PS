import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int N = 20;
    public static int[] A = new int[N];
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        for(int p = 1; p <= P; p++){
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            for(int i = 0; i < N; i++)
                A[i] = Integer.parseInt(st.nextToken());            
            System.out.printf("%d %d\n", T, solve());
        }
    }
    
    public static int solve(){
        int ret = 0;
        List<Integer> list = new ArrayList<>();
        list.add(A[0]);
        for(int j = 1; j < N; j++){
            int i = list.size() - 1;
            while(i >= 0 && list.get(i) > A[j]) i--;
            i++;
            ret += list.size() - i;
            list.add(i, A[j]);
        }
        return ret;
    }
}