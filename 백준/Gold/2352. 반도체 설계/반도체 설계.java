import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] A;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

    public static int solve() {
        List<Integer> list = new ArrayList<>();
        list.add(A[0]);

        for (int i = 1; i < N; i++) {
            int here = A[i];
            if(list.get(list.size() - 1) < here) list.add(here);
            else{
                int idx = Collections.binarySearch(list, here);
                if(idx < 0) idx = -idx - 1;
                list.set(idx, here);
            }
        }
        return list.size();
    }
}
