import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    static class House{
        long pos, val;
        public House(long pos, long val){
            this.pos = pos;
            this.val = val;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<House> houses = new ArrayList<>();
    public static long sum = 0;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long pos = Integer.parseInt(st.nextToken());
            long val = Integer.parseInt(st.nextToken());
            houses.add(new House(pos, val));
            sum += val;
        }
        Collections.sort(houses, (a, b) -> Long.compare(a.pos, b.pos));	//마을 위치 기준 오름차순 정렬
        long mid = 0;
        for(int i=0; i < N; i++){
            mid += houses.get(i).val;
            if((sum + 1)/2 <= mid){	//(sum+1)/2 : 중간값
                System.out.println(houses.get(i).pos);
                break;
            }
        }
    }
}