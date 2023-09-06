import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Duration implements Comparable<Duration>{
        int start;
        int end;
        public Duration(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Duration o){
            int comp = Integer.compare(start, o.start);
            return comp == 0? Integer.compare(end, o.end) : comp;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static List<Duration> durations = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            durations.add(new Duration(sm*100 + sd, em*100 + ed));
        }
        Collections.sort(durations);

        int idx = 0;
        int start = 301, end = 0;
        int ret = 0;
        while(start < 1201){
            boolean ok = false;
            for(Duration d: durations){
                if(d.start > start) break;
                if(d.start <= start && end < d.end){
                    end = d.end;
                    ok = true;
                }
            }
            if(ok){                
                start = end;
                ret++;
            }
            else break;
        }
        System.out.println(end < 1201 ? 0 : ret);
    }
}