import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main { 
    static class Line{
        public int x1;
        public int y1;
        public int x2;
        public int y2;
        public Line(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Line[] lines = new Line[2];
    
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 2; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            lines[i] = new Line(x1, y1, x2, y2);
        }    
        System.out.println(intersectionOfTwoLine(lines[0], lines[1]));
    }   
    
    // CCW(p1, p2, p3) * CCW(p1, p2, p4) < 0
    // CCW(p3, p4, p1) * CCW(p3, p4, p2) < 0
    public static int intersectionOfTwoLine(Line l1, Line l2){
        int r1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1) * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
        int r2 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1) * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);
        return (r1 < 0 && r2 < 0) ? 1 : 0;
    }
    
    public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3){
        long ret = 1L*x1*y2 + 1L*x2*y3 + 1L*x3*y1 - (1L*y1*x2 + 1L*y2*x3 + 1L*y3*x1);
        if(ret > 0) return 1;
        else if(ret < 0) return -1;
        return 0;
    }
}