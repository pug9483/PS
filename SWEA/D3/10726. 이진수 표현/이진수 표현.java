import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N의 배수로 양을 세기
// 0 ~ 9까지 모든 숫자 보기
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.printf("#%d %s\n", testCase, solve(N, M));
        }
    }

    private static String solve(int n, int m) {
        if((m & ((1 << n)-1)) == (1 << n)-1){
            return "ON";
        }
        return "OFF";
    }
}
