import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();
            if(s.equals("0")) break;

            if(check(s)) sb.append("yes\n");
            else sb.append("no\n");
        }

        System.out.print(sb);
    }

    private static boolean check(String s) {
        int start = 0;
        int end = s.length() - 1;

        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
