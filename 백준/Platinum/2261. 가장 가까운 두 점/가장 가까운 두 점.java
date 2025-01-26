import java.util.*;

public class Main {

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    private static int bruteForce(List<Point> a, int start, int end) {
        int ans = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                int d = dist(a.get(i), a.get(j));
                ans = Math.min(ans, d);
            }
        }
        return ans;
    }

    private static int closest(List<Point> a, int start, int end) {
        int n = end - start + 1;
        if (n <= 3) {
            return bruteForce(a, start, end);
        }

        int mid = (start + end) / 2;
        int left = closest(a, start, mid);
        int right = closest(a, mid + 1, end);
        int ans = Math.min(left, right);

        List<Point> strip = new ArrayList<>();
        int midX = a.get(mid).x;
        for (int i = start; i <= end; i++) {
            if ((a.get(i).x - midX) * (a.get(i).x - midX) < ans) {
                strip.add(a.get(i));
            }
        }

        strip.sort(Comparator.comparingInt(p -> p.y));
        int m = strip.size();
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m && (strip.get(j).y - strip.get(i).y) * (strip.get(j).y - strip.get(i).y) < ans; j++) {
                ans = Math.min(ans, dist(strip.get(i), strip.get(j)));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x, y));
        }

        points.sort(Comparator.comparingInt(p -> p.x));
        System.out.println(closest(points, 0, n - 1));
        sc.close();
    }
}