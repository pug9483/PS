import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static class Point {
        public long x, y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Line {
        public Point p1;
        public Point p2;
        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());
        Line l1 = new Line(new Point(x1, y1), new Point(x2, y2));
        Line l2 = new Line(new Point(x3, y3), new Point(x4, y4));
        System.out.println(cross(l1, l2));
    }
    
    public static int compare(Point p1, Point p2){
        if (p1.x < p2.x) return -1;
        else if (p1.x == p2.x) {
            if (p1.y < p2.y) return -1;
            if (p1.y > p2.y) return 1;
            return 0;
        } 
        return 1;
    }
    
    public static int ccw(Point p1, Point p2, Point p3) {
        long temp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        temp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (temp > 0) return 1;
        if (temp < 0) return -1;
        return 0;
    }
    
    public static int cross(Line l1, Line l2) {
        int r1 = ccw(l1.p1, l1.p2, l2.p1) * ccw(l1.p1, l1.p2, l2.p2);
        int r2 = ccw(l2.p1, l2.p2, l1.p1) * ccw(l2.p1, l2.p2, l1.p2);
        if (r1 <= 0 && r2 <= 0 && !(r1 == 0 && r2 == 0)) return 1;
        if (r1 == 0 && r2 == 0) {
            if (compare(l1.p1, l1.p2) == 1) {
                Point temp = l1.p1;
                l1.p1 = l1.p2;
                l1.p2 = temp;
            }
            if (compare(l2.p1, l2.p2) == 1) {
                Point temp = l2.p1;
                l2.p1 = l2.p2;
                l2.p2 = temp;
            }
            return (compare(l2.p1, l1.p2) != 1 && compare(l1.p1, l2.p2) != 1) ? 1 : 0;
        }
        return 0;
    }
}
