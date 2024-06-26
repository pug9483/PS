import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }

    private static int solve() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int index = lowerBound(A[i], list);
            if(list.isEmpty() || index == list.size()) list.add(A[i]);
            else list.set(index, A[i]);
        }
        return list.size();
    }

    private static int lowerBound(int target, List<Integer> list) {
        int low = 0;
        int high = list.size();

        while(low < high) {
            int mid = (low + high) / 2;

            if(list.get(mid) >= target) high = mid;
            else low = mid + 1;
        }
        return high;
    }
}