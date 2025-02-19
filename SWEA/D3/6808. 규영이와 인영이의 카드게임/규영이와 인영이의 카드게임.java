
import java.util.*;
import java.io.*;

public class Solution {

	private static int T;
	private static int win, lose;

	private static int[] gCard, iCard, candCard;
	private static boolean[] initCard;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			win = lose = 0;
			iCard = new int[9];
			gCard = new int[9];
			candCard = new int[9];
			initCard = new boolean[19];
			visited = new boolean[19];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				gCard[i] = Integer.parseInt(st.nextToken());
				initCard[gCard[i]] = true;
			}

			int iCount = 0;
			for (int i = 1; i <= 18; i++) {
				if (!initCard[i]) {
					iCard[iCount++] = i;
				}
			}

			makePermutation(0);
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static void makePermutation(int index) {
		if (index == 9) {
			int count = 0;
			for (int i = 0; i < 9; i++) {
				if (gCard[i] > candCard[i])
					count += gCard[i] + candCard[i];
			}

			if (count > 171 - count)
				win++;
			else
				lose++;

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;

				candCard[index] = iCard[i];
				makePermutation(index + 1);

				visited[i] = false;
			}
		}

	}
}
