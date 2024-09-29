import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static int[] cnt;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        cnt = new int[1000001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            cnt[A[i]]++;
        }
        solve();
    }

    private static void solve() {
        int[] ret = new int[N];

        Stack<Integer> stack = new Stack<>(); // 인덱스 저장
        for (int hereIdx = 0; hereIdx < N; hereIdx++) {
            while (!stack.isEmpty()) {
                int prevIdx = stack.peek();
                if (cnt[A[prevIdx]] < cnt[A[hereIdx]]) {
                    prevIdx = stack.pop();
                    ret[prevIdx] = A[hereIdx];
                }
                else break;
            }
            stack.add(hereIdx);
        }

        while (!stack.isEmpty()) {
            ret[stack.pop()] = -1;
        }
        printArr(ret);
    }

    private static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) sb.append(num).append(" ");
        System.out.println(sb);
    }
}