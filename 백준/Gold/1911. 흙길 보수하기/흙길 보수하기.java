import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    static class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int L;
    public static Interval[] intervals;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        intervals = new Interval[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            intervals[i] = new Interval(s, e);
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        int ret = 0;
        int pos = 0;
        for(int i = 0; i < N; i++){
            int start = intervals[i].start;
            int end = intervals[i].end;
            if(pos >= end) continue;
            int plus = 0;
            if(pos < start){
                plus = (end - start) % L == 0 ? (end - start) / L : (end - start) / L + 1;
                pos = start + plus * L;
            }
            else{
                plus = (end - pos) % L == 0 ? (end - pos) / L : (end - pos) / L + 1;
                pos = pos + plus * L;
            }
            ret += plus;
        }
        return ret;
    }
}