import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[] A;
    public static int[] pSum;
    public static Map<Integer, Long> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        pSum = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        partialSum();
        System.out.println(solve());
    }
 
    public static void partialSum(){
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum = (sum + A[i]) % M;
            pSum[i] = sum;
        }
    }
    
    public static long solve(){
        long ret = 0;
        for(int i = 0; i < N; i++){
            if(pSum[i] % M == 0) ret++;
            if(map.containsKey(pSum[i])) ret += map.get(pSum[i]);
            if(!map.containsKey(pSum[i])) map.put(pSum[i], 1L);
            else map.put(pSum[i], map.get(pSum[i]) + 1);
        }
        return ret;
    }
}