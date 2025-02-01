import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        for (int testCase = 1; testCase <= 10; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 암호문의 개수
            list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine()); // 명령어의 개수

            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                String command = st.nextToken();
                if(command.equals("I")) {
                    List<Integer> newList = new ArrayList<>();

                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int j = 0; j < y; j++) {
                        newList.add(Integer.parseInt(st.nextToken()));
                    }
                    list.addAll(x, newList);

                } else if(command.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int j = 0; j < y; j++) {
                        if(x + 1 < list.size()) {
                            list.remove(x + 1);
                        }
                    }

                } else if(command.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    List<Integer> newList = new ArrayList<>();

                    for (int j = 0; j < y; j++) {
                        newList.add(Integer.parseInt(st.nextToken()));

                    }
                    list.addAll(newList);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i)).append(" ");
            }
            // 명령어
            System.out.printf("#%d %s\n", testCase, sb);
        }
    }
}
