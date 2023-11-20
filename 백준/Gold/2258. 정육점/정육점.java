import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    static class Meat implements Comparable<Meat> {
        int weight;
        int price;

        public Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(final Meat o) {
            int cmp = Integer.compare(price, o.price);
            return  cmp == 0? Integer.compare(o.weight, weight) : cmp; 
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static List<Meat> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int sum = 0;
        
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list.add(new Meat(weight, price));
            sum += weight;
        }
        System.out.println(sum < M ? -1 : solve());
    }
    
    public static int solve(){
        Collections.sort(list);
        int ret = Integer.MAX_VALUE;
        int prevPrice = 0;
        int pSum = 0;
        int wSum = 0;
        
        for(Meat meat: list){
            wSum += meat.weight;    
            if(prevPrice == meat.price) pSum += meat.price;
            else pSum = prevPrice = meat.price;
            
            if(wSum >= M)
                ret = Math.min(ret, pSum);
        }
        return ret;
    }
}