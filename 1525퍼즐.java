import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N = 3;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Map<String, Integer> map = new HashMap<>();
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(br.readLine().replace(" ", ""));
        }

        System.out.println(solve(sb.toString(), "123456780"));
    }

    public static int solve(String src, String dst){
        if(src.equals(dst)) return 0;
        map.put(src, 1);
        map.put(dst, -1);
        Queue<String> q = new LinkedList<>();
        q.add(src);
        q.add(dst);
        while (!q.isEmpty()) {
            String here = q.poll();
            int dist = map.get(here);
            int empty = here.indexOf("0");
            int y = empty / 3;
            int x = empty % 3;
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if(check(ny, nx)) continue;
                String s = swap(y, x, ny, nx, here);
                if(map.get(s) == null){
                    q.add(s);
                    map.put(s, dist > 0 ? dist+1 : dist-1);
                }
                else if(map.get(s) * dist < 0){
                    return Math.abs(map.get(s)) + Math.abs(dist) - 1;
                }
            }
        }
        return -1;
    }

    private static String swap(int y, int x, int ny, int nx, String s) {
        StringBuilder sb = new StringBuilder(s);
        int origin = y*3 + x;
        int change = ny*3 + nx;
        char c = sb.charAt(change);
        sb.setCharAt(change, '0');
        sb.setCharAt(origin, c);
        return sb.toString();
    }


    private static boolean check(int y, int x) {
        if(y < 0 || y >= N || x < 0 || x >= N) return true;
        return false;
    }
}
