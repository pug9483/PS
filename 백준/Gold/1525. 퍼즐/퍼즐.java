import java.io.*;
import java.util.*;

public class Main {
    public static String target = "123456780"; // 목표 상태를 문자열로 표현
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }
        System.out.println(solve(sb.toString()));
    }

    private static int solve(String start) {
        // BFS를 위한 큐와 방문 여부 체크를 위한 맵 생성
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();

        // 초기 상태 설정
        q.add(start);
        map.put(start, 0);

        // BFS 시작
        while (!q.isEmpty()) {
            String here = q.poll(); // 현재 상태
            int dist = map.get(here); // 현재 상태까지의 이동 횟수

            // 목표 상태에 도달했는지 확인
            if (here.equals(target)) return dist;

            // 빈 칸의 위치 찾기
            int zeroIndex = here.indexOf('0'); // 문자열에서 '0'의 위치
            int y = zeroIndex / 3; // 행 인덱스 (0부터 시작)
            int x = zeroIndex % 3; // 열 인덱스 (0부터 시작)

            // 네 방향으로 이동 시도
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir]; // 새로운 행 인덱스
                int nx = x + dx[dir]; // 새로운 열 인덱스

                // 퍼즐 범위 내인지 확인
                if(ny < 0 || ny >= 3 || nx < 0 || nx >= 3) continue;
                int swapIndex = ny * 3 + nx; // 교환할 위치의 인덱스

                // 현재 상태를 문자 배열로 변환하여 교환 작업 수행
                char[] chars = here.toCharArray();

                // 빈 칸('0')과 인접한 숫자 교환
                chars[zeroIndex] = chars[swapIndex];
                chars[swapIndex] = '0';

                String next = new String(chars); // 새로운 상태

                // 방문하지 않은 상태라면 큐에 추가하고 방문 표시
                if (!map.containsKey(next)) {
                    map.put(next, dist + 1); // 이동 횟수 +1
                    q.offer(next);
                }
            }
        }

        // 목표 상태에 도달하지 못한 경우
        return -1;
    }
}
