import java.io.*;
import java.util.*;

public class Main {
    static class Square {
        int x1, y1;
        int x2, y2;

        public Square(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] parent;
    static List<Square> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        points.add(new Square(0, 0, 0, 0));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            points.add(new Square(x1, y1, x2, y2));
        }

        for (int i = 1; i < points.size(); i++) {
            Square p1 = points.get(i);
            for (int j = i - 1; j >= 0; j--) {
                Square p2 = points.get(j);
                if (intersect(p1, p2)) {
                    union(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= N; i++) {
            set.add(find(i));
        }
        System.out.println(set.size() - 1);
    }

    private static Integer find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    private static void union(int i, int j) {
        parent[find(i)] = find(j);
    }

    public static boolean intersect(Square p1, Square p2) {
        boolean a = p1.y2 > p2.y2 && p2.x2 < p1.x2 && p2.y1 > p1.y1 && p2.x1 > p1.x1;
        boolean b = p1.y2 < p2.y2 && p2.x2 > p1.x2 && p2.y1 < p1.y1 && p2.x1 < p1.x1;
        boolean c = p1.y2 < p2.y1 || p1.x2 < p2.x1 || p1.y1 > p2.y2 || p1.x1 > p2.x2;
        return !(a || b || c);
    }
}