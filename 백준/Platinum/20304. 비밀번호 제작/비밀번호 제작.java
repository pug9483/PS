import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static int[] A;
    public static final int MAX = 20;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++)
            A[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }
    
    public static int solve(){
        int ret = 0;
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);
        for(int i = 0; i < M; i++){
            dist[A[i]] = 0;
            q.add(A[i]);
        }
        while(!q.isEmpty()){
            int here = q.poll();
            ret = Math.max(ret, dist[here]);
            for(int i = 0; i < MAX; i++){
                int there = (1 << i) ^ here;
                if(there > N || dist[there] != -1) continue;
                dist[there] = dist[here] + 1;
                q.add(there);
            }
        }
        return ret;
    }
}