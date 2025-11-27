import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static class Point {
		int y;
		int x;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N;
	public static int M;
	public static int L;
	public static int K;
	public static List<Point> points;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		points = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.add(new Point(x, y));
		}
		int ret = 0;

		for(int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			for(int j = 0; j < points.size(); j++) {
				Point p2 = points.get(j);
				int cnt = 0;
				for (Point star : points) {
					if (star.x >= p1.x && star.x <= p1.x + L &&
						star.y >= p2.y && star.y <= p2.y + L) {
						cnt++;
					}
				}
				ret = Math.max(ret, cnt);
			}
		}
		System.out.println(K - ret);
	}
}
