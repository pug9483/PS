import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] pos;
    static final int[] dy = {0, 0, 1, -1};
    static final int[] dx = {1, -1, 0, 0};
    static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        pos = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            List<Integer> friends = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                int v = Integer.parseInt(st.nextToken());
                friends.add(v);
            }
            map.put(u, friends);
            update(u);
//            print();
        }
        System.out.println(calculate());

    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(pos[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    private static void update(int student) {
        int fy = 0, fx = 0;
        int[][] likeFriend = new int[N][N];
        int[][] likeSpace = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (pos[y][x] != 0) {
                    continue;
                }
                int spaceCnt = 0;
                int likeCnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        continue;
                    }
                    for (int friend : map.get(student)) {
                        if (pos[ny][nx] == friend) {
                            likeCnt++;
                        } else if (pos[ny][nx] == 0) {
                            spaceCnt++;
                        }
                    }
                }
                likeFriend[y][x] = likeCnt;
                likeSpace[y][x] = spaceCnt;
            }
        }

        int likeFriendCnt = -1;
        int likeSpaceCnt = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(pos[i][j] != 0) continue;
                if ((likeFriend[i][j] > likeFriendCnt) || (likeFriend[i][j] == likeFriendCnt && likeSpace[i][j] > likeSpaceCnt)) {
                    fy = i;
                    fx = j;
                    likeFriendCnt = likeFriend[i][j];
                    likeSpaceCnt = likeSpace[i][j];
                }
            }
        }
        pos[fy][fx] = student;
    }

    private static int calculate() {
        int ret = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int cnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        continue;
                    }
                    for (int friend : map.get(pos[y][x])) {
                        if (pos[ny][nx] == friend) {
                            cnt++;
                        }
                    }
                }
                int num = cnt == 0 ? 0 : (int) (Math.pow(10, cnt - 1));
                ret += num;
            }
        }
        return ret;
    }
}