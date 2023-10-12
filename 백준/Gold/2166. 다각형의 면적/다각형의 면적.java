import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    static class Point{
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Point> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x, y));
        }    
        list.add(new Point(list.get(0).x, list.get(0).y));
        System.out.printf("%.1f\n", solve());
    }   
    
    public static double solve(){
        double ret = 0.0;
        for(int i = 0; i < list.size() - 1; i++)
            ret += 1.0 * list.get(i).x * list.get(i+1).y;
        for(int i = 0; i < list.size() - 1; i++)
            ret -= 1.0 * list.get(i).y * list.get(i+1).x;
        return Math.abs(ret) / 2.0;
    }
}