import java.io.*;
import java.util.*;

public class Main {  
    static class Line{
        int src, dst;
        public Line(int src, int dst){
            this.src = src;
            this.dst = dst;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Line> lines = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            lines.add(new Line(src, dst));
        }
        lines.sort((o1, o2) -> Integer.compare(o1.src, o2.src));

        int end = Integer.MIN_VALUE;
        int ret = 0;
        
        for(Line line: lines){
            int s = Math.max(line.src, end);
            int e = line.dst;
            if(end >= e) continue;
            ret += e-s;
            end = e;
        }
        System.out.println(ret);
    }
}