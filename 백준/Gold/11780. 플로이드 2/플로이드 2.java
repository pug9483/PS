import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, M;
    static int[][] dist;
    static int[][] next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1][N + 1];
        next = new int[N + 1][N + 1];

        // 거리 배열 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 경로 배열 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(next[i], 0);
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (dist[u][v] > w) {
                dist[u][v] = w;
                next[u][v] = v;
            }
        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (dist[i][k] == INF) continue; // 가지치기
                for (int j = 1; j <= N; j++) {
                    if (dist[k][j] == INF) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // 최소 비용 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF) {
                    sb.append(0).append(' ');
                } else {
                    sb.append(dist[i][j]).append(' ');
                }
            }
            sb.append('\n');
        }

        // 경로 출력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF || i == j) {
                    sb.append(0).append('\n');
                } else {
                    List<Integer> path = getPath(i, j);
                    sb.append(path.size()).append(' ');
                    for (int city : path) {
                        sb.append(city).append(' ');
                    }
                    sb.append('\n');
                }
            }
        }

        // 결과 출력
        System.out.print(sb);
    }

    // 경로 복원 함수
    static List<Integer> getPath(int u, int v) {
        List<Integer> path = new ArrayList<>();
        path.add(u);
        while (u != v) {
            u = next[u][v];
            path.add(u);
        }
        return path;
    }
}
