import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int MAX = 1000001;
    public static boolean[] check = new boolean[MAX];
    public static List<Integer> primes = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        init();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            int ret = 0;
            for(int i = 0; i < primes.size(); i++){
                int a = primes.get(i);
                int b = N - a;
                if(a > b) break;
                if(!check[b]) ret++;
            }
            sb.append(ret).append("\n");
        }
        System.out.print(sb);
    }
    
    public static void init(){
        check[0] = check[1] = true;
        for(int i = 2; i < MAX; i++){
            if(!check[i]){
                primes.add(i);
                for(int j = 2 * i; j < MAX; j += i)
                    check[j] = true;
            }
        }
    }
}