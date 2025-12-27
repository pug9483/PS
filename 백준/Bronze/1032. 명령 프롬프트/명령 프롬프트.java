import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static List<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        M = words.isEmpty() ? 0 : words.get(0).length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            char commonChar = words.get(0).charAt(i);
            boolean isCommon = true;
            for (int j = 1; j < N; j++) {
                if(commonChar != words.get(j).charAt(i)) {
                    isCommon = false;
                    break;
                }
            }

            if(isCommon) {
                sb.append(commonChar);
            } else {
                sb.append('?');
            }
        }
        System.out.println(sb);
    }
}