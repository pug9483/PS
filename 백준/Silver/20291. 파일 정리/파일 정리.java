import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String file = br.readLine();
            String[] splitedFile = splitFile(file);
            if(map.containsKey(splitedFile[1])){
                map.get(splitedFile[1]).add(splitedFile[0]);
            }
            else{
                List<String> files = new ArrayList<>();
                files.add(splitedFile[0]);
                map.put(splitedFile[1], files);
            }
        }

        StringBuilder sb = new StringBuilder();
        map.keySet().stream()
                .sorted()
                .forEach(o -> sb.append(o).append(" ").append(map.get(o).size()).append("\n"));
        System.out.println(sb);
    }

    private static String[] splitFile(String file) {
        String[] split = file.split("\\.", -1);
        return split;
    }
}