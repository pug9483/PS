import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] A;
    static int[] minTree;
    static int[] maxTree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        minTree = new int[4 * N];
        maxTree = new int[4 * N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        minInit(1, 0, N - 1);
        maxInit(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            int min = minQuery(1, 0, N - 1, left, right);
            int max = maxQuery(1, 0, N - 1, left, right);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
    }

    private static int minInit(int node, int start, int end) {
        if (start == end) return minTree[node] = A[start];
        int mid = (start + end) / 2;
        int leftMin = minInit(node * 2, start, mid);
        int rightMin = minInit(node * 2 + 1, mid + 1, end);
        return minTree[node] = Math.min(leftMin, rightMin);
    }

    private static int minQuery(int node, int start, int end, int left, int right) {
        if (end < left || start > right) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minTree[node];
        int mid = (start + end) / 2;
        int leftMin = minQuery(node * 2, start, mid, left, right);
        int rightMin = minQuery(node * 2 + 1, mid + 1, end, left, right);
        return Math.min(leftMin, rightMin);
    }

    private static int maxInit(int node, int start, int end) {
        if (start == end) return maxTree[node] = A[start];
        int mid = (start + end) / 2;
        int leftMax = maxInit(node * 2, start, mid);
        int rightMax = maxInit(node * 2 + 1, mid + 1, end);
        return maxTree[node] = Math.max(leftMax, rightMax);
    }

    private static int maxQuery(int node, int start, int end, int left, int right) {
        if (end < left || start > right) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[node];
        int mid = (start + end) / 2;
        int leftMax = maxQuery(node * 2, start, mid, left, right);
        int rightMax = maxQuery(node * 2 + 1, mid + 1, end, left, right);
        return Math.max(leftMax, rightMax);
    }
}