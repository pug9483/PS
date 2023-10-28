import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.Math;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;    
    private static int S;    
    private static int M;    
    private static final int INF = 987654321;
    private static Integer[] arr = new Integer[3];
    private static int[][][][] cache = new int[60][61][61][61];
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        Arrays.fill(arr, 0);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ret = solve(0, arr[0], arr[1], arr[2]);
        System.out.println(ret == -INF ? - 1 : ret);
    }
    
    private static int solve(int count, int a1, int a2, int a3){
        if(a1 == 0 && a2 == 0 && a3 == 0) return count;
        int ret = INF;
        
        
        if(cache[count][a1][a2][a3] != 0) return cache[count][a1][a2][a3];
        
        ret = Math.min(ret, solve(count+1, Math.max(a1-9, 0), Math.max(a2-3, 0), Math.max(a3-1, 0)));
        ret = Math.min(ret, solve(count+1, Math.max(a1-9, 0), Math.max(a3-3, 0), Math.max(a2-1, 0)));
        ret = Math.min(ret, solve(count+1, Math.max(a2-9, 0), Math.max(a3-3, 0), Math.max(a1-1, 0)));
        ret = Math.min(ret, solve(count+1, Math.max(a2-9, 0), Math.max(a1-3, 0), Math.max(a3-1, 0)));
        ret = Math.min(ret, solve(count+1, Math.max(a3-9, 0), Math.max(a1-3, 0), Math.max(a2-1, 0)));
        ret = Math.min(ret, solve(count+1, Math.max(a3-9, 0), Math.max(a2-3, 0), Math.max(a1-1, 0)));
        
        cache[count][a1][a2][a3] = ret;
        return ret;
    }
}
