/*
2초에 15위치에 도착했다는 것은 4,6,8, ... 은 모두 15에 갈 수 있다.
=>  수빈이가 한 위치에 도착했다면 2초마다 같은 위치로 이동할 수 있다.
1. 홀수 시간에 어떤 칸에 도착했고, 동생이 홀수 시간만에 그 위치로 왔다면 찾을 수 있다.
2. 짝수 시간에 어떤 칸에 도착했고, 동생이 짝수 시간만에 그 위치로 왔다면 찾을 수 있다.

각각의 칸에 도달할 수 있는 가장 빠른 시간을 홀수 일 때와 짝수 일 때로 나누어서 저장해야 한다.
dist[i][v] : 위치 v에 수빈이가 도착하는 가장 빠른 시간(i = 0 짝수 시간, i = 1 홀수 시간)
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int pos;
        int time;

        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
    private static int N, K;
    private static int[][] dist = new int[2][500001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(solve());
    }

    private static int solve() {
        for (int i = 0; i < 2; i++) Arrays.fill(dist[i], -1);
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N, 0));
        dist[0][N] = 0;
        while (!q.isEmpty()) {
            Node here = q.poll();
            int pos = here.pos;
            int time = here.time;
            for(int next: new int[]{pos+1, pos-1, 2*pos}){
                if(0 <= next && next <= 500000){
                    if(dist[1-time][next] == -1){
                        dist[1-time][next] = dist[time][pos] + 1;
                        q.add(new Node(next, 1-time));
                    }
                }
            }
        }
        int ret = -1;
        int t = 0;
        while((K += t) <= 500000){
            if(dist[t%2][K] <= t){
                ret = t;
                break;
            }
            t++;
        }
        return ret;
    }
}
