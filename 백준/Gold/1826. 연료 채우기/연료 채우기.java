import java.io.*;
import java.util.*;

public class Main {
    static class Station{
        int dist;
        int weight;

        public Station(int dist, int weight) {
            this.dist = dist;
            this.weight = weight;
        }
    }

    public static int N;
    public static Station[] A;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new Station[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            A[i] = new Station(dist, weight);
        }
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        System.out.println(solve(L, P));
    }

    public static int solve(int L, int P) {
        int ret = 0;

        PriorityQueue<Station> info = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        info.addAll(List.of(A));

        while (P < L) {
            while (!info.isEmpty() && P >= info.peek().dist) {
                Station station = info.poll();
                pq.add(station.weight);
            }
            if(pq.isEmpty()) return -1;
            P += pq.poll();
            ret++;
        }

        return ret;
    }
}
