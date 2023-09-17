import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static List<Integer> primes = new ArrayList<>();
    public static final int MAX = 1000001;
    public static boolean[] check = new boolean[MAX];
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        for(int i = 2; i < MAX; i++){
            if(!check[i]){
                primes.add(i);
                for(int j = 2*i; j < MAX; j += i)
                    check[j] = true;
            }
        }
        for(int prime: primes){
            if(prime < M) continue;
            if(prime > N) break;
            System.out.println(prime);
        }
    }
} 