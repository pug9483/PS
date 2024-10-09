import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int[] A;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        A = new int[N];

        int minMoney = Integer.MAX_VALUE;
        int minNum = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (minMoney >= A[i]) {
                minMoney = A[i];
                minNum = i;
            }
        }

        M = Integer.parseInt(br.readLine());

        solve(minMoney, minNum);
    }

    static void solve(int minMoney, int index) {
        int[] digits = new int[51];
        int money = M;

        int cnt = 0;
        while (money >= minMoney) {
            digits[cnt++] = index;
            money -= minMoney;
        }

        int start = 0;
        for (int i = 0; i < cnt; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (A[j] <= money + minMoney) {
                    digits[i] = j;
                    money += minMoney - A[j];
                    break;
                }
            }

            if (digits[start] == 0) {
                start++;
                money += minMoney;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (start == cnt) sb.append("0");
        else{
            for (int i = start; i < cnt; i++) {
                sb.append(digits[i]);
            }
        }
        System.out.println(sb);
    }
}