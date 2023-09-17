import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int MAX = 1000001;
    public static boolean[] check = new boolean[MAX];
    public static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        for(int i = 2; i < MAX; i++){
            if(!check[i]){
                for(int j = 2*i; j < MAX; j += i)
                    check[j] = true;
            }
        }
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            boolean ok = false;
            for(int i = 2; i <= N/2; i++){
                if(!check[i] && !check[N-i]){
                    sb.append(String.valueOf(N) + " = " + String.valueOf(i) + " + " + String.valueOf(N-i) + "\n");
                    ok = true;
                    break;
                }
            }
            if(!ok) sb.append("Goldbach's conjecture is wrong.\n");
        }
        System.out.print(sb);
    }
} 