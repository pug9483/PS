import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N의 배수로 양을 세기
// 0 ~ 9까지 모든 숫자 보기
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int finish = (1 << 10) - 1;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            int num = Integer.parseInt(br.readLine());
            int ret = solve(num);
            System.out.printf("#%d %d\n", testCase, ret);
        }
    }

    private static int solve(int num) {
        int set = 0;
        int sum = num;
        while (true) {
            char[] A = String.valueOf(sum).toCharArray();
            for (int i = 0; i < A.length; i++) {
                int c = (A[i] - '0');
                set |= (1 << c);
            }

            if(set == finish) break;
            sum += num;
        }
        return sum;
    }
}
