import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static String S;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = br.readLine();

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < S.length(); i++) {
            int num = S.charAt(i) - '0';
            if (!stack.isEmpty() && stack.peek() < num && cnt < K) {
                stack.pop();
                cnt++;
                i--;
                continue;
            }
            stack.add(num);
        }

        while (!stack.isEmpty() && cnt < K) {
            cnt++;
            stack.pop();
        }

        stack.forEach(sb::append);
        System.out.println(sb);
    }
}