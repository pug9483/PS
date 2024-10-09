import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        int[] stationery = new int[N];
        int min = 50;
        int index = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stationery[i] = Integer.parseInt(st.nextToken());

            if (min >= stationery[i]) {
                min = stationery[i];
                index = i;
            }
        }

        int money = Integer.parseInt(br.readLine());
        char[] digits = new char[51];

        int cnt = 0;
        while (money >= min) {
            digits[cnt++] = (char) (index + '0');
            money -= min;
        }

        int start = 0;
        for (int i = 0; i < cnt; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (stationery[j] <= money + min) {
                    digits[i] = (char) (j + '0');
                    money += min - stationery[j];
                    break;
                }
            }

            if (digits[start] == '0') {
                start++;
                money += min;
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