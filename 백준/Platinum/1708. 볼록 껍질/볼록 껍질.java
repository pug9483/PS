import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static class Point {
        public long x, y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Point> points = new ArrayList<>();
    public static Point p1;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points.add(new Point(x, y));
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        // 가장 왼쪽 아래에 있는 점 찾기
        p1 = points.get(0);
        for(int i = 1; i < N; i++){
            if(points.get(i).y < p1.y || (points.get(i).y == p1.y && points.get(i).x < p1.x)){
                p1 = points.get(i);
            }
        }
        Collections.sort(points, (u, v) -> {
            int cmp = ccw(p1, u, v);                
            return cmp == 0? Long.compare(dist(p1, u), dist(p1, v)) : -cmp;
        });
        List<Point> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            while(list.size() >= 2 && ccw(list.get(list.size()-2), list.get(list.size()-1), points.get(i)) <= 0)
                list.remove(list.size() - 1);
            list.add(points.get(i));
        }
        return list.size();
    }
    
    public static int ccw(Point p1, Point p2, Point p3) {
        long temp = 1L*p1.x * p2.y + 1L*p2.x * p3.y + 1L*p3.x * p1.y;
        temp -= 1L*p1.y * p2.x + 1L*p2.y * p3.x + 1L*p3.y * p1.x;
        if (temp > 0) return 1;
        if (temp < 0) return -1;
        return 0;
    }
    
    public static long dist(Point p1, Point p2){
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}
