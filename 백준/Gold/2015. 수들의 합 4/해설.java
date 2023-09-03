/*
A[i] + ... + A[j] == K인 (i,j)쌍의 개수 = S[j] - S[i-1] == K인 (i,j)쌍의 개수
각각의 j에 대해 i =[1..j-1]에서 위의 조건에 해당하는 i의 개수를 찾으면 된다.

방법1. cnt[k] = S[i] == k인 i의 개수를 저장한다.
방법2. Map 사용
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N, K;
    public static int[] A;
    public static int[] pSum;
    public static Map<Integer, Long> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        pSum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }
    
    public static long solve(){
        long ret = 0;
        for(int i = 1; i <= N; i++)
            pSum[i] = A[i] + pSum[i-1];
        
        map.put(0, 1L);
        for(int i = 1; i <= N; i++){
            ret += map.getOrDefault(pSum[i] - K, 0L);
            if(map.containsKey(pSum[i]))
                map.put(pSum[i], map.get(pSum[i]) + 1);
            else map.put(pSum[i], 1L);
        }
        return ret;
    }
}
