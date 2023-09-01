/*
dist[i] : i까지의 거리
cnt[i] = i까지 가는 방법의 수 기록

어떤 정점에 도달하는 경로의 거리가 같을 경우,
방법의 개수 더해주기(큐에 정점을 새로 추가하지는 않는다)
if(dist[there] != -1 && dist[there] == dist[here] + 1){
  cnt[there] += cnt[here];
}
*/

now -> next일 때, next에 이미 방문하고 dist[now] + 1 == dist[next]이면
cnt[next] += cnt[now];
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, K;
    public static int[] dist;
    public static int[] cnt;
    
    public static boolean check(int num){
        return num < 0 || num > 100000;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        cnt = new int[100001];
        Arrays.fill(dist, -1);
        Arrays.fill(cnt, -1);
        bfs();
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        dist[N] = 0;
        cnt[N] = 1;
        while(!q.isEmpty()){
            int here = q.poll();
            for(int there: new int[]{here-1, here+1, here*2}){
                if(check(there)) continue;
                if(dist[there] == -1){
                    q.add(there);
                    dist[there] = dist[here] + 1;
                    cnt[there] = cnt[here];
                }
                else if(dist[there] == dist[here] + 1){
                    cnt[there] += cnt[here];
                }
            }
        }
        System.out.println(dist[K]);
        System.out.println(cnt[K]);
    }
}
