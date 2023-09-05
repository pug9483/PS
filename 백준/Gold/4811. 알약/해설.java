/*
한 조각의 개수: F, 반 조각의 개수: H

한 조각 먹는 경우: (F,H) -> (F-1, H+1)
반 조각 먹는 경우: (F,H) -> (F, H-1)

D[F][H]  = 약통에 약이 F개, 반 조각이 H개 있을 때 약을 먹는 방법의 수
         = D[f-1][H+1] + D[F][H-1] (D[0][H] = D[0][H-1] = 1, D[0][0]= 1)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {      
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static long[][] memo;
    
    public static void main(String[] args) throws IOException {
        String s = "";
        while(!(s = br.readLine()).equals("0")){
            N = Integer.parseInt(s);
            memo = new long[N+1][N+1];
            for(int i = 0; i < N+1; i++) Arrays.fill(memo[i], -1);
            sb.append(solve(N, 0)).append("\n");
        }
        System.out.print(sb);
    }
    
    public static long solve(int one, int half){
        if(memo[one][half] != -1) return memo[one][half];
        
        if(one == 0) return 1;
        if(half == 0) return memo[one][half] = solve(one-1, half+1);;
        
        long ret = solve(one-1, half+1) + solve(one, half-1);
        return memo[one][half] = ret;
    }
}
