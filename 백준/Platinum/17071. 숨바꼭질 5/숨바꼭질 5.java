import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    private static int n, k;
    private static int[][] dist = new int[2][500001];
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
 
        Arrays.fill(dist[0], -1);
        Arrays.fill(dist[1], -1);
 
        dist[0][n] = 0;
 
        System.out.println((n == k) ? 0 : solve());
    }
 
    private static int solve() {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
 
        int len, mod, time = 0;
        while (!q.isEmpty()) {
            len = q.size();
            time++;
            mod = time % 2; // 홀수, 짝수 판단
 
            for (int i = 0; i < len; i++) {
                int here = q.poll();
                if (here - 1 >= 0 && dist[mod][here - 1] == -1) {
                    q.add(here - 1);
                    dist[mod][here - 1] = time;
                }
                if (here + 1 <= 500000 && dist[mod][here + 1] == -1) {
                    q.add(here + 1);
                    dist[mod][here + 1] = time;
                }
                if (here * 2 <= 500000 && dist[mod][here * 2] == -1) {
                    q.add(here * 2);
                    dist[mod][here * 2] = time;
                }
            }
 
            int other = k + (time * (time+1) / 2); 
            if (other > 500000) break; 
            if (dist[mod][other] != -1) return time;
        }
 
        return -1;
    }
}