import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int num;
		int idx;

		public Node(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}

	static class Point {
		int num;
		int aIdx;
		int bIdx;

		public Point(int num, int aIdx, int bIdx) {
			this.num = num;
			this.aIdx = aIdx;
			this.bIdx = bIdx;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		List<Node> A = new ArrayList<>();
		StringTokenizer st =  new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			A.add(new Node(num, i));
		}

		M = Integer.parseInt(br.readLine());
		List<Node> B = new ArrayList<>();
		st =  new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			B.add(new Node(num, i));
		}

		A.sort((o1, o2) -> {
			int cmp =  Integer.compare(o2.num, o1.num);
			return cmp == 0 ? Integer.compare(o1.idx, o2.idx) : cmp;
		});
		B.sort((o1, o2) -> {
			int cmp =  Integer.compare(o2.num, o1.num);
			return cmp == 0 ? Integer.compare(o1.idx, o2.idx) : cmp;
		});

		List<Point> ret = new ArrayList<>();
		ret.add(new Point(-1, -1, -1));
		for(Node a : A){
			Point prev = ret.get(ret.size() - 1);
			if(a.idx <= prev.aIdx) continue;
			for(Node b : B){
				if(b.idx <= prev.bIdx) continue;
				if(a.num == b.num){
					ret.add(new Point(a.num, a.idx, b.idx));
					break;
				}
			}
		}


		System.out.println(ret.size() - 1);
		for (int i = 1; i < ret.size(); i++) {
			System.out.print(ret.get(i).num + " ");
		}
		System.out.println();
	}
}
