
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Point
{
	public int y;
	public int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {
	//0 : 빈칸
	//1 : 집  < 2N
	//2 : M <= 치킨집 <= 13 (치킨집의 개수는 최대 M개)
	
	//도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성
	public static int N;
	public static List<Point> chickens;
	public static List<Point> houses;
	public static boolean[] visited;
	public static int ret = Integer.MAX_VALUE;
	
	public static int getDist(Point a, Point b) {
		return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		chickens = new ArrayList<>();
		houses = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int tmp = sc.nextInt();
				if(tmp == 1) houses.add(new Point(j,i));
				if(tmp == 2) chickens.add(new Point(j,i));
			}
		}
		visited = new boolean[chickens.size()];

		for(int i=M; i>=1; i--) {
			solve(0,0,M);
		}
		System.out.println(ret);
		
	}

	private static void solve(int here, int count, int size) {
		if(count == size) {
			check();
			return;
		}
		
		for(int i=here; i<chickens.size(); i++) {
			visited[i] = true;
	
			solve(i+1, count+1, size);
			visited[i] = false;
		}
	}

	private static void check() {
		int total = 0;
		for(var house : houses) {
			int temp = Integer.MAX_VALUE;
			for(int i=0; i<chickens.size(); i++) {
				if(visited[i]) {
					temp = Math.min(temp, getDist(house, chickens.get(i)));
				}
			}
			total += temp;
		}
		if(total < ret) ret = total;
	}
}
